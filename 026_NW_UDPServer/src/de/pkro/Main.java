package de.pkro;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Main {

  public static void main(String[] args) {
    try {
      DatagramSocket socket = new DatagramSocket(5000);

      while (true) {
        byte[] buffer = new byte[50];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        // doesn't return anything, doesn't create an end to end connection
        socket.receive(packet);
        System.out.println(
                "Text received is "
                        + new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8));

        // not necessary as UDP connection is one way, this seems to be
        // added to show how to get sender info from packet and return
        // sth. to the sender by hand
        String returnString = "echo: " + new String(buffer, 0, packet.getLength());
        byte[] buffer2 = returnString.getBytes();
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket(buffer2, buffer2.length, address, port);
        socket.send(packet);
      }
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
