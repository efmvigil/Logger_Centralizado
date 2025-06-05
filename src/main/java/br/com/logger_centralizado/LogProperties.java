package br.com.logger_centralizado;

import java.util.Map;

public class LogProperties {

    private String timeStamp;
    private LogLevel level;
    private Map<String, String> context;

    public LogProperties() {
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }
}
