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

import hu.landov.mnb.pojos.Day;
import hu.landov.mnb.pojos.MNBCurrencies;
import hu.landov.mnb.pojos.MNBExchangeRates;
import hu.landov.mnb.pojos.MNBStoredInterval;
import hu.landov.mnb.pojos.Rate;
import hu.landov.mnb.soap.MNBArfolyamServiceSoap;
import hu.landov.mnb.soap.MNBArfolyamServiceSoapGetCurrenciesStringFaultFaultMessage;
import hu.landov.mnb.soap.MNBArfolyamServiceSoapGetDateIntervalStringFaultFaultMessage;
import hu.landov.mnb.soap.MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;
import hu.landov.mnb.soap.MNBArfolyamServiceSoapImpl;

/**
 * This class created as a facade to use http://www.mnb.hu/arfolyamok.asmx
 * webservice.
 * All methods returning ExchangeRate in not mentioned otherwise returns 
 * an exchange rate between a currency and hungarian forint (HUF).
 *
 * @author landov
 * @version 1.1.2
 * @since 2020-11-30
 */
public class MNBWebserviceFacade {

	private final MNBArfolyamServiceSoapImpl service;
	private final MNBArfolyamServiceSoap port;
	private JAXBContext jaxbContext;
	private Unmarshaller jaxbUnmarshaller;
	private final List<String> currencies;

	/**
	 * Public constructor
	 * 
	 * @throws MNBWebserviceFacadeException
	 */
	public MNBWebserviceFacade() throws MNBWebserviceFacadeException {
		service = new MNBArfolyamServiceSoapImpl();
		port = service.getCustomBindingMNBArfolyamServiceSoap();
		currencies = getCurrencies();
	}

	/**
	 * This generic method is used to unmarshall string responses from the
	 * webservice to POJOs.
	 * 
	 * @param xml the string response from the service
	 * @param the desired class
	 * @return <T> Object the object instance from the string representation
	 */
	private <T> Object unmarshall(String xml, Class<T> mnbclass) throws MNBWebserviceFacadeException {
		try {
			jaxbContext = JAXBContext.newInstance(mnbclass);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Object unmarshalled = jaxbUnmarshaller.unmarshal(new StringReader(xml));
			return unmarshalled;
		} catch (JAXBException e) {
			throw new MNBWebserviceFacadeException(
					"http://www.mnb.hu/arfolyamok.asmx returned no data for:" + mnbclass.getName());
		}
	}

	/**
	 * This method checks the availability of the currency by it's symbol. To get
	 * available symbols use getCurrencise() method.
	 * 
	 * @param currency Currency symbol.
	 * @return Nothing
	 * @throws IllegalArgumentException
	 */
	public void checkCurrency(String currency) throws IllegalArgumentException {
		if (!(currencies.contains(currency)))
			throw new IllegalArgumentException("Currency " + currency + " not found in dataset.");

	}

	/**
	 * This method checks the availability of the currency by it's symbol. To check
	 * available symbols use getCurrencise() method.
	 * 
	 * @return LocalDateInterval the interval of the available data.
	 * @throws IllegalArgumentException
	 */
	public LocalDateInterval getStoredInterval() throws MNBWebserviceFacadeException {
		try {
			String xml = port.getDateInterval();
			MNBStoredInterval storedInterval = (MNBStoredInterval) unmarshall(xml, MNBStoredInterval.class);
			LocalDateInterval localDateInterval = new LocalDateInterval(
					LocalDate.parse(storedInterval.getDateInterval().getStartdate()),
					LocalDate.parse(storedInterval.getDateInterval().getEnddate()));
			return localDateInterval;
		} catch (MNBArfolyamServiceSoapGetDateIntervalStringFaultFaultMessage e) {
			throw new MNBWebserviceFacadeException("Soap service thrown an exceprion: " + e.getMessage(),
					e.getFaultInfo(), e);
		}
	}

	/**
	 * Returns the list of currency symbols available.
	 * 
	 * @return List<String> of currency symbols
	 * @throws MNBWebserviceFacadeException
	 */
	public final List<String> getCurrencies() throws MNBWebserviceFacadeException {
		List<String> currencies = new ArrayList<String>();
		try {
			String xml = port.getCurrencies();
			MNBCurrencies mnbCurrencies = (MNBCurrencies) unmarshall(xml, MNBCurrencies.class);
			String[] curr = mnbCurrencies.getCurrencies().getCurr();
			for (String s : curr) {
				currencies.add(s);
			}
		} catch (MNBArfolyamServiceSoapGetCurrenciesStringFaultFaultMessage e) {
			throw new MNBWebserviceFacadeException("Soap service thrown an exceprion: " + e.getMessage(),
					e.getFaultInfo(), e);
		}
		return currencies;
	}

