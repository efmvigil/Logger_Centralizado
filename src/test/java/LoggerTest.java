import br.com.logger_centralizado.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public class LoggerTest {

    @Test
    public void deveRetornarAMesmaInstancia() {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        assertSame(logger1, logger2);
    }

    @Test
    public void deveGerarLogComAMensagemPassadaComoArgumento() {
        String mensagemEsperada = "Erro ao conectar com banco de dados";

        Logger logger = Logger.getInstance();
        Logger.Log log = logger.generateLog(mensagemEsperada);
        String mensagemObtida = log.getMessage();

        assertEquals(mensagemEsperada, mensagemObtida);
    }
}
