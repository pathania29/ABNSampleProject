package abn.pojo;

/**
 * This class represents Client_Information data in output.csv
 */
public class ClientInformationBean {

    private final String clientType;
    private final String clientNumber;
    private final String accountNumber;
    private final String subAccountNumber;

    public ClientInformationBean(String clientType, String clientNumber, String accountNumber, String subAccountNumber) {
        this.clientType = clientType;
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        this.subAccountNumber = subAccountNumber;
    }

    public String getClientType() {
        return clientType;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    @Override
    public String toString() {
        return "clientType=" + clientType +
                ",clientNumber=" + clientNumber +
                ",accountNumber=" + accountNumber +
                ",subAccountNumber=" + subAccountNumber;
    }
}
