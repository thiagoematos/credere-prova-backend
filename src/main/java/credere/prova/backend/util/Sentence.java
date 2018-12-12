package credere.prova.backend.util;

import java.util.List;

import static java.lang.String.join;

/**
 * It puts the words in a sentence formatted as: "word1, word2, word3 e word4."
 */
public interface Sentence {

    static String format(List<String> str) {
        int indexOfFirst = 0;
        if (str.size() > 1) {
            int indexOfLast = str.size() - 1;

            var everythingExceptLast = join(", ", str.subList(indexOfFirst, indexOfLast));
            var last = str.get(indexOfLast);

            return String.format("%s e %s", everythingExceptLast, last);
        }
        if (str.size() == 1) {
            return str.get(indexOfFirst);
        }
        return "";
    }

}
