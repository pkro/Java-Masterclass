package de.pkro;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    try {
        InetAddress address = InetAddress.getLocalHost();
        DatagramSocket datagramSocket = new DatagramSocket();

      Scanner scanner = new Scanner(System.in);
      String echoString;

      do {
        System.out.println("String to be echoed:");
        echoString = scanner.nextLine();
        byte[] buffer = echoString.getBytes();
        // packet contains everything from address to port number to message content
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
        datagramSocket.send(packet);

        // just receive response for demonstration purposes
        // as with UDP one usually doesn't want / expect a response
        byte[] buffer2 = new byte[50];
        packet = new DatagramPacket(buffer2, buffer2.length);
        // receive blocks / waits until it receives packet
        datagramSocket.receive(packet);
        System.out.println("Text received is " + new String(buffer2, 0, packet.getLength(), StandardCharsets.UTF_8 ));


      } while (!echoString.equals("exit"));
    }
    catch (SocketTimeoutException e) {
      e.printStackTrace();
    }
    catch(IOException e) {
        e.printStackTrace();
    }
    }
}
