package firsttry;

import org.junit.Test;

import static org.junit.Assert.*;


class CSVReaderTest {

    @Test
    public void testCSVReaderWithDefaultDelimiter() {
        CSVReader csvReader = new CSVReader("lionel,17,1150");
        String[] data = csvReader.split();

        assertEquals("lionel", data[0]);
        assertEquals("17", data[1]);
        assertEquals("1150", data[2]);
    }

    @Test
    public void testCSVReaderWithCustomDelimiter() {
        CSVReader csvReader = new CSVReader("emma;17;schwadorf", ';');
        String[] data = csvReader.split();

        assertEquals("emma", data[0]);
        assertEquals("17", data[1]);
        assertEquals("schwadorf", data[2]);
    }
}
