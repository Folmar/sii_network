package pl.marekfoltyn.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UDPMain {
    public static void main(String[] args) {
        try {
            //192.168.0.255 - moj adres rozgloszeniowy
            final InetAddress ADDRESS = InetAddress.getByName("192.168.0.255");
            ExecutorService executorService = Executors.newCachedThreadPool();
            DatagramSocket datagramSocket = new DatagramSocket(6666);

            UdpConnectionThread udpConnectionThread = new UdpConnectionThread(datagramSocket);
            executorService.submit(udpConnectionThread);

            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length, ADDRESS, 6666);

            Scanner scanner = new Scanner(System.in);
            String s = null;
            while ((s = scanner.nextLine()) != null) {
                //s nie moze być większe niz buf.length
                byte[] name = Arrays.copyOf("Adrian".getBytes(), 30);
                byte[] data = Arrays.copyOf(s.getBytes(), 1024 - 30);
                System.arraycopy(name, 0, buf, 0, 30);
                System.arraycopy(data, 0, buf, 31, 993);
                packet.setData(buf);
                datagramSocket.send(packet);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

