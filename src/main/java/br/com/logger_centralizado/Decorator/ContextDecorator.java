package br.com.logger_centralizado.Decorator;

import br.com.logger_centralizado.Log;

import java.util.Map;
import java.util.stream.Collectors;

public class ContextDecorator extends LogDecorator{

    private final Map<String, String> context;

    public ContextDecorator(Log log, Map<String, String> contextData) {
        super(log);
        this.context = contextData;
    }

    protected Map<String, String> getContext() {
        return context;
    }

    @Override
    public String getMessage() {
        return "[context: " + formatContext(getContext()) + "]" + super.getMessage();
    }

    private String formatContext(Map<String, String> contextData) {
        if (contextData == null || contextData.isEmpty()) {
            return "";
        }

        return contextData.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", "));
    }
}
