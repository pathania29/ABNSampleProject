package abn.reader;

import abn.exceptions.AbnClientException;
import abn.pojo.CsvOuputMappingBean;
import abn.tasks.Task;
import abn.writer.CSVOutputResultGenerator;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Multi Thread Reader to Read large files
 * Reference Document: System A File Specification.pdf
 * PROCESSED FUTURE MOVEMENT
 * This class reads input.txt and generates data in an list which is then used to
 * build Output.csv.
 */
public class MultiThreadReader {

    private static final Logger log = Logger.getLogger(MultiThreadReader.class);

    private final List<String> errorRecords = new ArrayList<>();

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    /**
     * Make a list of CsvOuputMappingBean which is passed to CSV Writer
     * @param stream
     * @return
     */
    public List<CsvOuputMappingBean> readInputFile(InputStream stream) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
                List<Task> taskList = getTaskLists(bufferedReader);
                log.info(" Number of records read from input file " + taskList.size());
                return getResultList(taskList);
        } catch (IOException | ExecutionException | InterruptedException e) {
            throw new AbnClientException(e.getMessage(), e);
        }
    }

    /**
     * Make a list of Task which is passed to ThreadExecutor
     * @param bufferedReader
     * @return
     * @throws IOException
     */
    private List<Task> getTaskLists(BufferedReader bufferedReader) throws IOException {
        List<Task> taskList = new ArrayList<>();
        String record;
        while ((record = bufferedReader.readLine()) != null) {
            Task task = new Task(record);
            taskList.add(task);
        }
        return taskList;
    }

    /**
     * Execute all tasks and get reference to Future objects
     * @param taskList
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private List<CsvOuputMappingBean> getResultList(List<Task> taskList) throws ExecutionException, InterruptedException {
        //Execute all tasks and get reference to Future objects
        List<Future<CsvOuputMappingBean>> resultList = getFutures(taskList);
        return getCsvOuputMappingBeans(resultList);
    }

    /**
     * Execute all tasks and get reference to Future objects
     * @param taskList
     * @return
     */
    private List<Future<CsvOuputMappingBean>> getFutures(List<Task> taskList) throws InterruptedException {
        List<Future<CsvOuputMappingBean>> resultList = executor.invokeAll(taskList);
        executor.shutdown();
        return resultList;
    }

    /**
     *
     * @param resultList
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private List<CsvOuputMappingBean> getCsvOuputMappingBeans(List<Future<CsvOuputMappingBean>> resultList) throws InterruptedException, ExecutionException {
        List<CsvOuputMappingBean> clientInformationBeanList = new ArrayList<>();
        for (Future<CsvOuputMappingBean> future : resultList) {
            CsvOuputMappingBean csvOuputMappingBean = future.get();
            if (!csvOuputMappingBean.getClientInformation().startsWith("Error:")) {
                clientInformationBeanList.add(future.get());
            } else {
                errorRecords.add(csvOuputMappingBean.getClientInformation());
            }
        }

        log.info(" Number of rows sent to CSV writer " + clientInformationBeanList.size());
        return clientInformationBeanList;
    }

    /**
     * Method to create Error records .txt file
     */
    public void createErrorRecordsFile(){
        log.info(" Number of error records " + getErrorRecords().size());
        CSVOutputResultGenerator.createErrorRecordsFile(getErrorRecords(), "MultiThreadErrorRecords.txt");

    }

    private List<String> getErrorRecords() {
        return errorRecords;
    }
}
