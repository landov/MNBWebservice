package hu.landov.mnb.pojos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBExchangeRates")
public class MNBExchangeRates {
	
	@XmlElement
	private Day[] day;

    public Day[] getDay ()
    {
        return day;
    }

    public void setDay (Day[] day)
    {
        this.day = day;
    }
}
