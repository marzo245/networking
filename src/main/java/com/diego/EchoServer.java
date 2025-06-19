package com.diego;

import java.net.*;
import java.io.*;

public class EchoServer {
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
            String funcion = "sin";
            if (inputLine.startsWith("fun")) {
                String[] parts = inputLine.split(":");
                if (parts.length == 2) {
                    funcion = parts[1];
                    try {
                        if (funcion.startsWith("sin")) {
                            String[] funcParts = funcion.split(" ");
                            if (funcParts.length == 2) {
                                double value = Double.parseDouble(funcParts[1]);
                                double result = Math.sin(value);
                                inputLine = "Result: " + String.valueOf(result);
                            } else {
                                inputLine = "Invalid function format.";
                            }
                        } else if(funcion.startsWith("cos")) {
                            String[] funcParts = funcion.split(" ");
                            if (funcParts.length == 2) {
                                double value = Double.parseDouble(funcParts[1]);
                                double result = Math.cos(value);
                                inputLine = "Result: " + String.valueOf(result);
                            } else {
                                inputLine = "Invalid function format.";
                            }
                        } else if(funcion.startsWith("tan")) {
                            String[] funcParts = funcion.split(" ");
                            if (funcParts.length == 2) {
                                double value = Double.parseDouble(funcParts[1]);
                                double result = Math.tan(value);
                                inputLine = "Result: " + String.valueOf(result);
                            } else {
                                inputLine = "Invalid function format.";
                            }
                        } else{
                            double value = Double.parseDouble(inputLine);
                            if(funcion.equals("sin")) {
                                double result = Math.sin(value);
                                inputLine = "Result: " + String.valueOf(result);
                            } else if(funcion.equals("cos")) {
                                double result = Math.cos(value);
                                inputLine = "Result: " + String.valueOf(result);
                            } else if(funcion.equals("tan")) {
                                double result = Math.tan(value);
                                inputLine = "Result: " + String.valueOf(result);
                            }  }
                    } catch (NumberFormatException e) {
                        inputLine = "Error: Invalid number format.";
                    }
                }else{
                    double value = Double.parseDouble(inputLine);
                    if(funcion.equals("sin")) {
                        double result = Math.sin(value);
                        inputLine = "Result: " + String.valueOf(result);
                    } else if(funcion.equals("cos")) {
                        double result = Math.cos(value);
                        inputLine = "Result: " + String.valueOf(result);
                    } else if(funcion.equals("tan")) {
                        double result = Math.tan(value);
                        inputLine = "Result: " + String.valueOf(result);
                    }} 
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
