package by.epam.viacom.cc.tests;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSuite {

    public List<XmlSuite> filledXmlSuite(String browser, int threads) {

        XmlSuite suite = new XmlSuite();
        suite.setName("Suite1");
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(threads);

        XmlTest test1 = new XmlTest(suite);
        test1.setName("Test1");
        List<XmlClass> classesTest1 = new ArrayList<XmlClass>();
        classesTest1.add(new XmlClass("by.epam.viacom.cc.tests.SimpleTest"));
        test1.setXmlClasses(classesTest1);
        Map<String, String> parametersTest1 = new HashMap<String, String>();
        parametersTest1.put("browser", browser);
        test1.setParameters(parametersTest1);

        XmlTest test2 = new XmlTest(suite);
        test2.setName("Test2");
        List<XmlClass> classesTest2 = new ArrayList<XmlClass>();
        classesTest2.add(new XmlClass("by.epam.viacom.cc.tests.SimpleTest"));
        test2.setXmlClasses(classesTest2);
        Map<String, String> parametersTest2 = new HashMap<String, String>();
        parametersTest2.put("browser", browser);
        test2.setParameters(parametersTest2);

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);

        return suites;
    }
}