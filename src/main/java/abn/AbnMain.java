package abn;

import abn.reader.CsvOutputMappingBuilder;
import abn.writer.CSVOutputResultGenerator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Main Class
 */
public class AbnMain {

    private static final Logger log = Logger.getLogger(AbnMain.class);

    public static void main(String[] args)
            throws IOException {
        log.info("Starting main method");
        CsvOutputMappingBuilder reader = new CsvOutputMappingBuilder();
        reader.readFileUsingInputStream(getInputStream("input/Input.txt"));
        CSVOutputResultGenerator.generateOutputCsv("output.csv", reader.getMappingBeanList());
    }

    private static InputStream getInputStream(String filePath){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(filePath);
    }
}
