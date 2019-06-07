package com.adamstinziani;

import java.io.IOException;
import java.net.*;

/*
UDP Server
Made by Adam Stinziani
Date: 2019-05-06
 */
public class Main {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5000);
            while(true){
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                //Blocked until packet received (does not create end to end connection)
                socket.receive(packet);
                System.out.println("Text received is: " + new String(buffer,0,packet.getLength()));

                String returnString = "echo: " + new String(buffer,0,packet.getLength());
                byte[] buffer2 = returnString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length, address, port);
                socket.send(packet);
            }
        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
