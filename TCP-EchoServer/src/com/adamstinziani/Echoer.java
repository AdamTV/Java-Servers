/*
TCP Server Echoer class
Made by Adam Stinziani
Date: 2019-05-06
 */
package com.adamstinziani;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread {
    private Socket socket;

    public Echoer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String echoString = input.readLine();
                System.out.println("Recieved Client input: " + echoString);
                if (echoString.equals("exit")) {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Thread Interrupted");
                }
                output.println("RESPONSE FROM SERVER: " + echoString);
            }
        } catch (IOException e) {
            System.out.println("OOPS: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }
}
