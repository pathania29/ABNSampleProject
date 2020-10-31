package abn.util;

import abn.pojo.ClientInformationBean;
import abn.pojo.ProductInformationBean;
import abn.pojo.TotalTransactionCostBean;

public class CSVColumnDataExtractorUtil {

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
     * @param record This represents single transaction record data
     */
    public static String getClientInformationColumnData(String record) {
        StringBuilder builder = new StringBuilder(record);
        String clientType = builder.substring(3, 7);
        String clientNumber = builder.substring(7, 11);
        String accountNumber = builder.substring(11, 15);
        String subAccountNumber = builder.substring(15, 19);
        ClientInformationBean clientInformation = new ClientInformationBean(clientType,
                clientNumber, accountNumber, subAccountNumber);
        return clientInformation.toString();
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
    public static String getProductInformationColumnData(String record) {
        StringBuilder builder = new StringBuilder(record);
        String productGroupCode = builder.substring(26, 28);
        String exchangeCode = builder.substring(28, 32);
        String symbol = builder.substring(32, 38);
        String expirationDate = builder.substring(38, 46);
        ProductInformationBean productInformationBean = new ProductInformationBean(exchangeCode,
                productGroupCode, symbol, expirationDate);
        return productInformationBean.toString();
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
    public static String getTotalTransactionCostColumnData(String record) {
        StringBuilder builder = new StringBuilder(record);
        String quantityLongStr = builder.substring(53, 63);
        String quantityShortStr = builder.substring(64, 74);

        long quantityLong = Long.parseLong(quantityLongStr.trim());
        long quantityShort = Long.parseLong(quantityShortStr.trim());

        TotalTransactionCostBean totalTransactionCostBean = new TotalTransactionCostBean(quantityLong, quantityShort);
        long totalTransaction = totalTransactionCostBean.getQuantity();
        return String.valueOf(totalTransaction);
    }
}
