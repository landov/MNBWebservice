package hu.landov.mnb;

public class MNBWebserviceFacadeException extends Exception {

	/**
	 * Java type that goes as soapenv:Fault detail element.
	 * 
	 */
	private String faultInfo;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5706617787806287088L;

	public MNBWebserviceFacadeException(String errorMessage) {
		super(errorMessage);
	}

	public MNBWebserviceFacadeException(String message, String faultInfo, Throwable cause) {
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	/**
	 * 
	 * @return returns fault bean: java.lang.String
	 */
	public String getFaultInfo() {
		return faultInfo;
	}

}
