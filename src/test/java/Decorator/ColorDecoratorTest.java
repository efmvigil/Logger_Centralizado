package Decorator;

import br.com.logger_centralizado.Decorator.ColorDecorator;
import br.com.logger_centralizado.Decorator.LevelDecorator;
import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.LogLevel;
import br.com.logger_centralizado.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColorDecoratorTest {

    @Test
    public void deveRetornarStringComAFormatacaoDeCorCorretaDeAcordoComONivel() {
        Log log = Logger.getInstance().generateLog("teste");
        LogLevel level = LogLevel.ERROR;
        ColorDecorator decorator = new ColorDecorator(new LevelDecorator(log, level));

        String expectedResult = "\u001B[31m" + "[" + level + "] " +  log.getMessage() + "\u001B[0m";
        String obtainedResult = decorator.getMessage();

        assertEquals(expectedResult, obtainedResult);
    }

    @Test
    public void deveRetornarStringComFormatacaoDeCorPadraoQuandoONivelDoLogForNulo() {
        Log log = Logger.getInstance().generateLog("teste");
        ColorDecorator decorator = new ColorDecorator(log);

        String expectedResult = "\u001B[90m" +  log.getMessage() + "\u001B[0m";
        String obtainedResult = decorator.getMessage();

        assertEquals(expectedResult, obtainedResult);
    }
}
