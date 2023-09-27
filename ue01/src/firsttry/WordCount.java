package firsttry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {


        public static long count(String s) {
            // Ersetze HTML-Tags und alles zwischen ihnen durch Leerzeichen
            s= s.replaceAll("<[^<>]+<[^<>]+>[^<>]+>"," ");

            s = s.replaceAll("(<[^>]+>)|(<[^>]*$)", " ");


            int counter = 0;
            String[] meinArray = s.trim().split("\\W+");

            for (int i = 0; i < meinArray.length; i++) {
                String word = meinArray[i];
                // Überprüfe, ob das Wort nur aus Buchstaben und Zahlen besteht
                if (word.matches("[a-zA-Z0-9]+")) {
                    counter++;
                }
            }

            return counter;
        }

}



        /*public static int count(String txt) {
            // Use a regular expression to match words while ignoring HTML tags
            Pattern pattern = Pattern.compile("(<[^>]+>)|(<[^>]*$)|([A-Za-z]+)");
            Matcher matcher = pattern.matcher(txt);

            int wordCount = 0;
            boolean insideTag = false;

            while (matcher.find()) {
                String match = matcher.group();

                if (match.startsWith("<")) {
                    insideTag = true;
                    System.out.println("test");
                }

                if (!insideTag && !match.startsWith("<")) {
                    System.out.println(match);
                    wordCount++;
                }

                if (match.endsWith(">") ) {
                    insideTag = false;
                }

            }

            return wordCount;
        }
    }*/


//assertEquals(1, WordCount.count(" eins<html"));
//      assertEquals(2, WordCount.count(" eins<img alt=\"<bild>\" > zwei"));
//      assertEquals(2, WordCount.count(" eins<img alt=\"bild>\" > zwei"));
//      assertEquals(2, WordCount.count(" eins<img alt=\"<bild>\" keinwort> zwei"));
//      assertEquals(2, WordCount.count(" eins<img alt=\"<bild>\" src=\"bild.png\" >zwei"));
//      assertEquals(2, WordCount.count(" eins<img alt=\"<bild\" keinwort>zwei"));
//      assertEquals(1, WordCount.count(" eins<img alt=\"<bild\" keinwort"));
//      assertEquals(2, WordCount.count(" eins<img alt=\"<bild\" keinwort> zwei"));
//      assertEquals(1, WordCount.count(" eins<img alt=\"<bild keinwort> keinwort"));
//      assertEquals(2, WordCount.count(" eins<img alt=\"<bild keinwort keinwort\">zwei"));
//      assertEquals(2, WordCount.count(" eins<img alt=\"<bild keinwort< keinwort\">zwei"));
