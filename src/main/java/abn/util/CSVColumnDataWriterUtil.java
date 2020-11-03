package abn.util;

import abn.pojo.ClientInformationBean;
import abn.pojo.ProductInformationBean;
import abn.pojo.TotalTransactionCostBean;

/**
 * Utility class to extract columns data for output.csv file
 */
public class CSVColumnDataWriterUtil {

    /**
     * Method logic taken from specification file
     * <p>
     * FIELDNAME         LENGTH    DESCRIPTION
     * RECORD CODE        3         "315"                     1-3
     * CLIENT TYPE        4                                   4-7
     * CLIENT NUMBER      4        (including O decimals)     8-11
     * ACCOUNT NUMBER     4        (including O decimals)     12-15
     * SUBACCOUNT NUMBER  4        (including O decimals)     16-19
     * record = record.replaceAll("\\s","");
     *
     * @param record This represents single transaction record data
     */
    public static ClientInformationBean getClientInformationColumnData(String record) {
        StringBuilder builder = new StringBuilder(record);
        String clientType = builder.substring(3, 7);
        String clientNumber = builder.substring(7, 11);
        String accountNumber = builder.substring(11, 15);
        String subAccountNumber = builder.substring(15, 19);
        return new ClientInformationBean(clientType,
                clientNumber, accountNumber, subAccountNumber);
    }

    /**
     * Method logic taken from specification file
     * <p>
     * FIELDNAME          LENGTH     DESCRIPTION
     * PRODUCT GROUP CODE   2                                   26-27
     * EXCHANGE CODE        4                                   28-31
     * SYMBOL               6                                   32-37
     * EXPIRATION DATE      8        (in format CCYYMMDD)       38-45
     *
     * @param record This represents single transaction record data
     */
    public static ProductInformationBean getProductInformationColumnData(String record) {
        StringBuilder builder = new StringBuilder(record);
        String productGroupCode = builder.substring(25, 27);
        String exchangeCode = builder.substring(27, 31);
        String symbol = builder.substring(31, 37);
        String expirationDate = builder.substring(37, 45);
        return new ProductInformationBean(exchangeCode,
                productGroupCode, symbol, expirationDate);
    }

    /**
     * Method logic taken from specification file
     * <p>
     * FIELDNAME          LENGTH        DESCRIPTION
     * QUANTITY LONG        10        (including O decimals)    53-62
     * QUANTITY SHORT       10        (including O decimals)    64-73
     *
     * @param record This represents single transaction record data
     */
    public static TotalTransactionCostBean getTotalTransactionCostColumnData(String record) {
        StringBuilder builder = new StringBuilder(record);
        String quantityLong = builder.substring(52, 62);
        String quantityShort = builder.substring(63, 73);
        return new TotalTransactionCostBean(quantityLong, quantityShort);
    }
}
