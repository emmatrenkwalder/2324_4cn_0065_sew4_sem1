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
        CSVReader cr = new CSVReader(',', '\"', true);
        // B.4
        assertEquals("[data, emma, tr\"n+k]", cr.split("data,  emma,     \"tr\\\"n+k\"").toString());
        assertEquals("[data , emma , tr\"n+k]", cr.split("data ,    emma ,     \"tr\\\"n+k\"").toString());
    }
}


