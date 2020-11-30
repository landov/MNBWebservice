package hu.landov.mnb.pojos;

import javax.xml.bind.annotation.XmlElement;

public class Units
{
	@XmlElement(name="Unit")
    private Unit[] unit;

    public Unit[] getUnit ()
    {
        return unit;
    }

    public void setUnit (Unit[] unit)
    {
        this.unit = unit;
    }
}
