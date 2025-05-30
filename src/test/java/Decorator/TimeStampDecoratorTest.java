package Decorator;

import br.com.logger_centralizado.Decorator.TimeStampDecorator;
import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.Logger;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class TimeStampDecoratorTest {

    @Test
    public void deveRetornarStringComDataEHoraAtualFormatadasCorretamente() {
        Log log = Logger.getInstance().generateLog("teste");
        TimeStampDecorator decorator = new TimeStampDecorator(log);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);

        String obtainedResult = decorator.getMessage();
        String expectedResult = "[" + formattedDateTime + "] " + log.getMessage();

        assertEquals(expectedResult, obtainedResult);
    }
}
