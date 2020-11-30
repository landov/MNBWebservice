package hu.landov.mnb.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBCurrencyUnits")
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
