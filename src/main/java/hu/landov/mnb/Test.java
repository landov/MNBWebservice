package hu.landov.mnb;

public class Test {
	static public void main(String[] args) throws Exception {
		
		MNBWebserviceFacade facade = new MNBWebserviceFacade();
		ExchangeRate currentRate;
		currentRate = facade.getCurrentExchangeRate("EUR");
		System.out.println(currentRate);
		currentRate = facade.getCurrentExchangeRate("USD");
		System.out.println(currentRate);
	}
}
