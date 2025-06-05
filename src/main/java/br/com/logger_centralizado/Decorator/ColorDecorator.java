package br.com.logger_centralizado.Decorator;

import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.LogLevel;

public class ColorDecorator extends LogDecorator{

    public ColorDecorator(Log log) {
        super(log);
    }

    @Override
    public String getMessage() {
        String resetColorCode = "\u001B[0m";
        LogLevel level = extractInnerLevel();

        return getColorCode(level) + super.getMessage() + resetColorCode;
    }

    private LogLevel extractInnerLevel() {
        if (super.inner instanceof LevelDecorator levelDecorator) {
            return levelDecorator.getLevel();
        } else return null;
    }

    private String getColorCode(LogLevel level) {
        if (level == null) {
           return "\u001B[90m";
        }
        return switch (level) {
            case TRACE -> "\u001B[37m";
            case DEBUG -> "\u001B[36m";
            case INFO  -> "\u001B[32m";
            case WARN  -> "\u001B[33m";
            case ERROR -> "\u001B[31m";
            case FATAL -> "\u001B[35m";
        };
    }
}
