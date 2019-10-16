package pl.marekfoltyn.server;

import java.io.*;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private final Socket acceptedSocket;

    //Dodanie konstruktora na potrzeby podpięcia socketa na odpowiednim porcie
    public ServerConnection(Socket acceptedSocket) {
        this.acceptedSocket = acceptedSocket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = acceptedSocket.getInputStream();
            OutputStream outputStream = acceptedSocket.getOutputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
