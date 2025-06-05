package br.com.logger_centralizado.Decorator;

import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.LogLevel;

public class LevelDecorator extends LogDecorator{

    private final LogLevel level;

    public LevelDecorator(Log log, LogLevel level) {
        super(log);
        this.level = level;
    }

    protected LogLevel getLevel() {
        return level;
    }

    @Override
    public String getMessage() {
        return "[" + level.name() + "] " + super.getMessage();
    }
}