	/**
	 * Returns the last exchange rate of a given currency.
	 * 
	 * @param currency Currency symbol
	 * @return ExchangeRate
	 * @throws MNBWebserviceFacadeException
	 */
	public ExchangeRate getCurrentExchangeRate(String currency) throws MNBWebserviceFacadeException {
		LocalDate lastDate = getStoredInterval().getEndDate();
		return getHistoricalExchangeRates(currency, lastDate, lastDate).get(0);
	}

	/**
	 * Returns a list of exchange rates between two dates.
	 * If there is no data on a given period an empty list returned.
	 * 
	 * @param currency  Currency symbol
	 * @param startDate
	 * @param endDate
	 * @return List<ExchangeRate>
	 * @throws MNBWebserviceFacadeException
	 */
	public List<ExchangeRate> getHistoricalExchangeRates(String currency, LocalDate startDate, LocalDate endDate)
			throws MNBWebserviceFacadeException {
		checkCurrency(currency);
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
			if (days == null)
				throw new IllegalArgumentException("No data within selection.");
			for (Day day : days) {
				String date = day.getDate();
				Rate[] rates = day.getRate();
				if (rates != null) {
					Rate rate = rates[0];
					exchangeRates.add(new ExchangeRate(date, rate));
				}

			}
		} catch (MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage e) {
			throw new MNBWebserviceFacadeException("Soap service thrown an exceprion: " + e.getMessage(),
					e.getFaultInfo(), e);
		}
		Collections.sort(exchangeRates, (x, y) -> x.getDate().compareTo(y.getDate()));
		return exchangeRates;
	}

	/**
	 * Returns a list of exchange rates between two dates.
	 * If there is no data on a given period an empty list returned.
	 * 
	 * @param currency Currency symbol
	 * @param startDate
	 * @param endDate
	 * @return List<ExchangeRate>
	 * @throws MNBWebserviceFacadeException
	 */
	public List<ExchangeRate> getHistoricalExchangeRates(String currency, String startDate, String endDate)
			throws MNBWebserviceFacadeException {
		return getHistoricalExchangeRates(currency, LocalDate.parse(startDate), LocalDate.parse(endDate));
	}

	/**
	 * Returns an exchange rate of a currency on a given day. 
	 * If there is no data on a given day an exception is thrown.
	 * 
	 * @param currency Currency symbol
	 * @param date
	 * @return ExchangeRate
	 * @throws MNBWebserviceFacadeException
	 */
	public ExchangeRate getHistoricalExchangeRate(String currency, LocalDate date) throws MNBWebserviceFacadeException {
		return getHistoricalExchangeRates(currency, date, date).get(0);
	}

	/**
	 * Returns an exchange rate of a currency on a given day. 
	 * If there is no data on a given day an exception is thrown.
	 * 
	 * @param currency Currency symbol
	 * @param date
	 * @return ExchangeRate
	 * @throws MNBWebserviceFacadeException
	 */
	public ExchangeRate getHistoricalExchangeRate(String currency, String date) throws MNBWebserviceFacadeException {
		return getHistoricalExchangeRates(currency, date, date).get(0);
	}
	/**
	 * Return an exchangate between two given currency on a given day.
	 * @param currency1
	 * @param currency2
	 * @param date
	 * @return ExchangeRate
	 * @throws MNBWebserviceFacadeException
	 */
	public ExchangeRate getExchangeRateBetween(String currency1, String currency2, String date)
			throws MNBWebserviceFacadeException {
		LocalDate localDate = LocalDate.parse(date);
		return getExchangeRateBetween(currency1, currency2, localDate);
	}
	
	/**
	 * Return an exchangate between two given currency on a given day.
	 * @param currency1
	 * @param currency2
	 * @param date
	 * @return ExchangeRate
	 * @throws MNBWebserviceFacadeException
	 */
	public ExchangeRate getExchangeRateBetween(String currency1, String currency2, LocalDate date)
			throws MNBWebserviceFacadeException {
		ExchangeRate rate1 = getHistoricalExchangeRate(currency1, date);
		ExchangeRate rate2 = getHistoricalExchangeRate(currency2, date);
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setDate(date);
		exchangeRate.setCurrency(rate1.getCurrency());
		exchangeRate.setUnit(rate1.getUnit());
		exchangeRate.setRate(rate1.getRate() / rate2.getRate());
		return exchangeRate;
	}

}
