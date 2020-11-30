package hu.landov.mnb.pojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Units")
@XmlAccessorType(XmlAccessType.FIELD)
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
