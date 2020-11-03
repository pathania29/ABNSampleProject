package abn.reader;

import abn.pojo.CsvOuputMappingBean;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static abn.util.CSVColumnDataWriterUtil.*;
import static abn.writer.CSVOutputResultGenerator.createErrorRecordsFile;

/**
 * Reference Document: System A File Specification.pdf
 * PROCESSED FUTURE MOVEMENT
 * This class reads input.txt and generates data in an list which is then used to
 * build Output.csv.
 */
public class SingleThreadReader {

    private static final Logger log = Logger.getLogger(SingleThreadReader.class);

    private final List<CsvOuputMappingBean> csvOuputMappingBeanList = new ArrayList<>();

    private final List<String> errorRecords = new ArrayList<>();

    /**
     * Method to read input file
     *
     * @param stream input stream of bytes
     * @throws IOException IOException
     */
    public List<CsvOuputMappingBean> readFileUsingInputStream(InputStream stream) throws IOException {
        log.info(" Started reading Input file ...");
        List<String> recordsList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String record;
            while ((record = bufferedReader.readLine()) != null) {
                recordsList.add(record);
            }
        }
        log.info("Number of records read from Input file " + recordsList.size());
        return processRecordsList(recordsList);
    }

    private List<CsvOuputMappingBean> processRecordsList(List<String> recordsList) {
        for (String record : recordsList) {
            createCSVBeanOutputData(record);
        }
        log.info("Number of records processed " + recordsList.size());
        return getMappingBeanList();
    }

    private void createCSVBeanOutputData(String record){
        try {
            String clientInformation = getClientInformationColumnData(record).toString();
            String productInformation = getProductInformationColumnData(record).toString();
            String totalTransactionCost = getTotalTransactionCostColumnData(record).toString();
            CsvOuputMappingBean csvOuputMappingBean = new CsvOuputMappingBean(clientInformation,
                    productInformation, totalTransactionCost);
            csvOuputMappingBeanList.add(csvOuputMappingBean);
        } catch (Exception exe) {
            log.error(" Issue with record " + record + " with message " + exe.getMessage());
            errorRecords.add(record);
            exe.printStackTrace();
        }
    }
        /**
         * Method to create ErrorRecords.txt
         */
        public void createErrorRecords () {
            log.info("Number of records with Error: " + getErrorRecords().size());
            createErrorRecordsFile(getErrorRecords(), "SingleThreadErrorRecords.txt");
        }

        /**
         * List of columns that are written in output file
         *
         * @return CsvOuputMappingBean
         */
        public List<CsvOuputMappingBean> getMappingBeanList () {
            return csvOuputMappingBeanList;
        }

        /**
         * List of error records
         *
         * @return List of error records in String
         */
        private List<String> getErrorRecords () {
            return errorRecords;
        }
    }
