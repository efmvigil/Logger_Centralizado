package br.com.logger_centralizado.Observer;

import br.com.logger_centralizado.Log;

public class ConsoleLogObserver implements LogObserver {
    @Override
    public void update(Log log) {
        System.out.println("ConsoleLog: " + log.getMessage());
    }
}
