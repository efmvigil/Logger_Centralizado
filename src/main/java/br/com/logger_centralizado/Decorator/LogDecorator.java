package br.com.logger_centralizado.Decorator;

import br.com.logger_centralizado.Log;

public abstract class LogDecorator implements Log {
    private Log wrappedLog;

    public LogDecorator(Log log) {
        this.wrappedLog = log;
    }

    @Override
    public String getMessage() {
        return wrappedLog.getMessage();
    }
}
