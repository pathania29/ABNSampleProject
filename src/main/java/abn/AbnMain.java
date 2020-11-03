package abn;

import abn.exceptions.AbnClientException;
import abn.pojo.CsvOuputMappingBean;
import abn.reader.MultiThreadReader;
import abn.reader.SingleThreadReader;
import abn.writer.CSVOutputResultGenerator;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Main Class
 */
public class AbnMain {

    private static final Logger log = Logger.getLogger(AbnMain.class);

    /**
     * Main method - Entry point for application
     *
     * @param args Runtime arguments
     * @throws IOException IOException
     */
    public static void main(String[] args) throws IOException {
        log.info("Starting main method");
        AbnMain main = new AbnMain();
        main.runApp(args);
    }

    /**
     * Choosing whether to use default input file or user is passing an input file
     *
     * @param args Runtime arguments
     * @throws IOException IOException
     */
    private void runApp(String[] args) throws IOException {
        String inputFile;
        String outFileName;
        String multiThreadRun;
        if (args.length == 0) {
            log.info(" Using default files. ");
            inputFile = "/input/Input.txt";
            outFileName = "output.csv";
            usingDefaultInput(inputFile, outFileName);
        } else if (args.length == 2) {
            log.info(" Using custom files. ");
            inputFile = args[0];
            outFileName = args[1];
            usingUserInput(inputFile, outFileName);
        } else {
            log.error(" Invalid Arguments Passed ");
            throw new AbnClientException("Invalid Arguments Passed");
        }
    }

    /**
     * Reading Input.txt from resources folder
     *
     * @param inputFile   Input file
     * @param outFileName Output result file
     * @throws IOException IOException
     */
    private void usingDefaultInput(String inputFile, String outFileName) throws IOException {
        log.info(" Input file is " + inputFile);
        log.info(" Output file to be generated " + outFileName);
        InputStream is = this.getClass().getResourceAsStream(inputFile);
        generateOutputReportUsingThreadPool(is, outFileName);
    }

    /**
     * Reading user supplied input data
     *
     * @param absolutePath       Absolute Path of Input file
     * @param userOutputFileName Output result file
     * @throws IOException IOException
     */
    private void usingUserInput(String absolutePath, String userOutputFileName) throws IOException {
        log.info(" Input file is " + absolutePath);
        log.info(" Output file to be generated " + userOutputFileName);
        generateOutputReportUsingSingleThread(new FileInputStream(absolutePath), userOutputFileName);
    }

    /**
     * Processing input file using Single Thread and generating output file.
     *
     * @param is          InputStream
     * @param outFileName Output file name
     * @throws IOException IOException
     */
    private void generateOutputReportUsingSingleThread(InputStream is, String outFileName) throws IOException {
        SingleThreadReader singleThreadReader = new SingleThreadReader();
        List<CsvOuputMappingBean> csvOuputMappingBeanList = singleThreadReader.readFileUsingInputStream(is);
        singleThreadReader.createErrorRecords();
        CSVOutputResultGenerator.generateOutputCsv(outFileName, csvOuputMappingBeanList);
    }

    /**
     * Processing Input file using multi-thread and generating output file.
     *
     * @param is InputStream
     * @param outFileName Output file name
     */
    private void generateOutputReportUsingThreadPool(InputStream is, String outFileName) {
        MultiThreadReader multiThreadReader = new MultiThreadReader();
        List<CsvOuputMappingBean> csvOuputMappingBeanList = multiThreadReader.readInputFile(is);
        multiThreadReader.createErrorRecordsFile();
        CSVOutputResultGenerator.generateOutputCsv(outFileName, csvOuputMappingBeanList);
    }
}
