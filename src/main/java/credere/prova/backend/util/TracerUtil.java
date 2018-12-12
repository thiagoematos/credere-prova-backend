package credere.prova.backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static credere.prova.backend.util.Sentence.format;

public class TracerUtil {

    private Logger logger = LoggerFactory.getLogger("Trace");

    private List<String> trace;

    private TracerUtil(List<String> trace) {
        this.trace = trace;
    }

    public static TracerUtil with(List<String> trace) {
        return new TracerUtil(trace);
    }

    public void log() {
        var message = format(trace);
        logger.info(String.format("a sonda %s", message));
    }

}
