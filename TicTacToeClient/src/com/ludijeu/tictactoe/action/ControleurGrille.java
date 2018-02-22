package com.ludijeu.tictactoe.action;
import com.ludijeu.tictactoe.vue.PageGrille;

public class ControleurGrille {
	
	protected PageGrille pageGrille;
	
	public ControleurGrille(PageGrille pageGrille)
	{
		this.pageGrille = pageGrille;
	}
	
	public void declencherCaseDeJeu(int colonne, int rangee)
	{
		System.out.println("jouer un coup : " + colonne + " - " + rangee);
		this.pageGrille.afficherCoupX(colonne, rangee);
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
