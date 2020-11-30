package hu.landov.mnb;

import java.time.LocalDate;
import java.util.Comparator;

import hu.landov.mnb.pojos.Rate;

/**
 * Representation of an exchange rate of a currency compared to another.
 *
 * @author landov
 * @version 1.1.2
 * @since 2020-11-30
 */
public class ExchangeRate {

	/**
	 * The date on which the exchange rate valid
	 */
	private LocalDate date;
	/*
	 * The amount of a currency the rate applies to.
	 */
	private Integer unit;
	/*
	 * Three letter symbol of the currency.
	 */
	private String currency;
	/*
	 * The exchange rate.
	 */
	private Double rate;

	public ExchangeRate() {

	}

	public ExchangeRate(LocalDate date, int unit, String currency, double rate) {
		this.date = date;
		this.unit = unit;
		this.currency = currency;
		this.rate = rate;
	}

	public ExchangeRate(String date, String unit, String currency, String rate) {
		this.date = LocalDate.parse(date);
		this.unit = Integer.parseInt(unit);
		this.currency = currency;
		String tempRate = rate.replace(",", ".");
		this.rate = Double.parseDouble(tempRate);
	}

	public ExchangeRate(String date, Rate rate) {
		this.date = LocalDate.parse(date);
		this.unit = Integer.parseInt(rate.getUnit());
		this.currency = rate.getCurr();
		String tempRate = rate.getContent().replace(",", ".");
		this.rate = Double.parseDouble(tempRate);
	}

	/**
	 * Returns the date of an exhange rate.
	 * 
	 * @return LocalDate
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the date of an exhange rate,
	 * 
	 * @param date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Returns the amount of a currency the rate applies to.
	 * 
	 * @return
	 */
	public int getUnit() {
		return unit;
	}

	/**
	 * Sets the amount of a currency the rate applies to.
	 * 
	 * @param unit
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}

	/**
	 * Returns the three letter symbol of the currency.
	 * 
	 * @return currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Sets the three letter symbol of the currency.
	 * 
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Returns the exchange rate.
	 * 
	 * @return exchange rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Sets the exchange rate.
	 * 
	 * @param rate
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return date.toString() + " " + unit.toString() + " " + currency + " " + rate.toString() + " HUF";
	}

}
