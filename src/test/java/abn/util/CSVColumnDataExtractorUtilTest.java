package abn.util;

import abn.pojo.ClientInformationBean;
import abn.pojo.ProductInformationBean;
import abn.pojo.TotalTransactionCostBean;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CSVColumnDataExtractorUtilTest {

    private final String testRecordData =
            "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O";

    @Test
    public void getClientInformationColumnData() {
        ClientInformationBean clientInformation = CSVColumnDataWriterUtil.getClientInformationColumnData(testRecordData);
        assertNotNull(clientInformation);
        assertEquals(4, clientInformation.getClientType().length());
        assertEquals(4, clientInformation.getClientNumber().length());
        assertEquals(4, clientInformation.getAccountNumber().length());
        assertEquals(4, clientInformation.getSubAccountNumber().length());

    }

    @Test
    public void getProductInformationColumnData() {
        ProductInformationBean productInformationBean = CSVColumnDataWriterUtil.getProductInformationColumnData(testRecordData);
        assertNotNull(productInformationBean);
        assertEquals(2, productInformationBean.getProductGroupCode().length());
        assertEquals(4, productInformationBean.getExchangeCode().length());
        assertEquals(6, productInformationBean.getSymbol().length());
        assertEquals(8, productInformationBean.getExpirationDate().length());
    }


    @Test
    public void getTotalTransactionCostColumnData() {
        TotalTransactionCostBean totalTransactionCostBean = CSVColumnDataWriterUtil.getTotalTransactionCostColumnData(testRecordData);
        assertNotNull(totalTransactionCostBean);
        assertEquals(10, totalTransactionCostBean.getQuantityLong().length());
        assertEquals(10, totalTransactionCostBean.getQuantityShort().length());
    }
}