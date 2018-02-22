package com.ludijeu.tictactoe;

import java.io.IOException;
import java.net.ServerSocket;

public class Serveur {
	
	public static int PORT = 2000;
	private ServerSocket ecouteur = null;

	public Serveur()
	{
		this.ouvrir();
	}
	
	public boolean ouvrir()
	{
				
		try {
			this.ecouteur = new ServerSocket(PORT);
			System.out.println("Lancement de l'ecoute du serveur");			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	

}
