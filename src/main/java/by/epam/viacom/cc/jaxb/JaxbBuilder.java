package by.epam.viacom.cc.jaxb;

import javax.xml.bind.*;
import java.io.*;

public class JaxbBuilder {

    public static void main (String [] args){
        JaxbBuilder.buildXML();
        JaxbBuilder.parseXML();
    }


    public static void buildXML() {
        TestParams test = new TestParams();
        test.setAdsEnabled(true);
        test.setAdServer("ads.google.com");

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

    public static void parseXML() {
        try {
            InputStream is = new FileInputStream("TestParams.xml");
            JAXBContext context = JAXBContext.newInstance(TestParams.class);
            Unmarshaller um = context.createUnmarshaller();
            TestParams test = (TestParams) um.unmarshal(is);
            System.out.println(test.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}