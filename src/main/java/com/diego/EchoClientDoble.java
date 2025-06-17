package com.diego;

import java.io.*;
import java.net.*;

public class EchoClientDoble {
    public static void main(String[] args) {
        try (
            Socket echoSocket = new Socket("127.0.0.1", 35000);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to server at 127.0.0.1:35000");
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Server response: " + in.readLine());
                if ("exit".equalsIgnoreCase(userInput)) {
                    System.out.println("Exiting...");
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: 127.0.0.1");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost");
        }
    }
}