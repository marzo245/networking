package com.diego;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

  private static final int PORT = 12345;
  private static final Set<PrintWriter> clientWriters = Collections.synchronizedSet(
    new HashSet<>()
  );

  public static void main(String[] args) {
    System.out.println("Servidor de chat iniciado en puerto " + PORT);

    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println(
          "Nuevo cliente conectado: " + clientSocket.getInetAddress()
        );
        new ClientHandler(clientSocket).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Clase interna para manejar cada cliente
  private static class ClientHandler extends Thread {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) {
      this.socket = socket;
    }

    public void run() {
      try {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        clientWriters.add(out);

        String msg;
        while ((msg = in.readLine()) != null) {
          System.out.println("Mensaje recibido: " + msg);
          // Reenviar a todos los clientes
          synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
              writer.println(msg);
            }
          }
        }
      } catch (IOException e) {
        System.out.println("Cliente desconectado.");
      } finally {
        try {
          if (out != null) clientWriters.remove(out);
          if (socket != null) socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
