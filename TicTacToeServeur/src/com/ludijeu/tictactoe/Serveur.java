package com.ludijeu.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
			try {
				while((connexion = ecouteur.accept()) != null)
				{
					System.out.println("Demande de connexion recue");
					this.imprimante = new PrintWriter(connexion.getOutputStream(),true);
					this.lecteur = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
					this.imprimante.println("Bievenue sur ce serveur"); this.imprimante.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// pour chaque connexion recue
	protected Socket connexion = null;
	protected PrintWriter imprimante = null;
	protected BufferedReader lecteur = null;
	
}
