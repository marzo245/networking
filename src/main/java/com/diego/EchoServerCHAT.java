package com.diego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerCHAT {

    
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(35000);
			System.out.println("Server started on port 35000. Waiting for client...");
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			e.printStackTrace();
			System.exit(1);
		}

		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
			System.out.println("Client connected.");
		} catch (IOException e) {
			System.err.println("Accept failed.");
			e.printStackTrace();
			System.exit(1);
		}

		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(
			new InputStreamReader(clientSocket.getInputStream())
		);

		String inputLine, outputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println("Mensaje: " + inputLine);
            outputLine = "Respuesta: " + inputLine;
			out.println(outputLine);
            if (outputLine.equals("Respuesta: Bye.")) {
				break;
			}
		}

		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
    }

    public char[] echo(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'echo'");
    }
    
}
