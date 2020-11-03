package abn.reader;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class SingleThreadReaderTest {

    private InputStream is;

    private final SingleThreadReader singleThreadReader = new SingleThreadReader();

    @Before
    public void init() {
        is = this.getClass().getResourceAsStream("/input/test.txt");
    }

    @Test
    public void readFile() throws IOException {
        assertNotNull(is);
        singleThreadReader.readFileUsingInputStream(is);
        assertThat(singleThreadReader.getMappingBeanList(), not(IsEmptyCollection.empty()));
    }

    @After
    public void cleanUp() throws IOException {
        is.close();
    }
}