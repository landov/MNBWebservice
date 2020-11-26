package hu.landov.mnb.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBCurrencies")
public class MNBCurrencies
{
	@XmlElement
    private Currencies Currencies;

    public Currencies getCurrencies ()
    {
        return Currencies;
    }

    public void setCurrencies (Currencies Currencies)
    {
        this.Currencies = Currencies;
    }

}
	