package hu.landov.mnb;

import java.time.LocalDate;

import hu.landov.mnb.classes.Rate;

public class ExchangeRate {

	private LocalDate date;
	private Integer unit;
	private String currency;
	private Double rate;

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
		rate = rate.replace(",", ".");
		this.rate = Double.parseDouble(rate);
	}

	public ExchangeRate(String date, Rate rate) {
		this.date = LocalDate.parse(date);
		this.unit = Integer.parseInt(rate.getUnit());
		this.currency = rate.getCurr();
		String tempRate = rate.getContent().replace(",", ".");
		this.rate = Double.parseDouble(tempRate);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return date.toString() + " " + unit.toString() +" "+ currency + " " + rate.toString();
	}

}
