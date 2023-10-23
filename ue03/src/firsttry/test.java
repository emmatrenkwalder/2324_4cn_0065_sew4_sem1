package firsttry;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.io.StringReader;

/**
 * Testfälle für die CSVReader-Klasse.
 * Autor: Emma Trenkwalder
 * Klasse: 4CN
 */


import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import java.io.IOException;
import java.io.StringReader;

 class CSVReaderTest {

    @Test
    public void testSkipInitialSpace() throws IOException {
        String inputLine = "  Emma,  17, NÖ";
        CSVReader csvReader = new CSVReader(inputLine);

        String[] row = csvReader.split(inputLine);
        String[] expectedRow = { "lio", "17", "fünfhaus" };

        assertArrayEquals(expectedRow, row);
    }
}


