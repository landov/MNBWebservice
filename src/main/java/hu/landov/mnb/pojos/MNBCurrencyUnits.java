package hu.landov.mnb.pojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBCurrencyUnits")
@XmlAccessorType(XmlAccessType.FIELD)
public class MNBCurrencyUnits {
	@XmlElement
	private Units units;

    public Units getUnits ()
    {
        return units;
    }

    public void setUnits (Units units)
    {
        this.units = units;
    }
}
