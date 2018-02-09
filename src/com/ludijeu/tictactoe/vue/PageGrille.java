package com.ludijeu.tictactoe.vue;
import com.ludijeu.tictactoe.action.ControleurGrille;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PageGrille extends Application
{
	protected ControleurGrille controleur;
	
	public PageGrille()
	{
		//this.controleur = new ControleurGrille();
		//this.controleur.setVue(this);
		this.controleur = new ControleurGrille(this);
	}
	
	
	//public PageGrille(ControleurGrille controleur)
	//{
	//	this.controleur = controleur;
	//}
	
	
	@Override
	public void start(Stage scenePrincipale) 
	{
		
		StackPane racine = new StackPane();
		//racine.getChildren().add();
		Scene scene = new Scene(racine, 800, 800);
		String cheminCSS = "decoration/Tictactoe.css";
		scene.getStylesheets().add(PageGrille.class.getResource(cheminCSS).toExternalForm());
		scenePrincipale.setScene(scene);
		scenePrincipale.show();
	}
	
}
