package hu.landov.mnb.pojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="Currencies")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currencies
{
	@XmlElement(name="Curr")
    private String[] curr;

    public String[] getCurr ()
    {
        return curr;
    }

    public void setCurr (String[] curr)
    {
        this.curr = curr;
    }
}