package pl.marekfoltyn.nbp;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class WalutyMain {
    public static void main(String[] args) throws IOException {

        String json = "{\"currency\":\"bat (Tajlandia)\",\"code\":\"THB\",\"mid\":0.1270}";
//        callWebService();

        ObjectMapper objectMapper = new ObjectMapper(); //żeby było można korzystać z ObjectMapper należy pobrać biblioteki jackson (maven central w przeglądarce)
        Rate rate = objectMapper.readValue(json, Rate.class);
        System.out.println();

        changeToPLN(rate);
        //dodanie scratch file ctrl + alt + shift + insert - zaznaczyć wynik w konsoli i zostanie utworzony plik
    }


    private static void callWebService() throws IOException {
        URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        String readValue = new String(bufferedInputStream.readAllBytes());
        System.out.println(readValue);
    }

    private static void changeToPLN(Rate rate) {
        BigDecimal PLNcalculate = BigDecimal.valueOf(1000);
        BigDecimal PLN = rate.getMid().multiply(PLNcalculate);
        System.out.println("100 zł kosztuje " + PLN + " " + rate.getCode());
    }
}
