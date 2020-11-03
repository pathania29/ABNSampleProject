package abn.tasks;

import abn.pojo.CsvOuputMappingBean;
import abn.util.CSVColumnDataWriterUtil;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;

/**
 * Callable task to create one row of output data
 */
public class Task implements Callable<CsvOuputMappingBean> {

    private static final Logger log = Logger.getLogger(Task.class);

    private final String record;

    public Task(String record) {
        this.record = record;
    }

    @Override
    public CsvOuputMappingBean call() {
        log.debug(" Processing record " + record);
        try {
            String clientInformation = CSVColumnDataWriterUtil.getClientInformationColumnData(record).toString();
            String productInformation = CSVColumnDataWriterUtil.getProductInformationColumnData(record).toString();
            String totalTransactionCost = CSVColumnDataWriterUtil.getTotalTransactionCostColumnData(record).toString();
            return new CsvOuputMappingBean(clientInformation,
                    productInformation, totalTransactionCost);
        } catch (Exception exe) {
            log.error(" Issue processing record: " + record + " : " +exe.getMessage());
            exe.printStackTrace();
            return new CsvOuputMappingBean("Error: " + record, null, null);
        }
    }
}
