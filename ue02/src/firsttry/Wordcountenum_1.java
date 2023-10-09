package firsttry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Zustandsautomaten um die Wörter im Text zu zählen.
 *
 * @author Emma Trenkwalder
 * @version 1.0
 */
public class Wordcountenum_1 {


    /**
     * Hauptmethode des Programms->die den Text aus einer Datei liest und die Anzahl der Wörter zählt.
     *
     * @param args Die Kommandozeilenargumente (nicht verwendet)
     * @throws IOException Wenn ein Fehler beim Lesen der Datei auftritt
     */
    public static void main(String[] args) throws IOException {
        System.out.println(new Wordcountenum_1().count(Files.readString(Path.of("C:/Users/emmat/OneDrive - HTL Wien 3 Rennweg/SJ2024/SEW/SEW4/sew4_sem1j/ue02/src/firsttry/crsto12.html"))));
    }

    /**
     * Ein Enumerator, der die verschiedenen Zustände des Zustandsautomaten darstellt.
     */
    enum State {
        NOWORD, INWORD, INTAG, INSTRING, ESCAPED
    }

    int counter;

    /**
     * Zählt die Anzahl der Wörter im gegebenen Text.
     *
     * @param text Der Text, in dem die Wörter gezählt werden sollen
     * @return Die Anzahl der Wörter im Text
     */
    public int count(String text) {
        State state = State.NOWORD;
        counter = 0;

        for (char c : text.toCharArray()) {
            switch (state) {
                case NOWORD:
                    if (Character.isLetter(c)) {
                        counter++;
                        state = State.INWORD;
                    } else if (c == '<') {
                        state = State.INTAG;
                    }
                    break;
                case INWORD:
                    if (c == '<') {
                        state = State.INTAG;
                    } else if (!Character.isLetter(c)) {
                        state = State.NOWORD;
                    }
                    break;
                case INTAG:
                    if (c == '>') {
                        state = State.NOWORD;
                    } else if (c == '\"') {
                        state = State.INSTRING;
                    }
                    break;
                case INSTRING:
                    if (c == '\"') {
                        state = State.INTAG;
                    } else if (c == '\\') {
                        state = State.ESCAPED;
                    }
                    break;
                case ESCAPED:
                    state = State.INSTRING;
                    break;
            }
        }

        return counter;
    }
}
