package abn.writer;

import abn.pojo.CsvOuputMappingBean;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.Writer;
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
}

