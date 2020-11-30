package hu.landov.mnb.pojos;

import javax.xml.bind.annotation.XmlElement;

public class Currencies
{
	@XmlElement
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