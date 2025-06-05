package Decorator;

import br.com.logger_centralizado.Decorator.ContextDecorator;
import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ContextDecoratorTest {

    @Test
    public void deveRetornarStringComChavesEValoresDeContextoFormatadosCorretamente() {
        String key1 = "userID";
        String value1 = "12345";
        String key2 = "requestID";
        String value2 = "req-01";

        Map<String, String> context = new LinkedHashMap<>();
        context.put("userID", "12345");
        context.put("requestID", "req-01");

        Log log = Logger.getInstance().generateLog("teste");
        ContextDecorator decorator = new ContextDecorator(log, context);

        String obtainedResult = decorator.getMessage();
        String expectedResult = "[context: " + key1 + "=" + value1 + ", " + key2 + "=" + value2 + "]" + log.getMessage();

        assertEquals(expectedResult, obtainedResult);
    }

    @Test
    public void deveRetornarStringComContextoVazioQuandoNaoExistemParesDeChaveEValor() {
        Log log = Logger.getInstance().generateLog("teste");
        Map<String, String> context = new HashMap<>();
        ContextDecorator decorator = new ContextDecorator(log, context);

        String obtainedResult = decorator.getMessage();
        String expectedResult = "[context: ]" + log.getMessage();

        assertEquals(expectedResult, obtainedResult);
    }
}
