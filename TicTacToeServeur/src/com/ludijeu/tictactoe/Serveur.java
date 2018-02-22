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
	
	protected Socket connexion = null;
	public void ecouter()
	{
		if(ecouteur != null)
		{
			try {
				while((connexion = ecouteur.accept()) != null)
				{
					System.out.println("Demande de connexion recue");
					ContactJoueur contact = new ContactJoueur();
					contact.imprimante = new PrintWriter(connexion.getOutputStream(),true);
					contact.lecteur = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
					contact.imprimante.println("Bievenue sur ce serveur"); contact.imprimante.flush();
					contact.ecouterMessages();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public class ContactJoueur
	{
		// pour chaque connexion recue
		public Socket connexion = null;
		public PrintWriter imprimante = null;
		public BufferedReader lecteur = null;
		
		public void recevoirMessage()
		{
			if(lecteur == null) return;
			try {
				System.out.println("Recevoir message()");
				String message = null;
				while((message = lecteur.readLine()) != null)
				{
					System.out.println("Message recu " + message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		
		public void ecouterMessages()
		{
			Thread processusReseau = new Thread(
					new Runnable()
					{
						public void run()
						{
							recevoirMessage();
						}
					}
				);
			processusReseau.start();
		}		
	}
	
}
