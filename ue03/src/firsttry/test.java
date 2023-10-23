package firsttry;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;

class CSVReaderTest {

    @Test
    public void testCSVReaderWithQuotes() throws IOException {
        CSVReader csvReader = new CSVReader("\"John\",\"30\",\"New York\"");
        String[] data = csvReader.split();

        assertEquals("John", data[0]);
        assertEquals("30", data[1]);
        assertEquals("New York", data[2]);
    }

    @Test
    public void testCSVReaderWithCustomDelimiterAndQuotes() throws IOException {
        CSVReader csvReader = new CSVReader("'Emma' | '17' | 'schwadorf'", '|', '\'');
        String[] data = csvReader.split();

        assertEquals("emma", data[0]);
        assertEquals("17", data[1]);
        assertEquals("schwadorf", data[2]);
    }

    @Test(expected = IOException.class)
    public void testCSVReaderWithUnmatchedQuote() throws IOException {
        CSVReader csvReader = new CSVReader("\"lio\",\"700\",\"fünfhaus");
        csvReader.split(); // Should throw an IOException
    }
}
