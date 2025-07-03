package br.com.logger_centralizado.Observer;

import br.com.logger_centralizado.Decorator.LevelDecorator;
import br.com.logger_centralizado.Decorator.TimeStampDecorator;
import br.com.logger_centralizado.Log;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogObserver implements LogObserver {
    private final String filePath;

    public FileLogObserver(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void update(Log log) {
        String timeStamp = "", level = "";

        if (log instanceof TimeStampDecorator ts) timeStamp = ts.getTimeStamp();
        if (log instanceof LevelDecorator lvl) level = lvl.getLevel().toString();

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(
                    (timeStamp.isEmpty() ? "" : "[" + timeStamp + "] ") +
                            (level.isEmpty() ? "" : "[" + level + "] ") +
                            log.getMessage() + "\n"
            );
        } catch (IOException e) {
            System.err.println("Erro ao salvar log em arquivo: " + e.getMessage());
        }
    }
}
