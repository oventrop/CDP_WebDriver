package utility.logger.logger_impl;

import utility.logger.Logger;

public class ConsoleLogger implements Logger {

    public void log(String msg) {
        System.out.println(msg);
    }
}