package hu.landov.mnb.pojos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MNBCurrencies")
public class MNBCurrencies
{
	@XmlElement
    private Currencies currencies;

    public Currencies getCurrencies ()
    {
        return currencies;
    }

    public void setCurrencies (Currencies currencies)
    {
        this.currencies = currencies;
    }

}
	