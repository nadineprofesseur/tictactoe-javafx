package com.ludijeu.tictactoe;
import com.ludijeu.tictactoe.vue.PageGrille;

public class App 
{
	public static void main(String[] parametres) 
	{
		PageGrille pageGrille = new PageGrille();
		pageGrille.launch(PageGrille.class, parametres);
	}
}
