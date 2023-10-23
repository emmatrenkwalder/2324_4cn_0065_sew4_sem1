package firsttry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CSVReader {
    private BufferedReader reader;
    private char delimiter;
    private char quote;

    private enum CSVState {
        NORMAL, IN_QUOTE
    }

    public CSVReader(String filename) throws IOException {
        this(filename, ',', '"');
    }

    public CSVReader(String filename, char delimiter, char quote) throws IOException {
        this.reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.quote = quote;
    }

    public String[] split(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        CSVState state = CSVState.NORMAL;

        for (char c : line.toCharArray()) {
            if (state == CSVState.NORMAL) {
                if (c == delimiter) {
                    result.add(field.toString().trim()); // Führende Leerzeichen entfernen
                    field.setLength(0);
                } else if (c == quote) {
                    state = CSVState.IN_QUOTE;
                } else {
                    field.append(c);
                }
            } else if (state == CSVState.IN_QUOTE) {
                if (c == quote) {
                    state = CSVState.NORMAL;
                } else {
                    field.append(c);
                }
            }
        }

        result.add(field.toString().trim()); // Führende Leerzeichen entfernen
        return result.toArray(new String[0]);
    }

    public String[] readNextLine() throws IOException {
        String line = reader.readLine();
        if (line != null) {
            return split(line);
        }
        return null;
    }

    public void close() throws IOException {
        reader.close();
    }
}
