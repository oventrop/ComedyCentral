package by.epam.viacom.cc.tests;


import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.testng.TestNG;
import org.testng.xml.SuiteXmlParser;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRunner {

    @Argument
    private List<String> arguments = new ArrayList<String>();

    @Option(name = "-t", usage = "number of threads for test run")
    private int threads;

    @Option(name = "-b", usage = "browser for test run")
    private String browser;

    public static void main(String[] args) throws IOException {
         new MainRunner().run(args);
    }

     public void run(String [] args){
         CmdLineParser parser = new CmdLineParser(this);
         try {
             parser.parseArgument(args);
            if (arguments.isEmpty()) {
                threads = 1;
                browser = "chrome";
            }
         } catch (CmdLineException e) {
             System.err.println(e.getMessage());
             System.err.println("java SampleMain [options...] arguments...");
             parser.printUsage(System.err);
             System.err.println();
             return;
         }

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

//        XmlTest test3 = new XmlTest(suite);
//        test3.setName("Test3");
//        List<XmlClass> classesTest3 = new ArrayList<XmlClass>();
//        classesTest3.add(new XmlClass("by.epam.viacom.cc.tests.SimpleTest"));
//        test3.setXmlClasses(classesTest3);
//        Map<String, String> parametersTest3 = new HashMap<String, String>();
//        parametersTest3.put("browser", "firefox");
//        test3.setParameters(parametersTest3);
//
//        XmlTest test4 = new XmlTest(suite);
//        test4.setName("Test4");
//        List<XmlClass> classesTest4 = new ArrayList<XmlClass>();
//        classesTest4.add(new XmlClass("by.epam.viacom.cc.tests.SimpleTest"));
//        test4.setXmlClasses(classesTest4);
//        Map<String, String> parametersTest4 = new HashMap<String, String>();
//        parametersTest4.put("browser", "chrome");
//        test4.setParameters(parametersTest4);


        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }
}
