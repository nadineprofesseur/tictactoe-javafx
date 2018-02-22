package com.ludijeu.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ludijeu.tictactoe.action.ControleurGrille;

public class Client {

	public static int PORT = 2000;
	protected Socket connexion = null;	
	protected PrintWriter imprimante = null;
	protected BufferedReader lecteur = null;
	protected ControleurGrille controleur = null;
	
	public Client(ControleurGrille controleur)
	{
		this.controleur = controleur;
		try {
			this.connexion = new Socket(InetAddress.getLocalHost(),PORT);
			this.imprimante = new PrintWriter(connexion.getOutputStream(),true);
			this.lecteur = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean envoyerMessage(String message)
	{
		this.imprimante.println(message);
		this.imprimante.flush();
		System.out.println("Message envoye " + message);
		return true;
	}
	
	public void recevoirMessage()
	{
		if(lecteur == null) return;
		try {
			System.out.println("Recevoir message()");
			String message = null;
			while((message = lecteur.readLine()) != null)
			{
				System.out.println("Message recu " + message);
				interpreterMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	Pattern regexCoup = Pattern.compile("<coup><symbole>(.*)</symbole><colonne>(.*)</colonne><rangee>(.*)</rangee></coup>");
	public void interpreterMessage(String message)
	{
		Matcher coupDansMessage = regexCoup.matcher(message);
		if(coupDansMessage.matches())
		{
			String symbole = coupDansMessage.group(1);
			if(symbole.compareTo("x") == 0) 
			{
				this.controleur.recevoirCoup(Integer.parseInt(coupDansMessage.group(2)), Integer.parseInt(coupDansMessage.group(3)));
			}
		}

	}
	
}
