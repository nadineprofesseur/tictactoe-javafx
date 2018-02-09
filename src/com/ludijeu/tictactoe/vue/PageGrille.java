package com.ludijeu.tictactoe.vue;
import com.ludijeu.tictactoe.action.ControleurGrille;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
//import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PageGrille extends Application
{
	protected class CaseJeu extends Rectangle
	{
		public CaseJeu() 
		{
			this.setWidth(100);
			this.setHeight(100);
			this.setFill(Color.AZURE);
			this.setStroke(Color.CHOCOLATE);
			this.setArcWidth(20);
			this.setArcHeight(20);				
		}
	}
	
	protected ControleurGrille controleur;	
	protected Rectangle[][] grille;		// https://docs.oracle.com/javafx/2/api/javafx/scene/shape/Rectangle.html
		
	public PageGrille()
	{
		// Le controleur est genere par la vue car l'instance utile de vue est créée en arrière plan par l'engin graphique
		// On recupere ainsi la reference a cette instance pour y referer dans le reste de l'application
		this.controleur = new ControleurGrille(this); 
		// Une autre solution aurait été d'enregistrer la vue auprès d'un Registre ou Navigateur général
		
		grille = new Rectangle[3][3];
		for(int colonne = 0; colonne < 3; colonne++)
		{
			for(int rangee = 0; rangee < 3; rangee++)
			{
				Rectangle caseDeJeu = new CaseJeu();				
				grille[colonne][rangee] = caseDeJeu;
			}
		}		
	}	
	
	@Override
	public void start(Stage scenePrincipale) 
	{
		VBox racine = new VBox();
		Pane panneauHaut = new Pane();
		Pane panneauBas = new Pane();
		racine.getChildren().add(panneauHaut);
		racine.getChildren().add(panneauBas);
		
		GridPane panneauGrille = new GridPane();
		panneauBas.getChildren().add(panneauGrille);
		panneauHaut.getChildren().add(new TextField());
		panneauHaut.getChildren().add(new TextField());
		
		for(int colonne = 0; colonne < 3; colonne++)
		{
			for(int rangee = 0; rangee < 3; rangee++)
			{
				panneauGrille.add(grille[colonne][rangee],colonne, rangee);
			}
		}
		Scene scene = new Scene(racine, 800, 800);
		String cheminCSS = "decoration/Tictactoe.css";
		scene.getStylesheets().add(PageGrille.class.getResource(cheminCSS).toExternalForm());
		scenePrincipale.setScene(scene);
		scenePrincipale.show();
	}
	
}
