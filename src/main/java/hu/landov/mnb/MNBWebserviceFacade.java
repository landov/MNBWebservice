package hu.landov.mnb;

import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import hu.landov.mnb.classes.Day;
import hu.landov.mnb.classes.MNBCurrencies;
import hu.landov.mnb.classes.MNBExchangeRates;
import hu.landov.mnb.classes.MNBStoredInterval;
import hu.landov.mnb.classes.Rate;
import hu.landov.mnb.soap.MNBArfolyamServiceSoap;
import hu.landov.mnb.soap.MNBArfolyamServiceSoapGetCurrenciesStringFaultFaultMessage;
import hu.landov.mnb.soap.MNBArfolyamServiceSoapGetDateIntervalStringFaultFaultMessage;
import hu.landov.mnb.soap.MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;
import hu.landov.mnb.soap.MNBArfolyamServiceSoapImpl;

public class MNBWebserviceFacade {

	private MNBArfolyamServiceSoapImpl service = new MNBArfolyamServiceSoapImpl();
	private MNBArfolyamServiceSoap port = service.getCustomBindingMNBArfolyamServiceSoap();
	private JAXBContext jaxbContext;
	private Unmarshaller jaxbUnmarshaller;

	private <T> Object unmarshall(String xml, Class<T> mnbclass) {
		try {
			jaxbContext = JAXBContext.newInstance(mnbclass);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Object unmarshalled = jaxbUnmarshaller.unmarshal(new StringReader(xml));
			return unmarshalled;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public LocalDateInterval getStoredInterval() {
		try {
			String xml = port.getDateInterval();
			MNBStoredInterval storedInterval = (MNBStoredInterval) unmarshall(xml, MNBStoredInterval.class);
			LocalDateInterval localDateInterval = new LocalDateInterval(
					LocalDate.parse(storedInterval.getDateInterval().getStartdate()),
					LocalDate.parse(storedInterval.getDateInterval().getEnddate()));
			return localDateInterval;
		} catch (MNBArfolyamServiceSoapGetDateIntervalStringFaultFaultMessage e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getCurrencies() throws MNBArfolyamServiceSoapGetCurrenciesStringFaultFaultMessage {
		List<String> currencies = new ArrayList<String>();
		try {
			String xml = port.getCurrencies();
			MNBCurrencies mnbCurrencies = (MNBCurrencies) unmarshall(xml, MNBCurrencies.class);
			String[] curr = mnbCurrencies.getCurrencies().getCurr();
			for (String s : curr) {
				currencies.add(s);
			}
		} catch (MNBArfolyamServiceSoapGetCurrenciesStringFaultFaultMessage e) {
			e.printStackTrace();
		}
		return currencies;
	}

	public ExchangeRate getCurrentExchangeRate(String currency) {
			LocalDate lastDate = getStoredInterval().getEndDate();
			return getHistorycalExchangeRates(currency,lastDate,lastDate).get(0);
	}

	public List<ExchangeRate> getHistorycalExchangeRates(String currency, LocalDate startDate, LocalDate endDate) {
		List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();
		LocalDateInterval localDateInterval = getStoredInterval();
		if (startDate.isBefore(localDateInterval.getStartDate())) {
			throw (new IllegalArgumentException(startDate + " is earlyer than stored date interval."));
		} else if (endDate.isAfter(localDateInterval.getEndDate())) {
			throw (new IllegalArgumentException(endDate + " is later than stored date interval"));
		}
		try {
			String xml = port.getExchangeRates(startDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
					endDate.format(DateTimeFormatter.ISO_LOCAL_DATE), currency);
			MNBExchangeRates currentExchangeRates = (MNBExchangeRates) unmarshall(xml, MNBExchangeRates.class);
			Day[] days = currentExchangeRates.getDay();
			if (days == null) throw new IllegalArgumentException("No data within selection.");
			for (Day day : days) {
				String date = day.getDate();
				Rate[] rates = day.getRate();
				if (rates != null) {
					Rate rate = rates[0];
					exchangeRates.add(new ExchangeRate(date, rate));
				}
				
			}
		} catch (MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage e) {
			e.printStackTrace();
		}
		Collections.sort(exchangeRates, (x, y) -> x.getDate().compareTo(y.getDate()));
		return exchangeRates;
	}
	
	public List<ExchangeRate> getHistorycalExchangeRates(String currency, String startDate, String endDate) {
		return getHistorycalExchangeRates(currency,LocalDate.parse(startDate),LocalDate.parse(endDate));
	}
	
	public ExchangeRate getHistoricalExchangeRate(String currency, LocalDate date) {
		return getHistorycalExchangeRates(currency,date,date).get(0);
	}
	
	public ExchangeRate getHistoricalExchangeRate(String currency, String date) {
		return getHistorycalExchangeRates(currency,date,date).get(0);
	}
	
	/*public ExchangeRate getExchangeRateBetween(String currency1, String currency2, LocalDate Date) {
		
	}*/
}
