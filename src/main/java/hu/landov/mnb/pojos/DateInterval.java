package hu.landov.mnb.pojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DateInterval")
@XmlAccessorType(XmlAccessType.FIELD)
public class DateInterval {
	@XmlAttribute
	private String enddate;
	@XmlAttribute
    private String startdate;

    public String getEnddate ()
    {
        return enddate;
    }

    public void setEnddate (String enddate)
    {
        this.enddate = enddate;
    }

    public String getStartdate ()
    {
        return startdate;
    }

    public void setStartdate (String startdate)
    {
        this.startdate = startdate;
    }
}
