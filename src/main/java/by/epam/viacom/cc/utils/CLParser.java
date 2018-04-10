package by.epam.viacom.cc.utils;

import by.epam.viacom.cc.tests.TestSuite;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class CLParser {
    @Argument
    private List<String> arguments = new ArrayList<String>();

    @Option(name = "-t", usage = "number of threads for test run")
    private static int threads = 1;

    public static int getThreads() {
        return threads;
    }

    public static String getBrowser() {
        return browser;
    }

    @Option(name = "-b", usage = "browser for test run")
    private static String browser = "firefox";

    public void run(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);

        } catch (CmdLineException e) {
            System.out.println("Unable to parse arguments");
            e.printStackTrace();
            return;
        }
    }
}