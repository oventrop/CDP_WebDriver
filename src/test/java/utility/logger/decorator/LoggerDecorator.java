package utility.logger.decorator;

import utility.logger.Logger;

class LoggerDecorator implements Logger {

    Logger logger;

    public LoggerDecorator(Logger logger) {
        this.logger = logger;
    }

    public void log(String logMessage) {
        /*
         * Default implementation to be overriden by subclasses.
         */
        logger.log(logMessage);
    }
}