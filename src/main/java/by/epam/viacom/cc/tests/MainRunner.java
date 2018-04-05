package by.epam.viacom.cc.tests;


import org.testng.TestNG;
import org.testng.xml.SuiteXmlParser;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRunner {
    public static void main(String[] args) {
//        String file = "src/resources/testng.xml";
//        SuiteXmlParser parser = new SuiteXmlParser();
//        XmlSuite suite = null;
//        try {
//            suite = parser.parse(file, new FileInputStream(file), true);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        XmlSuite suite = new XmlSuite();
        suite.setName("Suite1");

        XmlTest test = new XmlTest(suite);
        test.setName("Test1");
        List<XmlClass> classes = new ArrayList<XmlClass>();

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("browser", "Firefox");
        suite.setParameters(parameters);

        classes.add(new XmlClass("by.epam.viacom.cc.tests.ProxyTest"));
        test.setXmlClasses(classes);

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }
}
