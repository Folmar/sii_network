package pl.marekfoltyn.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

class UdpConnectionThread implements Runnable {

    private DatagramSocket socket;

    public UdpConnectionThread(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                this.socket.receive(packet);
                byte[] data = packet.getData();
                byte[] imie = Arrays.copyOfRange(data, 0, 30);
                String stringImie = new String(imie);
                String dane = new String(Arrays.copyOfRange(data, 31, 1023));
                System.out.println("<" + stringImie.trim() + ">:" + dane.trim());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
