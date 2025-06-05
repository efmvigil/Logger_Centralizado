package br.com.logger_centralizado.Decorator;

import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.LogProperties;

public class JsonFormatDecorator extends LogDecorator{

    private final LogProperties propertiesObject;

    public JsonFormatDecorator(Log log) {
        super(log);
        this.propertiesObject = buildObject(log);
    }

    @Override
    public String getMessage() {
        return getTimeStamp() + super.getMessage();
    }

    private LogProperties buildObject(Log log) {
        LogProperties properties = new LogProperties();
        if (log instanceof TimeStampDecorator timeStampDecorator) properties.setTimeStamp(timeStampDecorator.getTimeStamp());
        if (log instanceof LevelDecorator levelDecorator) properties.setLevel(levelDecorator.getLevel());
        if (log instanceof ContextDecorator contextDecorator) properties.setContext(contextDecorator.getContext());

        return properties;
    }
}
