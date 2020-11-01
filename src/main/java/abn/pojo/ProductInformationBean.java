package abn.pojo;

/**
 * This class represents Product_Information data in output.csv
 */
public class ProductInformationBean {

    private final String exchangeCode;
    private final String productGroupCode;
    private final String symbol;
    private final String expirationDate;

    public ProductInformationBean(String exchangeCode, String productGroupCode, String symbol, String expirationDate) {
        this.exchangeCode = exchangeCode;
        this.productGroupCode = productGroupCode;
        this.symbol = symbol;
        this.expirationDate = expirationDate;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public String getProductGroupCode() {
        return productGroupCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "exchangeCode='" + exchangeCode +
                ",productGroupCode='" + productGroupCode +
                ",symbol='" + symbol +
                ",expirationDate='" + expirationDate;
    }
}
