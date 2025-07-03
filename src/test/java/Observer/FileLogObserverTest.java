package Observer;

import br.com.logger_centralizado.Decorator.LevelDecorator;
import br.com.logger_centralizado.Decorator.TimeStampDecorator;
import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.LogLevel;
import br.com.logger_centralizado.Logger;
import br.com.logger_centralizado.Observer.FileLogObserver;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;

public class FileLogObserverTest {

    @Test
    public void deveSalvarMensagemComTimestampELevel() throws IOException {
        String path = "test-log.txt";
        Log log = new TimeStampDecorator(
                new LevelDecorator(
                        Logger.getInstance().generateLog("Erro de disco"),
                        LogLevel.ERROR
                )
        );

        FileLogObserver observer = new FileLogObserver(path);
        observer.update(log);

        String content = Files.readString(Path.of(path));
        assertTrue(content.contains("[ERROR]"));
        assertTrue(content.contains("Erro de disco"));

        Files.deleteIfExists(Path.of(path)); // limpa após o teste
    }
}
