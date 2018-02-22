package com.ludijeu.tictactoe;
import com.ludijeu.tictactoe.vue.PageGrille;

public class App 
{
	public static void main(String[] parametres) 
	{
		//ControleurGrille controleurGrille = new ControleurGrille();
		//PageGrille pageGrille = new PageGrille(controleurGrille);
		PageGrille pageGrille = new PageGrille();
		pageGrille.launch(PageGrille.class, parametres);
		//pageGrille.afficherNomO("Nadine");
		//pageGrille.afficherNomX("Caroline");
	}
}
