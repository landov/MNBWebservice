package hu.landov.mnb.pojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBCurrentExchangeRates")
@XmlAccessorType(XmlAccessType.FIELD)
public class MNBCurrentExchangeRates
{
	@XmlElement(name = "Day")
    private Day day;

    public Day getDay ()
    {
        return day;
    }

}
