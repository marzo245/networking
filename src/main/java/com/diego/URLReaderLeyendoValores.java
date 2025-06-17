package com.diego;

import java.io.*;
import java.net.*;

public class URLReaderLeyendoValores {

    public static void main(String[] args) throws Exception {
    //Pagina usada documetnacion URL https://docs.oracle.com/javase/8/docs/api/java/net/URL.html
    URL google = new URL("http://www.google.com/");
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()))) {
    String inputLine = null;
    while ((inputLine = reader.readLine()) != null) {
    System.out.println(inputLine);
    }
    } catch (IOException x) {
    System.err.println(x);
    }
    System.out.println(google.getProtocol() + "\n" + google.getAuthority() + "\n" + google.getHost() + "\n" +
     google.getPort() + "\n" + google.getPath() + "\n" + google.getQuery() + "\n" + google.getFile() + "\n" +
        google.getRef());
    }

}