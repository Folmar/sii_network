package pl.marekfoltyn.nbp;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WalutyMain {
    public static void main(String[] args) throws IOException {
        URL url = new URL("hhtp://api.nbp.pl/api/exchangerates/tables/A");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        String readValue = new String(bufferedInputStream.readAllBytes());
        System.out.println(readValue);

        ObjectMapper objectMapper = new ObjectMapper();
    }
}
