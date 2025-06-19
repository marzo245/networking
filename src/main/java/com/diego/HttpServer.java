package com.diego;

import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.util.Date;

public class HttpServer {
    private static final int PORT = 35000;
    private static final String BASE_DIR = System.getProperty("user.dir") + "/src/main/java/com/diego/";
    private static final String DEFAULT_FILE = "index.html";
    
    public static void main(String[] args) {
        try {
            startServer();
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
    
    private static void startServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("HTTP Server running on port " + PORT);
            System.out.println("Serving files from: " + BASE_DIR);
            System.out.println("Access at: http://localhost:" + PORT);
            
            while (true) {
                handleClientRequest(serverSocket.accept());
            }
        }
    }
    
    private static void handleClientRequest(Socket clientSocket) {
        try (InputStream input = clientSocket.getInputStream();
             OutputStream output = clientSocket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             PrintWriter writer = new PrintWriter(output, true)) {
            
            // Leer la solicitud HTTP
            String requestLine = reader.readLine();
            if (requestLine == null) return;
            
            System.out.println(new Date() + " - Request: " + requestLine);
            
            // Procesar la solicitud GET
            if (requestLine.startsWith("GET")) {
                String requestedFile = extractRequestedFile(requestLine);
                serveFile(requestedFile, writer, output);
            } else {
                sendErrorResponse(501, "Not Implemented", writer);
            }
            
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
    
    private static String extractRequestedFile(String requestLine) {
        String[] parts = requestLine.split(" ");
        if (parts.length < 2) return DEFAULT_FILE;
        
        String filePath = parts[1].substring(1); // Eliminar el slash inicial
        return filePath.isEmpty() ? DEFAULT_FILE : filePath;
    }
    
    private static void serveFile(String fileName, PrintWriter writer, OutputStream output) throws IOException {
        Path filePath = Paths.get(BASE_DIR, fileName);
        
        if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
            sendErrorResponse(404, "Not Found", writer);
            return;
        }
        
        try {
            byte[] fileContent = Files.readAllBytes(filePath);
            String contentType = getContentType(fileName);
            
            // Enviar cabeceras HTTP
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: " + contentType);
            writer.println("Content-Length: " + fileContent.length);
            writer.println("Connection: close");
            writer.println(); // Línea en blanco entre cabeceras y contenido
            
            // Enviar contenido del archivo
            output.write(fileContent);
            output.flush();
            
            System.out.println("Served file: " + fileName + " (" + fileContent.length + " bytes)");
            
        } catch (IOException e) {
            sendErrorResponse(500, "Internal Server Error", writer);
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    private static void sendErrorResponse(int statusCode, String statusText, PrintWriter writer) {
        writer.println("HTTP/1.1 " + statusCode + " " + statusText);
        writer.println("Content-Type: text/html");
        writer.println();
        writer.println("<html><head><title>" + statusCode + " " + statusText + "</title></head>");
        writer.println("<body><h1>" + statusCode + " " + statusText + "</h1>");
        writer.println("<p>The requested resource could not be found or processed.</p>");
        writer.println("</body></html>");
        
        System.err.println("Error response sent: " + statusCode + " " + statusText);
    }
    
    private static String getContentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        
        switch (extension) {
            case "html":
            case "htm":
                return "text/html";
            case "css":
                return "text/css";
            case "js":
                return "application/javascript";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "ico":
                return "image/x-icon";
            case "txt":
                return "text/plain";
            case "json":
                return "application/json";
            default:
                return "application/octet-stream";
        }
    }
}
