package by.epam.viacom.cc.tests;


import by.epam.viacom.cc.utils.CLParser;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.testng.TestNG;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainRunner {

    @Argument
    private List<String> arguments = new ArrayList<String>();

    @Option(name = "-t", usage = "number of threads for test run")
    private int threads;

    @Option(name = "-b", usage = "browser for test run")
    private String browser;

    public static void main(String[] args)  {
        String[] args2 = {"-b=firefox", "-t=2"};
        new MainRunner().run(args2);

    }

    public void run(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.out.println("Unable to parse arguments");
            e.printStackTrace();
            return;
        }
        if (arguments.isEmpty()) {
            threads = 1;
            browser = "chrome";
        }

        TestNG tng = new TestNG();
        tng.setXmlSuites(new TestSuite().filledXmlSuite(browser, threads));
        tng.run();
    }


}
