package secondtry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Diese Klasse ermöglicht das Zählen der Wörter in einer HTML-Datei unter Verwendung einer Zustandsmaschine.
 * Autor: Emma Trenkwalder
 * Klasse: 4cn
 */
class WordCount {

    public static void main(String[] args) throws IOException {
        // Druckt die Anzahl der Wörter in einer HTML-Datei
        System.out.println(new WordCount().count(Files.readString(Path.of("C:/Users/emmat/OneDrive - HTL Wien 3 Rennweg/SJ2024/SEW/SEW4/sew4_sem1j/ue02/src/firsttry/crsto12.html"))));
    }

    /**
     * Enums für die Zustände der Zustandsmaschine.
     */
    enum State {
        NOWORD {  // Zustand -> KEIN WORT
            @Override
            State handleChar(char c, WordCount context) {
                if (Character.isLetter(c)) {  // Wenn c ein Buchstabe ist
                    context.counter++;  // Wortanzahl erhöhen
                    return INWORD; // Übergang in den INWORD-Zustand
                } else if (c == '<') {
                    return INTAG; // Übergang in den INTAG-Zustand, wenn ein offenes HTML-Tag gefunden wird
                }
                return this;
            }
        },
        INWORD {
            @Override
            State handleChar(char c, WordCount context) {
                // Übergang zum INTAG-Zustand, wenn ein offenes HTML-Tag gefunden wird
                if (c == '<') return INTAG;

                // Übergang zum NOWORD-Zustand, wenn ein nicht-buchstabiges Zeichen gefunden wird
                if (!Character.isLetter(c))
                    return NOWORD;
                return this;
            }
        },
        INTAG {
            @Override
            State handleChar(char c, WordCount context) {
                // Übergang in den NOWORD-Zustand, wenn ein schließendes HTML-Tag gefunden wird
                if (c == '>') return NOWORD;
                // Übergang in den INSTRING-Zustand, wenn ein doppeltes Anführungszeichen gefunden wird
                if (c == '\"') return INSTRING;
                return this;
            }
        },
        INSTRING {
            @Override
            State handleChar(char c, WordCount context) {
                // Übergang in den INTAG-Zustand, wenn ein schließendes doppeltes Anführungszeichen gefunden wird
                if (c == '\"') return INTAG;
                // Übergang in den ESCAPED-Zustand, wenn ein Backslash gefunden wird
                if (c == '\\') return ESCAPED;
                return this;
            }
        },
        ESCAPED {
            @Override
            State handleChar(char c, WordCount context) {
                // Zurück zum INSTRING-Zustand
                return INSTRING;
            }
        };

        /**
         * Diese Methode verarbeitet das aktuelle Zeichen basierend auf dem Zustand.
         *
         * @param c       Das aktuelle Zeichen
         * @param context Eine Instanz von WordCount
         * @return Der neue Zustand
         */
        abstract State handleChar(char c, WordCount context);
    }

    // Zählervariable für die Wortanzahl
    int counter;

    /**
     * Diese Methode durchläuft den Text und zählt die Wörter.
     *
     * @param text Der Eingabetext
     * @return Die Anzahl der Wörter
     */
    public int count(String text) {
        // Startzustand: NOWORD
        State state = State.NOWORD;
        // Initialisieren des Zählers
        counter = 0;
        for (char c : text.toCharArray()) {
            // Aktualisieren des Zustands und des Zählers basierend auf dem aktuellen Zeichen
            state = state.handleChar(c, this);
        }

        // Die endgültige Wortanzahl zurückgeben
        return counter;
    }

}


/*

@startuml
[*] --> NOWORD : Start

NOWORD --> INWORD : Wenn Buchstabe gefunden
NOWORD --> INTAG : Wenn '<' gefunden
NOWORD --> NOWORD : Sonst

INWORD --> INTAG : Wenn '<' gefunden
INWORD --> NOWORD : Wenn kein Buchstabe gefunden
INWORD --> INWORD : Sonst

INTAG --> NOWORD : Wenn '>' gefunden
INTAG --> INSTRING : Wenn '"' gefunden
INTAG --> INTAG : Sonst

INSTRING --> INTAG : Wenn '"' gefunden
INSTRING --> ESCAPED : Wenn '\\' gefunden
INSTRING --> INSTRING : Sonst

ESCAPED --> INSTRING : Zurück zu INSTRING
@enduml

 */
