package com.ludijeu.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Serveur {
	
	public static int PORT = 2000;
	protected ServerSocket ecouteur = null;
	static public int INDICE_X = 0;
	static public int INDICE_O = 1;
	protected ContactJoueur[] listeJoueurs;

	public Serveur()
	{
		this.listeJoueurs = new ContactJoueur[2];
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
				if((connexion = ecouteur.accept()) != null)
				{
					System.out.println("Demande de connexion recue");
					ContactJoueur contact = new ContactJoueur("x");
					this.listeJoueurs[INDICE_X] = contact;
					contact.imprimante = new PrintWriter(connexion.getOutputStream(),true);
					contact.lecteur = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
					contact.imprimante.println("Bievenue sur ce serveur " + contact.symbole); contact.imprimante.flush();
					contact.ecouterMessages();
				}
				if((connexion = ecouteur.accept()) != null)
				{
					System.out.println("Demande de connexion recue");
					ContactJoueur contact = new ContactJoueur("y");
					this.listeJoueurs[INDICE_O] = contact;
					contact.imprimante = new PrintWriter(connexion.getOutputStream(),true);
					contact.lecteur = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
					contact.imprimante.println("Bievenue sur ce serveur " + contact.symbole); contact.imprimante.flush();
					contact.ecouterMessages();
				}
				// refuser les autres connexion
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected int[][] grille = new int[3][3];
	protected String[] listeSymboles = new String[]{"x","o"};
	
	public class ContactJoueur
	{
		// pour chaque connexion recue
		public Socket connexion = null;
		public PrintWriter imprimante = null;
		public BufferedReader lecteur = null;
		
		public String symbole = null;
		
		public ContactJoueur(String symbole)
		{
			this.symbole = symbole;
		}
		
		
		Pattern regexCoup = Pattern.compile("<coup><symbole>(.*)</symbole><colonne>(.*)</colonne><rangee>(.*)</rangee></coup>");

		public void recevoirMessage()
		{
			if(lecteur == null) return;
			try {
				System.out.println("Recevoir message()");
				String message = null;
				while((message = lecteur.readLine()) != null)
				{
					System.out.println("Message recu " + message);
					Matcher coupDansMessage = regexCoup.matcher(message);
					if(coupDansMessage.matches())
					{
						String symbole = coupDansMessage.group(1);
						if(symbole.compareTo("x") == 0) 
						{
							grille[Integer.parseInt(coupDansMessage.group(2))][Integer.parseInt(coupDansMessage.group(3))] = INDICE_X;
							listeJoueurs[INDICE_O].envoyerMessage(message);
						}
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		
		public void envoyerMessage(String message)
		{
			System.out.println("Envoi du message " + message);
			this.imprimante.println(message);
			this.imprimante.flush();		
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
