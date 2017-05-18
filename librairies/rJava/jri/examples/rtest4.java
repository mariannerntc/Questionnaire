import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;
import java.util.logging.Logger;

public class Runner {

    private static Logger log = Logger.getLogger("Runner");

    static class LoggingConsole implements RMainLoopCallbacks {
        private Logger log;

        LoggingConsole(Logger log) {
            this.log = log;
        }

        public void rWriteConsole(Rengine re, String text, int oType) {
            log.info(String.format("rWriteConsole: %s", text));
        }

        public void rBusy(Rengine re, int which) {
            log.info(String.format("rBusy: %s", which));
        }

        public void rShowMessage(Rengine re, String message) {
            log.info(String.format("rShowMessage: %s",  message));
        }

        public String rReadConsole(Rengine re, String prompt, int addToHistory) {
            return null;
        }

        public String rChooseFile(Rengine re, int newFile) {
            return null;
        }

        public void rFlushConsole(Rengine re) {
        }

        public void rLoadHistory(Rengine re, String filename) {
        }

        public void rSaveHistory(Rengine re, String filename) {
        }
    }

    Rengine engine = new Rengine(new String[] {"--no-save"}, false, new LoggingConsole(log));
    ...
    // Use the engine somewhere to evaluate a R method and see the output
    engine.eval(rScriptSourceFile);
}
