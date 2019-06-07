/*
TCP Server Main
Made by Adam Stinziani
Date: 2019-05-06
 */
package com.adamstinziani;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
	    try(ServerSocket serverSocket = new ServerSocket(5000)){
            while(true){
                //Create end to end connection
                new Echoer(serverSocket.accept()).start();
            }

        }catch (IOException e){
            System.out.println("Server Exception: " + e.getMessage());
	    }
    }
}
