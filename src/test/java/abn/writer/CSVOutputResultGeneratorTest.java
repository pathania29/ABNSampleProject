package abn.writer;

import abn.pojo.CsvOuputMappingBean;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CSVOutputResultGeneratorTest {

    private static final String testOutputRecordsFileName = "testOutput.csv";
    private static final String errorRecordsFileName = "ErrorRecordstest.txt";
    private static final List<CsvOuputMappingBean> outputTestDataList = new ArrayList<>();
    private final List<String> errorRecordsTestDataList = Arrays.asList(
            "315CL  123400020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012260     687950000092550000000             O",
            "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001");

    @BeforeClass
    public static void setTestData() {
        String clientInformation = "clientType=CL  ,clientNumber=4321,accountNumber=0002,subAccountNumber=0001";
        String productInformation = "exchangeCode='SGX ,productGroupCode='FU,symbol='NK    ,expirationDate='20100910";
        String totalTransactionAmount = "quantityLong=0000000001,quantityShort=0000000000,totalQuantity=1";
        CsvOuputMappingBean bean = new CsvOuputMappingBean(clientInformation, productInformation, totalTransactionAmount);
        outputTestDataList.add(bean);
    }


    @Test
    public void generateOutputCsv() {
        CSVOutputResultGenerator.generateOutputCsv(testOutputRecordsFileName, outputTestDataList);
        File testOutputFile = new File(testOutputRecordsFileName);
        assertTrue(testOutputFile.exists());
    }

    @Test
    public void createErrorRecordsFile() {
        CSVOutputResultGenerator.createErrorRecordsFile(errorRecordsTestDataList, errorRecordsFileName);
        File errorRecordsFile = new File(errorRecordsFileName);
        assertTrue(errorRecordsFile.exists());
    }

    @AfterClass
    public static void cleanUp() {
        File testOutputFile = new File(testOutputRecordsFileName);
        if (testOutputFile.exists()) {
            testOutputFile.delete();
        }
        File errorRecordsFile = new File(errorRecordsFileName);
        if (errorRecordsFile.exists()) {
            errorRecordsFile.delete();
        }
    }
}