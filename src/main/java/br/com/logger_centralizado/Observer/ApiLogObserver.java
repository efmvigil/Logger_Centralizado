package br.com.logger_centralizado.Observer;

import br.com.logger_centralizado.Log;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiLogObserver implements LogObserver{

    private final URL endpointUrl;

    public ApiLogObserver(URL endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    @Override
    public void update(Log log) {
        String jsonMessage = log.getMessage(); // deve ser JSON já formatado com JsonFormatDecorator

        try {
            HttpURLConnection con = (HttpURLConnection) endpointUrl.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = con.getOutputStream()) {
                os.write(jsonMessage.getBytes());
            }

            con.getResponseCode();
        } catch (Exception e) {
            System.err.println("Erro ao enviar log para API: " + e.getMessage());
        }
    }
}
