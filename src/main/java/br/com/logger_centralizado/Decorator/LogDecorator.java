package br.com.logger_centralizado.Decorator;

import br.com.logger_centralizado.Log;

public abstract class LogDecorator implements Log {
    protected Log inner;

    public LogDecorator(Log log) {
        this.inner = log;
    }

    @Override
    public String getMessage() {
        return inner.getMessage();
    }
}
