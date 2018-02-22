package com.ludijeu.tictactoe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	
	public static int PORT = 2000;
	private ServerSocket ecouteur = null;

	public Serveur()
	{
		this.ouvrir();
		this.ecouter();
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
	
	public void ecouter()
	{
		if(ecouteur != null)
		{
				Socket connexion = null;
				try {
					while((connexion = ecouteur.accept()) != null)
					{
						System.out.println("Demande de connexion recue");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
