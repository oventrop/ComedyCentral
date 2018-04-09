package by.epam.viacom.cc.jaxb;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"adServer", "adsEnabled"}, name = "responseParams")
@XmlRootElement
public class TestParams {

    private String adServer;
    private boolean adsEnabled;

    public String getAdServer() {
        return adServer;
    }

    public void setAdServer(String adServer) {
        this.adServer = adServer;
    }

    public boolean isAdsEnabled() {
        return adsEnabled;
    }

    public void setAdsEnabled(boolean adsEnabled) {
        this.adsEnabled = adsEnabled;
    }

    @Override
    public String toString() {
        return "TestParams{" +
                "adServer='" + adServer + '\'' +
                ", adsEnabled=" + adsEnabled +
                '}';
    }
}
