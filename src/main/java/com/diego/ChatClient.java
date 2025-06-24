package com.diego;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Ingresa la IP del servidor: ");
    String serverIP = sc.nextLine();

    System.out.print("Ingresa tu nombre: ");
    String name = sc.nextLine();

    final int PORT = 12345;

    try {
      Socket socket = new Socket(serverIP, PORT);
      System.out.println("Conectado al servidor en " + serverIP + ":" + PORT);

      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(
        new InputStreamReader(socket.getInputStream())
      );

      // Hilo para leer mensajes del servidor
      new Thread(() -> {
        try {
          String msg;
          while ((msg = in.readLine()) != null) {
            System.out.println("\n" + msg);
            System.out.print("> ");
          }
        } catch (IOException e) {
          System.out.println("Conexión cerrada.");
        }
      })
        .start();

      // Hilo principal para enviar mensajes
      String msg;
      while (true) {
        System.out.print("> ");
        msg = sc.nextLine();
        if (msg.equalsIgnoreCase("salir")) break;
        out.println(name + ": " + msg);
      }

      socket.close();
      System.exit(0);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
