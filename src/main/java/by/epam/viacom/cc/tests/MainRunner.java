package by.epam.viacom.cc.tests;


import org.testng.TestNG;
import org.testng.xml.SuiteXmlParser;
import org.testng.xml.XmlSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainRunner {
    public static void main(String[] args) {
        String file = "D:/WORKSPACE/ComedyCentral/testng.xml";
        SuiteXmlParser parser = new SuiteXmlParser();
        XmlSuite suite = null;
           try {
            suite = parser.parse(file, new FileInputStream(file), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<XmlSuite> suitesList = new ArrayList<>();
        suitesList.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suitesList);
        tng.run();
    }
}
