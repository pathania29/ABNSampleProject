package abn.pojo;

/**
 * This class represents Client_Information data in output.csv
 */
public class TotalTransactionCostBean {
    private final String quantityLong;
    private final String quantityShort;

    public TotalTransactionCostBean(String quantityLong, String quantityShort) {
        this.quantityLong = quantityLong;
        this.quantityShort = quantityShort;
    }

    public String getQuantityLong() {
        return quantityLong;
    }

    public String getQuantityShort() {
        return quantityShort;
    }

    public long getQuantity() {
        long quantityLongValue = Long.parseLong(getQuantityLong());
        long quantityShortValue = Long.parseLong(getQuantityShort());

        return quantityLongValue - quantityShortValue;
    }

    @Override
    public String toString() {
        return  "quantityLong=" + quantityLong +
                ",quantityShort=" + quantityShort +
                ",totalQuantity=" +getQuantity();
    }
}
