package abn.pojo;

/**
 * This class represents Client_Information data in output.csv
 */
public class TotalTransactionCostBean {
    private final long quantityLong;
    private final long quantityShort;

    public TotalTransactionCostBean(long quantityLong, long quantityShort) {
        this.quantityLong = quantityLong;
        this.quantityShort = quantityShort;
    }

    private long getQuantityLong() {
        return quantityLong;
    }

    private long getQuantityShort() {
        return quantityShort;
    }

    public long getQuantity() {
        return getQuantityLong() - getQuantityShort();
    }

    @Override
    public String toString() {
        return String.valueOf(getQuantity());
    }
}
