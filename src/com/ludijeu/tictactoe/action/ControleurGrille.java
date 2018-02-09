package com.ludijeu.tictactoe.action;
import com.ludijeu.tictactoe.vue.PageGrille;

public class ControleurGrille {
	
	protected PageGrille pageGrille;
	
	public ControleurGrille(PageGrille pageGrille)
	{
		this.pageGrille = pageGrille;
	}
	
	public void jouerCoup()
	{
		System.out.println("jouer un coup");
	}
	
	public void tester()
	{
		this.pageGrille.afficherTemps(3, 45);
		this.pageGrille.ecrireNomO("Nadine");
		this.pageGrille.ecrireNomX("Caroline");
	}
}
