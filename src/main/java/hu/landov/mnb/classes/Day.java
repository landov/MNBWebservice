package hu.landov.mnb.classes;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Day {
	
	@XmlAttribute
	private String date;

	@XmlElement
    private Rate[] Rate;

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public Rate[] getRate ()
    {
        return Rate;
    }

    public void setRate (Rate[] Rate)
    {
        this.Rate = Rate;
    }

}
