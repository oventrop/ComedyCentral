package by.epam.viacom.cc.tests;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainArgParser {

    @Argument
    private List<String> arguments = new ArrayList<String>();

    @Option(name = "-t", usage = "number of threads for test run")
    int threads;

    public int doMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (arguments.isEmpty())
                throw new CmdLineException(parser, "No argument is given");

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java SampleMain [options...] arguments...");
            parser.printUsage(System.err);
            System.err.println();
        }
        return threads;
    }
}
