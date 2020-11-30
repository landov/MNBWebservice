package hu.landov.mnb.pojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBExchangeRates")
@XmlAccessorType(XmlAccessType.FIELD)
public class MNBExchangeRates {
	
	@XmlElement(name="Day")
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
