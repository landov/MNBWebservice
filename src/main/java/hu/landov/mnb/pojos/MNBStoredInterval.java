package hu.landov.mnb.pojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBStoredInterval")
@XmlAccessorType(XmlAccessType.FIELD)
public class MNBStoredInterval {
	@XmlElement
	private DateInterval dateInterval;

    public DateInterval getDateInterval ()
    {
        return dateInterval;
    }

    public void setDateInterval (DateInterval dateInterval)
    {
        this.dateInterval = dateInterval;
    }
}
