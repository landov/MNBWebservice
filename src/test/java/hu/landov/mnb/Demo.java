package hu.landov.mnb;

import java.time.LocalDate;
import java.util.List;

public class Demo {

	static public void main(String[] args) {
		try {
			MNBWebserviceFacade facade = new MNBWebserviceFacade();

			System.out.println("Data available between:");
			LocalDateInterval localDateInterval = facade.getStoredInterval();
			System.out.print(localDateInterval.getStartDate() + " and ");
			System.out.println(localDateInterval.getEndDate());
			System.out.println();

			System.out.println("Available currencies:");
			List<String> currencies = facade.getCurrencies();
			for (String currency : currencies) {
				System.out.printf("%s \t", currency);
			}
			System.out.println();
			System.out.println();

			System.out.println("Some actual rates:");
			ExchangeRate currentRate;
			currentRate = facade.getCurrentExchangeRate("EUR");
			System.out.println(currentRate);
			currentRate = facade.getCurrentExchangeRate("USD");
			System.out.println(currentRate);
			currentRate = facade.getCurrentExchangeRate("CHF");
			System.out.println(currentRate);
			currentRate = facade.getCurrentExchangeRate("JPY");
			System.out.println(currentRate);
			System.out.println();

			System.out.println("Some historical rates of EUR:");
			List<ExchangeRate> historicalRates = facade.getHistoricalExchangeRates("EUR", LocalDate.parse("2020-10-01"),
					LocalDate.parse("2020-10-31"));
			for (ExchangeRate rate : historicalRates) {
				System.out.println(rate);
			}
			System.out.println();
			try {
				System.out.println("USD on 1977-05-17");
				System.out.println(facade.getHistoricalExchangeRate("USD", "1977-05-17"));
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			System.out.println();
			System.out.println();
			try {
				System.out.println("and on 1978-08-01");
				System.out.println(facade.getHistoricalExchangeRate("USD", "1978-08-01"));
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

			ExchangeRate exchangeRate;
			System.out.println("EUR/USD rate:");
			exchangeRate = facade.getExchangeRateBetween("EUR", "USD", localDateInterval.getEndDate());
			System.out.printf("%s %d %s %f USD %n", exchangeRate.getDate(), exchangeRate.getUnit(),
					exchangeRate.getCurrency(), exchangeRate.getRate());
			System.out.println("JPZ/USD rate:");
			exchangeRate = facade.getExchangeRateBetween("JPY", "USD", localDateInterval.getEndDate());
			System.out.printf("%s %d %s %f USD %n", exchangeRate.getDate(), exchangeRate.getUnit(),
					exchangeRate.getCurrency(), exchangeRate.getRate());
		} catch (MNBWebserviceFacadeException e) {
			e.printStackTrace();
		}
	}
}
