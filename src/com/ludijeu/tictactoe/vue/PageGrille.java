package com.ludijeu.tictactoe.vue;
import com.ludijeu.tictactoe.action.ControleurGrille;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PageGrille extends Application
{
	protected ControleurGrille controleur;
	
	protected Rectangle caseDeJeu;
	
	public PageGrille()
	{
		// Le controleur est genere par la vue car l'instance utile de vue est créée en arrière plan par l'engin graphique
		// On recupere ainsi la reference a cette instance pour y referer dans le reste de l'application
		this.controleur = new ControleurGrille(this); 
		// Une autre solution aurait été d'enregistrer la vue auprès d'un Registre ou Navigateur général
		
		// https://docs.oracle.com/javafx/2/api/javafx/scene/shape/Rectangle.html
		this.caseDeJeu = new Rectangle();
		this.caseDeJeu.setWidth(100);
		this.caseDeJeu.setHeight(100);
		this.caseDeJeu.setFill(Color.AZURE);
		this.caseDeJeu.setStroke(Color.CHOCOLATE);
		this.caseDeJeu.setArcWidth(20);
		this.caseDeJeu.setArcHeight(20);
	}	
	
	@Override
	public void start(Stage scenePrincipale) 
	{
		
		StackPane racine = new StackPane();
		this.caseDeJeu.setX(100);
		this.caseDeJeu.setY(100);
		racine.getChildren().add(this.caseDeJeu);
		Scene scene = new Scene(racine, 800, 800);
		String cheminCSS = "decoration/Tictactoe.css";
		scene.getStylesheets().add(PageGrille.class.getResource(cheminCSS).toExternalForm());
		scenePrincipale.setScene(scene);
		scenePrincipale.show();
	}
	
}
