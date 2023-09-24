package firsttry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
    public static void main(String[] args) {

    }
    public static String removeHtmlTags(String text) {
        StringBuilder result = new StringBuilder();
        boolean insideTag = false;

        for (char c : text.toCharArray()) {
            if (c == '<') {
                insideTag = true;
            } else if (c == '>') {
                insideTag = false;
            } else if (!insideTag) {
                result.append(c);
            }

        }

        System.out.println(result.toString());
        return result.toString();
    }

    public static int count(String txt) {
        // Entfernen Sie HTML-Tags
        String textWithoutTags = removeHtmlTags(txt);

        // Verwenden Sie regulären Ausdruck, um Wörter zu zählen
        Pattern pattern = Pattern.compile("\\b[A-Za-z]+\\b");
        Matcher matcher = pattern.matcher(textWithoutTags);

        int wordCount = 0;
        while (matcher.find()) {
            wordCount++;
        }

        return wordCount;
    }

}
