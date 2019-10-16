package pl.marekfoltyn.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        ExecutorService executorService = Executors.newCachedThreadPool();//zamiast pisać Thread najlepiej posługiwać się Executorami
        Socket acceptedSocket = serverSocket.accept();
        while (true) {
            executorService.submit(new ServerConnection(acceptedSocket));
        }
    }
}
