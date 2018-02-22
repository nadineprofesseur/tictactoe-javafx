package com.ludijeu.tictactoe.action;
import java.util.regex.Pattern;

import com.ludijeu.tictactoe.Client;
import com.ludijeu.tictactoe.vue.PageGrille;

public class ControleurGrille {
	
	protected PageGrille pageGrille;
	protected Client client;
	
	public ControleurGrille(PageGrille pageGrille)
	{
		this.pageGrille = pageGrille;
		this.client = new Client(this);
		
		Thread processusReseau = new Thread(
				new Runnable()
				{
					public void run()
					{
						client.recevoirMessage();
					}
				}
			);
		processusReseau.start();
	}
	
	protected String symbole = "x";

	public void setSymbole(String symbole) {
		System.out.println("le symbole enregistre est " + symbole);
		this.symbole = symbole;
	}

	public void declencherCaseDeJeu(int colonne, int rangee)
	{
		System.out.println("jouer un coup : " + colonne + " - " + rangee);
		if(this.symbole.compareTo("x") == 0) this.pageGrille.afficherCoupX(colonne, rangee);
		if(this.symbole.compareTo("o") == 0) this.pageGrille.afficherCoupO(colonne, rangee);
		this.client.envoyerMessage("<coup><symbole>"+symbole+"</symbole><colonne>"+colonne+"</colonne><rangee>"+rangee+"</rangee></coup>");
	}
	
	public void recevoirCoup(int colonne, int rangee) // on recoit toujours le symbole inverse, jamais le sien
	{
		System.out.println("recevoir un coup : " + colonne + " - " + rangee);
		if(this.symbole.compareTo("x") == 0) this.pageGrille.afficherCoupO(colonne, rangee);
		if(this.symbole.compareTo("o") == 0) this.pageGrille.afficherCoupX(colonne, rangee);
	}
	
	public void initialiser()
	{
		this.pageGrille.afficherTemps(0, 0);
		this.pageGrille.ecrireNomO("Nadine");
		this.pageGrille.ecrireNomX("Caroline");
		//this.pageGrille.afficherCoupO(2, 2);
		//this.pageGrille.afficherCoupX(1, 0);
	}
}
