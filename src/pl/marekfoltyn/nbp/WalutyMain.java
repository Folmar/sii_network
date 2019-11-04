package pl.marekfoltyn.nbp;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;

public class WalutyMain {
    public static void main(String[] args) throws IOException {
//        tabliceTablic();
//        metodaZebyNieStracicKodu();

        String json = "{\"currency\":\"bat (Tajlandia)\",\"code\":\"THB\",\"mid\":0.1270}";
//        callWebService();

        ObjectMapper objectMapper = new ObjectMapper(); //żeby było można korzystać z ObjectMapper należy pobrać biblioteki jackson (maven central w przeglądarce)
        Rate rate = objectMapper.readValue(json, Rate.class);
        changeToPLN(rate);
        //dodanie scratch file ctrl + alt + shift + insert - zaznaczyć wynik w konsoli i zostanie utworzony plik
    }

    private static void tabliceTablic() throws IOException {
        String json = callJsonWebService();
        ObjectMapper objectMapper = new ObjectMapper();

        Table[] tables = objectMapper.readValue(json, Table[].class);
        for (Table t : tables) {
            for (Rate rate : t.getRates()) {
                System.out.println(rate.getCurrency() + " " + rate.getMid());
            }
        }
    }

    private static void metodaZebyNieStracicKodu() throws com.fasterxml.jackson.core.JsonProcessingException {
        String json = "{\"currency\":\"bat (Tajlandia)\",\"code\":\"THB\",\"mid\":0.1270}";
//        callWebService();

        ObjectMapper objectMapper = new ObjectMapper(); //żeby było można korzystać z ObjectMapper należy pobrać biblioteki jackson (maven central w przeglądarce)
        Rate rate = objectMapper.readValue(json, Rate.class);
        System.out.println();

        Rate rate1 = new Rate();
        rate1.setCode("MVA");
        rate1.setCurrency("Moja waluta");
        rate1.setMid(new BigDecimal("33.33"));

        Rate[] rates = {rate, rate1};

        String s = objectMapper.writeValueAsString(rates);
        System.out.println(s);
    }

    //dodać parametr na tabelę A,B,C
    //Stworzyć tabelę walut które nie będą się powtarzać - wyciągnąć unikalne wartości z 3 tabel A, B, C
    private static void callWebService() throws IOException {
        URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        String readValue = new String(bufferedInputStream.readAllBytes());
        System.out.println(readValue);
    }

    private static String callJsonWebService() throws IOException {
        URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        String readValue = new String(bufferedInputStream.readAllBytes());
        return readValue;
    }

    private static void changeToPLN(Rate rate) {
        BigDecimal result = new BigDecimal("100").divide(rate.getMid(), 2, RoundingMode.HALF_EVEN);
        System.out.println("100 zł kosztuje " + result + " " + rate.getCode());
    }
}
