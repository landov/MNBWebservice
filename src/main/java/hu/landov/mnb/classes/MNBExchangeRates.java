package hu.landov.mnb.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBExchangeRates")
public class MNBExchangeRates {
	
	@XmlElement
	private Day[] Day;

    public Day[] getDay ()
    {
        return Day;
    }

    public void setDay (Day[] Day)
    {
        this.Day = Day;
    }
}
