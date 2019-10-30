package pl.marekfoltyn.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5555);
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes("JAVAAAAiii\n");
        dataOutputStream.flush();
        dataOutputStream.close();
        outputStream.close();
        socket.close();
    }

    /**
     * ZAD DOM zapisywanie i odczytywanie z pliku - przypomnieć sobie
     *
     * Sprawdzić te sokety - czy działają lokalnie - poczytać o UDP w Java
     *
     * ściągnąć JavaFX Scene Builder 2.0
     * */
}
