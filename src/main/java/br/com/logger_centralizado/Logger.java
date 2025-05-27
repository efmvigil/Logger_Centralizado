package br.com.logger_centralizado;

public class Logger {

    private static Logger instance;

    private Logger(){}

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }

        return instance;
    }

    public class Log {
        private String message;

        private Log(String message) {
            this.message = message;
        }
    }

    private Log generateLog(String message) {
        return new Log(message);
    }
}
