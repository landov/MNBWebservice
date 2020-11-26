package hu.landov.mnb;

import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import hu.landov.mnb.classes.Day;
import hu.landov.mnb.classes.MNBCurrencies;
import hu.landov.mnb.classes.MNBCurrentExchangeRates;
import hu.landov.mnb.classes.MNBExchangeRates;
import hu.landov.mnb.classes.Rate;
import hu.landov.mnb.soap.MNBArfolyamServiceSoap;
import hu.landov.mnb.soap.MNBArfolyamServiceSoapImpl;

public class MNBWebserviceFacade {

	private MNBArfolyamServiceSoapImpl service = new MNBArfolyamServiceSoapImpl();
	private MNBArfolyamServiceSoap port = service.getCustomBindingMNBArfolyamServiceSoap();
	private JAXBContext jaxbContext;
	private Unmarshaller jaxbUnmarshaller;

	private <T> Object unmarshall(String xml, Class<T> mnbclass) throws Exception {
		jaxbContext = JAXBContext.newInstance(mnbclass);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Object unmarshalled = jaxbUnmarshaller.unmarshal(new StringReader(xml));
		return unmarshalled;
	}
	
	

	public ExchangeRate getCurrentExchangeRate(String currency) throws Exception {
		String date = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
		String xml = port.getExchangeRates(date, date, currency);
		MNBExchangeRates currentExchangeRates = (MNBExchangeRates) unmarshall(xml,
				MNBExchangeRates.class);
		Day[] days = currentExchangeRates.getDay();
		Rate[] rates = days[0].getRate();
		Rate rate = rates[0];
		ExchangeRate exchangeRate = new ExchangeRate(date,rate);
		return exchangeRate;

	}
}
