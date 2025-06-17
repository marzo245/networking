package com.diego;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class URLReaderLeyendoPaginas {

    public static void main(String[] args) throws Exception {
    //Pagina usada documetnacion URL https://docs.oracle.com/javase/8/docs/api/java/net/URL.html
    
    Scanner scanner = new Scanner(System.in);
    System.out.println("Ingrese la URL que desea leer:");
    URL urlLeida = new URL(scanner.nextLine());
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlLeida.openStream()))) {
    String inputLine = null;
    while ((inputLine = reader.readLine()) != null) {
    System.out.println(inputLine);
    }
    } catch (IOException x) {
    System.err.println(x);
    }
    System.out.println("Protocolo--> " + urlLeida.getProtocol() + "\n" + "Authority--> "+ urlLeida.getAuthority() + "\n" + "Host --> "+ urlLeida.getHost() + "\n" +
    "Port --> "+ urlLeida.getPort() + "\n" + " Path--> "+ urlLeida.getPath() + "\n" + "Query--> "+urlLeida.getQuery() + "\n" + 
    "File --> "+ urlLeida.getFile() + "\n" +"Ref-->" +
        urlLeida.getRef());
    }

}