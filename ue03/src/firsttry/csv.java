package firsttry;

import java.io.IOException;

class CSVReader {
    private String line;
    private char delimiter;

    public CSVReader(String line) {
        this(line, ',');
    }

    public CSVReader(String line, char delimiter) {
        this.line = line;
        this.delimiter = delimiter;
    }

    public String[] split() {
        return line.split(String.valueOf(delimiter));
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader("emma,17,verzweifelt");
        String[] data = csvReader.split();

        for (String field : data) {
            System.out.println(field);
        }
    }
}
