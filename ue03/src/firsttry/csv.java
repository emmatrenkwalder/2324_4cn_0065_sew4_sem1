package firsttry;

import java.util.LinkedList;
import java.util.List;

/**
 * CSVReader
 *
 * @author Emma Trenkwalder 4CN
 */
class CSVReader {

    /**
     * Jetziges Wort welches gebaut wird
     */
    public StringBuilder current = new StringBuilder();

    /**
     * Wörter
     */
    public List<String> words = new LinkedList<>();

    /**
     * Delimiter
     */
    private final char delimiter;

    /**
     * Doublequote
     */
    private final char doublequote;

    /**
     * skipinitialspace
     */
    private final boolean skipinitialspace;

    /**
     * Konstruktor
     *
     * @param delimiter        Delimiter
     * @param doublequote      Doublequote
     * @param skipinitialspace Skipinitialspace
     */
    public CSVReader(char delimiter, char doublequote, boolean skipinitialspace) {
        this.delimiter = delimiter;
        this.doublequote = doublequote;
        this.skipinitialspace = skipinitialspace;
    }

    /**
     * Konstruktor
     */
    public CSVReader() {
        this(',', '\"', false);
    }

    /**
     * Statemachine
     */
    enum State {
        /**
         * Nicht in einer Zelle
         */
        NOCELL {
            @Override
            State handleChar(char c, CSVReader csv) {
                if (Character.isWhitespace(c) && csv.skipinitialspace) return this;
                if (c == csv.doublequote) return IN_QUOTE;
                if (c == '\\') return ESCAPED;
                csv.current.append(c);
                return INCELL;

            }
        },
        /**
         * In einer Zelle
         */
        INCELL {
            @Override
            State handleChar(char c, CSVReader csv) {
                if (c == csv.delimiter) {
                    csv.words.add(csv.current.toString());
                    csv.current.setLength(0);
                    return NOCELL;
                }
                if (c == csv.doublequote) return ESCAPED;
                csv.current.append(c);
                return this;
            }
        },
        /**
         * In Doublequote Zelle
         */
        IN_QUOTE {
            @Override
            State handleChar(char c, CSVReader csv) {
                if (c == '\\') return ESCAPED;
                if (c == csv.doublequote) return NOCELL;
                csv.current.append(c);
                return this;
            }
        },
        /**
         * Escape Character
         */
        ESCAPED {
            @Override
            State handleChar(char c, CSVReader csv) {
                if (Character.isLetter(c)) csv.current.append("\"");
                csv.current.append(c);
                return IN_QUOTE;
            }
        };

        /**
         * handle Method
         *
         * @param c   Zeichen
         * @param csv Context
         * @return State
         */
        abstract State handleChar(char c, CSVReader csv);
    }

    /**
     * Split Methode
     *
     * @param text Zeile
     * @return Wörter
     */
    public List<String> split(String text) {
        words.clear();
        current.setLength(0);
        State state = State.NOCELL;
        for (char c : text.toCharArray()) {
            state = state.handleChar(c, this);
        }
        words.add(current.toString());
//        System.out.println(words);
        return words;
    }

    /**
     * toString
     *
     * @return String
     */
    @Override
    public String toString() {
        return words.toString();
    }
}
