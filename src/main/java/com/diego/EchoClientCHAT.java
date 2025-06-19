package com.diego;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class EchoClientCHAT {

	public void ejecutaServicio(String ipRmiregistry, int puertoRmiRegistry, String nombreServicio) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry(ipRmiregistry, puertoRmiRegistry);
			EchoServerCHAT echoServerCHAT = (EchoServerCHAT) registry.lookup(nombreServicio);
			System.out.println(echoServerCHAT.echo("Hola como estas?"));
		} catch (Exception e) {
			System.err.println("Hay un problema:");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EchoClientCHAT ec = new EchoClientCHAT();
		ec.ejecutaServicio("127.0.0.1", 23000, "echoServer");
	}
}
