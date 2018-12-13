package credere.prova.backend.util;

import java.util.List;

import static java.lang.String.join;

/**
 * It puts the words in a sentence formatted as: "word1, word2, word3 e word4."
 */
public interface Sentence {

    static String format(List<String> words) {
        int indexOfFirst = 0;
        if (words.size() > 1) {
            int indexOfLast = words.size() - 1;

            var everythingExceptLast = join(", ", words.subList(indexOfFirst, indexOfLast));
            var last = words.get(indexOfLast);

            return String.format("%s e %s", everythingExceptLast, last);
        }
        if (words.size() == 1) {
            return words.get(indexOfFirst);
        }
        return "";
    }

}
