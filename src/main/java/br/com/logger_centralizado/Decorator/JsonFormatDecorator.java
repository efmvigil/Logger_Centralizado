package br.com.logger_centralizado.Decorator;

import br.com.logger_centralizado.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonFormatDecorator extends LogDecorator{
    public JsonFormatDecorator(Log log) {
        super(log);
    }

    @Override
    public String getMessage() {
        return getTimeStamp() + super.getMessage();
    }

    private String getTimeStamp() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        return "[" + formattedDateTime + "] ";
    }
}
