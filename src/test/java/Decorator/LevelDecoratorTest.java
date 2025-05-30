package Decorator;

import br.com.logger_centralizado.Decorator.LevelDecorator;
import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.LogLevel;
import br.com.logger_centralizado.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevelDecoratorTest {

    @Test
    public void deveRetornarStringComNivelDoLogFormatadoCorretamente() {
        Log log = Logger.getInstance().generateLog("teste");
        LogLevel level = LogLevel.ERROR;
        LevelDecorator decorator = new LevelDecorator(log, level);

        String expectedResult = "[" + level + "] " +  log.getMessage();
        String obtainedResult = decorator.getMessage();

        assertEquals(expectedResult, obtainedResult);
    }
}
