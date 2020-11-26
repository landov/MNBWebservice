package hu.landov.mnb.classes;

import javax.xml.bind.annotation.XmlElement;

public class Units
{
	@XmlElement
    private Unit[] Unit;

    public Unit[] getUnit ()
    {
        return Unit;
    }

    public void setUnit (Unit[] Unit)
    {
        this.Unit = Unit;
    }
}
