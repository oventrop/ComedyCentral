package by.epam.viacom.cc.jaxb;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "responseParams")
@XmlRootElement
public class TestParams {

    private String adServer;
    private boolean adsEnabled;
    private int freewheelNetworkID;
    private boolean amazonEnabled;

    public boolean isAmazonEnabled() {
        return amazonEnabled;
    }

    @XmlElement
    public void setAmazonEnabled(boolean amazonEnabled) {
        this.amazonEnabled = amazonEnabled;
    }

    public int getFreewheelNetworkID() {
        return freewheelNetworkID;
    }

    @XmlElement
    public void setFreewheelNetworkID(int freewheelNetworkID) {
        this.freewheelNetworkID = freewheelNetworkID;
    }

    public String getAdServer() {
        return adServer;
    }

    @XmlElement
    public void setAdServer(String adServer) {
        this.adServer = adServer;
    }

    public boolean isAdsEnabled() {
        return adsEnabled;
    }

    @XmlElement
    public void setAdsEnabled(boolean adsEnabled) {
        this.adsEnabled = adsEnabled;
    }

    @Override
    public String toString() {
        return "TestParams{" +
                "adServer='" + adServer + '\'' +
                ", adsEnabled=" + adsEnabled +
                ", freewheelNetworkID=" + freewheelNetworkID +
                ", amazonEnabled=" + amazonEnabled +
                '}';
    }

}
