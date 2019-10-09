package pl.marekfoltyn.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) throws IOException {
        Socket localhost = new Socket("localhost", 5555);
        OutputStream outputStream = localhost.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes("Cześć, uczę się JAVY");
        outputStream.close();
    }

    /**
     * ZAD DOM zapisywanie i odczytywanie z pliku - przypomnieć sobie
     * */
}
