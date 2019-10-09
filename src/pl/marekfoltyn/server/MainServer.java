package pl.marekfoltyn.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555); //port dowolny większy od 1000
        Socket acceptedSocket = serverSocket.accept();
        InputStream inputStream = acceptedSocket.getInputStream();
        OutputStream outputStream = acceptedSocket.getOutputStream();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        inputStream.close();
        outputStream.close();
    }

    //W serwerze zapiąc wątki i zaimplementować Runnable
}
