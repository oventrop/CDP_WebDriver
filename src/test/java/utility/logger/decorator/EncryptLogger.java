package utility.logger.decorator;

import utility.logger.Logger;

public class EncryptLogger extends LoggerDecorator {

    public EncryptLogger(Logger logger) {
        super(logger);
    }

    public void log(String logMessage) {
        /*
         * Added functionality
         */
        logMessage = encrypt(logMessage);

        logger.log(logMessage);
    }

    private String encrypt(String logMessage) {
        /*
         * Apply simple encryption by Transposition - shift all characters by
         * one position.
         */
        logMessage = logMessage.substring(logMessage.length() - 1)
                + logMessage.substring(0, logMessage.length() - 1);

        return logMessage;
    }

}
