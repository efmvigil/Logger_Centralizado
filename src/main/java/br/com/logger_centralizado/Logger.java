package br.com.logger_centralizado;

import br.com.logger_centralizado.Observer.LogObserver;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static volatile Logger instance;
    private final List<LogObserver> observers = new ArrayList<>();

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

    public void addObserver(LogObserver observer) {
        observers.add(observer);
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
