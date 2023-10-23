package firsttry;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
/**
 * Testfälle für die CSVReader-Klasse.
 * Autor: Emma Trenkwalder
 * Klasse: 4CN
 */

 class CSVReaderTest {

    @Test
    public void testCSVReaderWithEscapedQuotes() throws IOException {
        CSVReader csvReader = new CSVReader("\"Emma\",\"17\",\"in \"\"Schwadorf\"");
        String[] data = csvReader.split();

        assertEquals("Emma", data[0]);
        assertEquals("17", data[1]);
        assertEquals("in \"Schwadorf", data[2]);
    }

    @Test
    public void testCSVReaderWithCustomDelimiterAndEscapedQuotes() throws IOException {
        CSVReader csvReader = new CSVReader("'lio' | '17' | '1150, wien'", '|', '\'');
        String[] data = csvReader.split();

        assertEquals("lio", data[0]);
        assertEquals("17", data[1]);
        assertEquals("1150, wien", data[2]);
    }
}
