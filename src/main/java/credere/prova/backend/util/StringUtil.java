package credere.prova.backend.util;

public interface StringUtil {

    // https://bytenota.com/java-replace-last-occurrence-of-a-string/
    static String replaceLast(String find, String replace, String string) {
        var lastIndex = string.lastIndexOf(find);

        if (lastIndex == -1) {
            return string;
        }

        var beginString = string.substring(0, lastIndex);
        var endString = string.substring(lastIndex + find.length());

        return beginString + replace + endString;
    }

}
