package abn.reader;

import abn.pojo.CsvOuputMappingBean;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static abn.util.CSVColumnDataExtractorUtil.*;

/**
 * Reference Document: System A File Specification.pdf
 * PROCESSED FUTURE MOVEMENT
 * This class reads input.txt and generates data in an list which is then used to
 * build Output.csv.
 */
public class CsvOutputMappingBuilder {

    private static final Logger log = Logger.getLogger(CsvOutputMappingBuilder.class);

    private final List<CsvOuputMappingBean> csvOuputMappingBeanList = new ArrayList<>();

    public void readFileUsingInputStream(InputStream stream) throws IOException {
        log.info(" Started reading Input.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String record;
        while ((record = bufferedReader.readLine()) != null) {
            String clientInformation = getClientInformation(record);
            String productInformation = getProductInformation(record);
            String totaltransactionCost = getTotalTransactionCost(record);
            CsvOuputMappingBean csvOuputMappingBean = new CsvOuputMappingBean(clientInformation, productInformation, totaltransactionCost);
            csvOuputMappingBeanList.add(csvOuputMappingBean);
        }
        bufferedReader.close();
        log.info("Input.txt read.");
    }



    private String getClientInformation(String record) {
        return getClientInformationColumnData(record);
    }

    private String getProductInformation(String record) {
        return getProductInformationColumnData(record);
    }

    private String getTotalTransactionCost(String record) {
        return getTotalTransactionCostColumnData(record);
    }

    public List<CsvOuputMappingBean> getMappingBeanList() {
        return csvOuputMappingBeanList;
    }
}
