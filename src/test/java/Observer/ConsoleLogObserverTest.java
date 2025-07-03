package Observer;

import br.com.logger_centralizado.Decorator.ColorDecorator;
import br.com.logger_centralizado.Decorator.LevelDecorator;
import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.LogLevel;
import br.com.logger_centralizado.Logger;
import br.com.logger_centralizado.Observer.ConsoleLogObserver;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleLogObserverTest {

    @Test
    public void deveImprimirMensagemNoConsoleCorretamente() {
        // Captura a saída do console
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        try {
            Log baseLog = Logger.getInstance().generateLog("Falha crítica no sistema");
            Log levelLog = new LevelDecorator(baseLog, LogLevel.ERROR);
            Log colorLog = new ColorDecorator(levelLog);

            ConsoleLogObserver observer = new ConsoleLogObserver();
            observer.update(colorLog);

            String printed = output.toString().trim();

            assertTrue(printed.contains("ConsoleLog"));
            assertTrue(printed.contains("[ERROR]"));
            assertTrue(printed.contains("Falha crítica no sistema"));
        } finally {
            System.setOut(originalOut);
        }
    }
}
