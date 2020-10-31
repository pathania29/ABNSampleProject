package abn.pojo;

import com.opencsv.bean.CsvBindByName;

/**
 * This class represents one row in Output.csv
 * To add new columns please add data here.
 * <p>
 * Note: Don't delete setter and getter being used by opencsv using reflection
 */
public class CsvOuputMappingBean {

    @CsvBindByName(column = "Client_Information")
    private String clientInformation;

    @CsvBindByName(column = "Product_Information")
    private String productInformation;

    @CsvBindByName(column = "Total_Transaction_Amount")
    private String totalTransactionAmount;

    public CsvOuputMappingBean(String clientInformation, String productInformation, String totalTransactionAmount) {
        this.clientInformation = clientInformation;
        this.productInformation = productInformation;
        this.totalTransactionAmount = totalTransactionAmount;
    }

    public String getClientInformation() {
        return clientInformation;
    }

    public void setClientInformation(String clientInformation) {
        this.clientInformation = clientInformation;
    }

    public String getProductInformation() {
        return productInformation;
    }

    public void setProductInformation(String productInformation) {
        this.productInformation = productInformation;
    }

    public String getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    public void setTotalTransactionAmount(String totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }
}
