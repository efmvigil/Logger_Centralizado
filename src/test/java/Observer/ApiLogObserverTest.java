package Observer;

import br.com.logger_centralizado.Decorator.JsonFormatDecorator;
import br.com.logger_centralizado.Decorator.LevelDecorator;
import br.com.logger_centralizado.Log;
import br.com.logger_centralizado.LogLevel;
import br.com.logger_centralizado.Logger;
import br.com.logger_centralizado.Observer.ApiLogObserver;
import org.junit.Test;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.mockito.Mockito.*;

public class ApiLogObserverTest {

    @Test
    public void deveEnviarLogParaAPIComoJson() throws Exception {
        Log log = new JsonFormatDecorator(
                new LevelDecorator(
                        Logger.getInstance().generateLog("Falha crítica"),
                        LogLevel.FATAL
                )
        );

        URL mockUrl = mock(URL.class);
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        OutputStream mockOutput = mock(OutputStream.class);

        when(mockUrl.openConnection()).thenReturn(mockConnection);
        when(mockConnection.getOutputStream()).thenReturn(mockOutput);
        when(mockConnection.getResponseCode()).thenReturn(200);

        ApiLogObserver observer = new ApiLogObserver(mockUrl);
        observer.update(log);

        verify(mockConnection).setRequestMethod("POST");
        verify(mockOutput).write(log.getMessage().getBytes());
        verify(mockConnection).getResponseCode();
    }

}
