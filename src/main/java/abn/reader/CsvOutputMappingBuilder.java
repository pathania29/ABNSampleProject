package abn.reader;

import abn.pojo.ClientInformationBean;
import abn.pojo.CsvOuputMappingBean;
import abn.pojo.ProductInformationBean;
import abn.pojo.TotalTransactionCostBean;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static abn.util.CSVColumnDataExtractorUtil.*;
import static abn.writer.CSVOutputResultGenerator.createErrorRecordsFile;

/**
 * Reference Document: System A File Specification.pdf
 * PROCESSED FUTURE MOVEMENT
 * This class reads input.txt and generates data in an list which is then used to
 * build Output.csv.
 */
public class CsvOutputMappingBuilder {

    private static final Logger log = Logger.getLogger(CsvOutputMappingBuilder.class);

    private final List<CsvOuputMappingBean> csvOuputMappingBeanList = new ArrayList<>();

    private final List<String> errorRecords = new ArrayList<>();

    /**
     * Method to read input.txt file
     *
     * @param stream
     * @throws IOException
     */
    public void readFileUsingInputStream(InputStream stream) throws IOException {
        log.info(" Started reading Input file ...");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String record;
            while ((record = bufferedReader.readLine()) != null) {
                try {
                    String clientInformation = getClientInformation(record).toString();
                    String productInformation = getProductInformation(record).toString();
                    String totalTransactionCost = getTotalTransactionCost(record).toString();
                    CsvOuputMappingBean csvOuputMappingBean = new CsvOuputMappingBean(clientInformation,
                            productInformation, totalTransactionCost);
                    csvOuputMappingBeanList.add(csvOuputMappingBean);
                } catch (Exception exe) {
                    log.error(" Issue with record " + record + " with message " + exe.getMessage());
                    errorRecords.add(record);
                    exe.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw e;
        }

        log.info("Finished reading input file...");

        checkErrorRecords("ErrorRecords.txt");

    }

    private void checkErrorRecords(String errorFileName) {
        log.info(" Error records size is " + getErrorRecords().size());
        if (!errorRecords.isEmpty()) {
            createErrorRecordsFile(getErrorRecords(), errorFileName);
        }
    }

    /**
     * Method to get Client_Information
     *
     * @param record
     * @return
     */
    private ClientInformationBean getClientInformation(String record) {
        return getClientInformationColumnData(record);
    }

    /**
     * Method to get Product_Information
     *
     * @param record
     * @return
     */
    private ProductInformationBean getProductInformation(String record) {
        return getProductInformationColumnData(record);
    }

    /**
     * Method to get Total_Transaction_Amount
     *
     * @param record
     * @return
     */
    private TotalTransactionCostBean getTotalTransactionCost(String record) {
        return getTotalTransactionCostColumnData(record);
    }

    public List<CsvOuputMappingBean> getMappingBeanList() {
        return csvOuputMappingBeanList;
    }

    public List<String> getErrorRecords() {
        return errorRecords;
    }
}
