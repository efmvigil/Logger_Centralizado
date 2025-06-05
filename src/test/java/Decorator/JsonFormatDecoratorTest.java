package Decorator;

import br.com.logger_centralizado.Decorator.ContextDecorator;
import br.com.logger_centralizado.Decorator.JsonFormatDecorator;
import br.com.logger_centralizado.Decorator.LevelDecorator;
import br.com.logger_centralizado.Decorator.TimeStampDecorator;
import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.LogLevel;
import br.com.logger_centralizado.Logger;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JsonFormatDecoratorTest {

    @Test
    public void deveRetornarUmaStringJSONContendoApenasAPropriedadeMessage() {
        Log log = Logger.getInstance().generateLog("teste");
        JsonFormatDecorator jsonFormatDecorator = new JsonFormatDecorator(log);

        String result = jsonFormatDecorator.getMessage();

        assertTrue(result.contains("\"message\":\"teste\""));
        assertFalse(result.contains("\"timeStamp\""));
        assertFalse(result.contains("\"level\""));
        assertFalse(result.contains("\"context\""));
    }

    @Test
    public void deveRetornarUmaStringJSONContendoAPropriedadeLevel() {
        Log log = Logger.getInstance().generateLog("teste");
        LogLevel level = LogLevel.ERROR;

        LevelDecorator levelDecorator = new LevelDecorator(log, level);
        JsonFormatDecorator jsonFormatDecorator = new JsonFormatDecorator(levelDecorator);

        String result = jsonFormatDecorator.getMessage();

        assertTrue(result.contains("\"level\":\"ERROR\""));
        assertTrue(result.contains("\"message\":\"teste\""));
    }

    @Test
    public void deveRetornarUmaStringJSONContendoTodasAsPropriedades() {
        Log log = Logger.getInstance().generateLog("teste");
        LogLevel level = LogLevel.ERROR;
        Map<String, String> context = Map.of("userID", "12345", "requestID", "req-01");

        LevelDecorator levelDecorator = new LevelDecorator(log, level);
        TimeStampDecorator timeStampDecorator = new TimeStampDecorator(levelDecorator);
        ContextDecorator contextDecorator = new ContextDecorator(timeStampDecorator, context);
        JsonFormatDecorator jsonFormatDecorator = new JsonFormatDecorator(contextDecorator);

        String result = jsonFormatDecorator.getMessage();

        System.out.println(result);

        assertTrue(result.contains("\"level\":\"ERROR\""));
        assertTrue(result.contains("\"timeStamp\""));
        assertTrue(result.contains("\"context\""));
        assertTrue(result.contains("\"userID\":\"12345\""));
        assertTrue(result.contains("\"requestID\":\"req-01\""));
        assertTrue(result.contains("\"message\":\"teste\""));
    }
}
