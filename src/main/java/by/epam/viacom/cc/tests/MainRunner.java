package by.epam.viacom.cc.tests;


import by.epam.viacom.cc.utils.CLParser;
import org.testng.TestNG;

public class MainRunner {

    public static void main(String[] args) {
        new CLParser().run(args);

        TestNG tng = new TestNG();
        tng.setXmlSuites(new TestSuite().filledXmlSuite(CLParser.getBrowser(), CLParser.getThreads()));
        tng.run();
    }

    //switched to MobileTest repo
}


