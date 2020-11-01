package abn;

import abn.reader.CsvOutputMappingBuilder;
import abn.writer.CSVOutputResultGenerator;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Main Class
 */
public class AbnMain {

    private static final Logger log = Logger.getLogger(AbnMain.class);

    public static void main(String[] args) throws IOException {
        log.info("Starting main method");
        AbnMain main = new AbnMain();
        if (args.length == 0) {
            log.info("Using default files. ");
            main.usingDefaultInput();
        } else if (args.length == 2) {
            log.info("Using custom files. ");
            main.usingUserInput(args);
        } else {
            log.error("Invalid Arguments Passed");
        }
    }

    private void usingDefaultInput() throws IOException {
        String inputFile = "input/Input.txt";
        String outFileName = "output.csv";
        log.info(" Input file is " +inputFile);
        log.info(" Output file to be generated " +outFileName);
        runApplication(outFileName, getInputStreamFromResources(inputFile));
    }

    private void usingUserInput(String[] args) throws IOException {
        String inputFile = args[0];
        String outFileName = args[1];
        log.info(" Input file is " +inputFile);
        log.info(" Output file to be generated " +outFileName);
        runApplication(outFileName, getInputStreamFromArgs(inputFile));
    }

    private void runApplication(String outFileName, InputStream is) throws IOException {
        CsvOutputMappingBuilder reader = new CsvOutputMappingBuilder();
        reader.readFileUsingInputStream(is);
        CSVOutputResultGenerator.generateOutputCsv(outFileName, reader.getMappingBeanList());
    }

    private InputStream getInputStreamFromResources(String resources) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(resources);
    }

    private InputStream getInputStreamFromArgs(String absolutePath) throws FileNotFoundException {
      return new FileInputStream(absolutePath);
    }
}
