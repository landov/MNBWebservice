package hu.landov.mnb.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBCurrencyUnits")
public class MNBCurrencyUnits {
	@XmlElement
	private Units Units;

    public Units getUnits ()
    {
        return Units;
    }

    public void setUnits (Units Units)
    {
        this.Units = Units;
    }
}
