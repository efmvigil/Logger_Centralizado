package br.com.logger_centralizado;

public class Logger {

    private static volatile Logger instance;

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

    public class LogImpl implements Log {

        private String message;

        private LogImpl(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public Log generateLog(String message) {
        return new LogImpl(message);
    }
}
