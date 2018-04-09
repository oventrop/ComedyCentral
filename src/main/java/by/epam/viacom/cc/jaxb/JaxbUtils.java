package by.epam.viacom.cc.jaxb;

import javax.xml.bind.*;
import java.io.*;

public class JaxbUtils {

    public void buildXML() {

        TestParams test = new TestParams();
        test.setAdsEnabled(true);
        test.setAdServer("freewheel");
        test.setFreewheelNetworkID(82125);
        test.setAmazonEnabled(false);

        try {
            JAXBContext context = JAXBContext.newInstance(TestParams.class);
            Marshaller m = context.createMarshaller();
            OutputStream os = new FileOutputStream("TestParams.xml");
            m.marshal(test, os);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public TestParams parseXML(String path) {
        TestParams test = new TestParams();
        try {
            InputStream is = new FileInputStream(path);
            JAXBContext context = JAXBContext.newInstance(TestParams.class);
            Unmarshaller um = context.createUnmarshaller();
            test = (TestParams) um.unmarshal(is);
            System.out.println(test.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return test;
    }
}