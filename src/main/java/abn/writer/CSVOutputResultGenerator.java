package abn.writer;

import abn.pojo.CsvOuputMappingBean;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class is used to create Output.csv
 */
public class CSVOutputResultGenerator {

    private static final Logger log = Logger.getLogger(CSVOutputResultGenerator.class);

    /**
     * This method generates the actual output.csv
     *
     * @param outputFileName outputFileName
     * @param outputList     outputList
     */
    public static void generateOutputCsv(String outputFileName, List<CsvOuputMappingBean> outputList) {
        try {
            log.info("Generating... " +outputFileName);
            Writer writer = new PrintWriter(outputFileName);
            StatefulBeanToCsvBuilder<CsvOuputMappingBean> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<CsvOuputMappingBean> beanWriter = builder.build();
            beanWriter.write(outputList);
            writer.close();
            log.info(outputFileName + " generated !!! ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method creates the error record txt file
     * @param errorRecords
     */
    public static void createErrorRecordsFile(List<String> errorRecords, String errorFileName) {

        log.info(" Error records file is " +errorFileName);

        //Get the file reference
        Path path = Paths.get(errorFileName);

        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String data : errorRecords) {
                writer.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


