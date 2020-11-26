package hu.landov.mnb.classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBStoredInterval")
@XmlAccessorType(XmlAccessType.FIELD)
public class MNBStoredInterval {
	@XmlElement
	private DateInterval DateInterval;

    public DateInterval getDateInterval ()
    {
        return DateInterval;
    }

    public void setDateInterval (DateInterval DateInterval)
    {
        this.DateInterval = DateInterval;
    }
}
