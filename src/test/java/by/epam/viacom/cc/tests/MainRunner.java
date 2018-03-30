package by.epam.viacom.cc.tests;

import org.testng.TestNG;
import org.testng.xml.SuiteXmlParser;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainRunner {


    public static void main(String[] args) throws IOException {
        String file = "testng.xml";
        SuiteXmlParser parser = new SuiteXmlParser();
        XmlSuite suite = parser.parse( file, new FileInputStream(file), true);;

        List<XmlSuite> suitesList= new ArrayList<>();
        suitesList.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suitesList);
        tng.run();

    }

}
