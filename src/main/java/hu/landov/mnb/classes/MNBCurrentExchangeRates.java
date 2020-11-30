package hu.landov.mnb.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBCurrentExchangeRates")
public class MNBCurrentExchangeRates
{
	@XmlElement
    private Day day;

    public Day getDay ()
    {
        return day;
    }

    public void setDay (Day day)
    {
        this.day = day;
    }

}
