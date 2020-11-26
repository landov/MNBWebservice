package hu.landov.mnb.classes;

import javax.xml.bind.annotation.XmlElement;

public class Currencies
{
	@XmlElement
    private String[] Curr;

    public String[] getCurr ()
    {
        return Curr;
    }

    public void setCurr (String[] Curr)
    {
        this.Curr = Curr;
    }
}