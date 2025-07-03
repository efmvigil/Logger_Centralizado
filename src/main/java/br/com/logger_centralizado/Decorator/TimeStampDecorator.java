package br.com.logger_centralizado.Decorator;

import br.com.logger_centralizado.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStampDecorator extends LogDecorator {

    private String timeStamp;

    public TimeStampDecorator(Log log) {
        super(log);
        this.timeStamp = getFormattedDateTime();
    }

    @Override
    public String getMessage() {
        return "[" + getTimeStamp() + "] " + super.getMessage();
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    private String getFormattedDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }
}
