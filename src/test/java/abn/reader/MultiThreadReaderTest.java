package abn.reader;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class MultiThreadReaderTest {

    private InputStream is;

    private final MultiThreadReader multiThreadReader = new MultiThreadReader();

    @Before
    public void init() {
        is = this.getClass().getResourceAsStream("/input/test.txt");
    }

    @Test
    public void readFile() throws IOException {
        assertNotNull(is);
        assertThat( multiThreadReader.readInputFile(is), not(IsEmptyCollection.empty()));
    }

    @After
    public void cleanUp() throws IOException {
        is.close();
    }

}