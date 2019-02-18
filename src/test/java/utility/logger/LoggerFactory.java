package utility.logger;

import utility.logger.logger_impl.ConsoleLogger;
import utility.logger.logger_impl.FileLogger;

public class LoggerFactory {

    public static Logger getLogger(boolean isFileLoggingEnabled) {
        if (isFileLoggingEnabled) {
            return FileLogger.getFileLogger();
        } else {
            return new ConsoleLogger();
        }
    }
}