package com.adamstinziani;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/*
UDP Server
Made by Adam Stinziani
Date: 2019-05-06
 */
public class Main {

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost(); //getByName() pass host name
            DatagramSocket datagramSocket = new DatagramSocket(); //Isn't associated with a port

            Scanner scanner = new Scanner(System.in);
            String echoString;

            do{
                System.out.printf("Enter string to be echoed: ");
                echoString = scanner.nextLine();
                byte[] buffer = echoString.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                datagramSocket.send(packet);

                byte[] buffer2 = new byte[50];
                packet = new DatagramPacket(buffer2,buffer2.length);
                //Going to wait here until it receives server's message
                datagramSocket.receive(packet);
                System.out.println("Text received is: " + new String(buffer2,0,packet.getLength()));

            }while(!echoString.equals("exit"));
        } catch (SocketTimeoutException e) {
            System.out.println("The socket timed out");
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
