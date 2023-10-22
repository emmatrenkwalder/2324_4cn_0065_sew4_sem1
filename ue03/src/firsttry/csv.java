package firsttry;
import com.sun.source.tree.BreakTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class csv {

    public static class CSVReader {
        private BufferedReader reader;
        private char delimiter;

        public enum CSVState {
            NORMAL, IN_QUOTE
        }

        public CSVReader(String filename) throws IOException {
            this(filename, ',');
        }

        public CSVReader(String filename, char delimiter) throws IOException {
            this.reader = new BufferedReader(new FileReader(filename));
            this.delimiter = delimiter;
        }

        public String[] split(String input) {
            ArrayList<String> result = new ArrayList<>();
            StringBuilder currentField = new StringBuilder();
            boolean inQuotedField = false;

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                if (c == '"') {
                    if (inQuotedField) {
                        if (i < input.length() - 1 && input.charAt(i + 1) == '"') {
                            currentField.append('"');
                            i++;
                        } else {
                            inQuotedField = false;
                        }
                    } else {
                        inQuotedField = true;
                    }
                } else if (c == delimiter) {
                    if (inQuotedField) {
                        currentField.append(c);
                    } else {
                        result.add(currentField.toString().trim());
                        currentField.setLength(0);
                    }
                } else {
                    currentField.append(c);
                }
            }

            if (inQuotedField) {
                throw new IllegalArgumentException("Missing closing quote.");
            }

            result.add(currentField.toString().trim());

            return result.toArray(new String[0]);
        }

        public String[] readNext() throws IOException {
            String line = reader.readLine();
            if (line == null) {
                return null;
            }
            return split(line);
        }

        public void close() throws IOException {
            reader.close();
        }

        public static void main(String[] args) {
            try (CSVReader csvReader = new CSVReader("example.csv")) {
                String[] row;
                while ((row = csvReader.readNext()) != null) {
                    for (String field : row) {
                        System.out.print(field + " ");
                    }
                    System.out.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}