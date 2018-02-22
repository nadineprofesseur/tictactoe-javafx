package com.ludijeu.tictactoe;
import com.ludijeu.tictactoe.vue.PageGrille;

import javafx.application.Application;

public class App 
{
	public static void main(String[] parametres) 
	{
		//PageGrille pageGrille = new PageGrille();
		Application.launch(PageGrille.class, parametres);
	}
}
