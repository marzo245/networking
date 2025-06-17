package com.diego;

import java.net.*;
import java.io.*;

public class EchoServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(35000);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		}

		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(
			new InputStreamReader(clientSocket.getInputStream())
		);

		String inputLine, outputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println("Mensaje: " + inputLine);
            String funcion = "sin";
            if(inputLine.contains("fun:")){
                if(inputLine.contains("fun:sin")){
                    funcion = "sin";
					String[] parts = inputLine.split(":");
                    System.out.println(parts);
					int doubledValue = (int) Math.sin(Integer.parseInt(parts[1]));
			        inputLine = String.valueOf(doubledValue);
				}else if(inputLine.contains("fun:cos")){
					funcion = "cos";
					int doubledValue = (int) Math.cos(Integer.parseInt(inputLine));
					inputLine = String.valueOf(doubledValue);
				} else if(inputLine.contains("fun:tan")){
					funcion = "tan";
					int doubledValue = (int) Math.tan(Integer.parseInt(inputLine));
					inputLine = String.valueOf(doubledValue);
				} else{
					if(funcion.equals("sin")){
						int doubledValue = (int) Math.sin(Integer.parseInt(inputLine));
						inputLine = String.valueOf(doubledValue);
					} else if(funcion.equals("cos")){
						int doubledValue = (int) Math.cos(Integer.parseInt(inputLine));
						inputLine = String.valueOf(doubledValue);
					} else if(funcion.equals("tan")){
						int doubledValue = (int) Math.tan(Integer.parseInt(inputLine));
						inputLine = String.valueOf(doubledValue);
					}
				}
			}else{
			    int doubledValue = Integer.parseInt(inputLine) * 2;
			    inputLine = String.valueOf(doubledValue);
			}
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
}
