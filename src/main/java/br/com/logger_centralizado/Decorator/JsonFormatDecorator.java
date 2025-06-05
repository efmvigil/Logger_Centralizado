package br.com.logger_centralizado.Decorator;

import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.LogProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormatDecorator extends LogDecorator{

    private final LogProperties propertiesObject;

    public JsonFormatDecorator(Log log) {
        super(log);
        LogProperties properties = new LogProperties();
        this.propertiesObject = buildObject(log, properties);
    }

    @Override
    public String getMessage() {
        return toJson(propertiesObject);
    }

    private String toJson(LogProperties properties) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.writeValueAsString(properties);
        } catch (JsonProcessingException e) {
            return "{\"error\": \"Falha ao converter para JSON\"}";
        }
    }

    private LogProperties buildObject(Log log, LogProperties properties) {
        if (log instanceof TimeStampDecorator timeStampDecorator) properties.setTimeStamp(timeStampDecorator.getTimeStamp());
        if (log instanceof LevelDecorator levelDecorator) properties.setLevel(levelDecorator.getLevel());
        if (log instanceof ContextDecorator contextDecorator) properties.setContext(contextDecorator.getContext());

        if (log instanceof LogDecorator decorator) {
            buildObject(decorator.getInner(), properties);
        } else properties.setMessage(log.getMessage());

        return properties;
    }
}
