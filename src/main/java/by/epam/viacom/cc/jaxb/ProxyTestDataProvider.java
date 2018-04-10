package by.epam.viacom.cc.jaxb;

import org.testng.annotations.DataProvider;

public class ProxyTestDataProvider {

    private static final String TP1 = "src/main/resources/TestPass1.xml";
    private static final String TP2 = "src/main/resources/TestPass2.xml";
    private static final String TF1 = "src/main/resources/TestFail1.xml";
    private static final String TF2 = "src/main/resources/TestFail1.xml";

    @DataProvider(name = "XmlDataProvider")
    public Object[][] getDataFromDataprovider() {

        return new Object[][]
                {
                        {new JaxbUtils().parseXML(TP1)},
                        {new JaxbUtils().parseXML(TP2)},
                        {new JaxbUtils().parseXML(TF1)},
                        {new JaxbUtils().parseXML(TF2)}

                };
    }
}