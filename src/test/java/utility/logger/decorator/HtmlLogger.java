package utility.logger.decorator;

import utility.logger.Logger;

public class HtmlLogger extends LoggerDecorator {

    public HtmlLogger(Logger logger) {
        super(logger);
    }

    public void log(String logMessage) {
        /*
         * Added functionality
         */
        logMessage = makeHTML(logMessage);
        logger.log(logMessage);
    }

    private String makeHTML(String logMessage) {
        /*
         * Make it into an HTML document.
         */
        logMessage = "<HTML><BODY>" + "<b>" + logMessage + "</b>"
                + "</BODY></HTML>";

        return logMessage;
    }
}