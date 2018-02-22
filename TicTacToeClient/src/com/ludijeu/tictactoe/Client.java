package com.ludijeu.tictactoe;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	public static int PORT = 2000;
	private Socket connexion = null;	
	
	public Client()
	{
		try {
			this.connexion = new Socket(InetAddress.getLocalHost(),PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
