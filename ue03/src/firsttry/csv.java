package firsttry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CSVReader {
    private String line;
    private char delimiter;
    private char quote;

    public CSVReader(String line) {
        this(line, ',', '"');
    }

    public CSVReader(String line, char delimiter, char quote) {
        this.line = line;
        this.delimiter = delimiter;
        this.quote = quote;
    }

    public String[] split() throws IOException {
        List<String> result = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean insideQuotedField = false;

        for (char c : line.toCharArray()) {
            if (c == quote) {
                insideQuotedField = !insideQuotedField;
            } else if (c == delimiter && !insideQuotedField) {
                result.add(field.toString());
                field.setLength(0);
            } else if (c == delimiter && insideQuotedField) {
                field.append(c);
            } else {
                field.append(c);
            }
        }

        result.add(field.toString());
        if (insideQuotedField) {
            throw new IOException("Unmatched quote in CSV data.");
        }

        for (int i = 0; i < result.size(); i++) {
            String fieldData = result.get(i);
            result.set(i, fieldData.replace("" + quote + quote, "" + quote));
        }

        return result.toArray(new String[0]);
    }
}
