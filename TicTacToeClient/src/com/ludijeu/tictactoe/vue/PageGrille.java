package com.ludijeu.tictactoe.vue;
import com.ludijeu.tictactoe.action.ControleurGrille;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
		protected int colonne;
		protected int rangee;
		
		public CaseJeu() 
		{
			this.setWidth(100);
			this.setHeight(100);
			this.setFill(Color.AZURE);
			this.setStroke(Color.CHOCOLATE);
			this.setArcWidth(20);
			this.setArcHeight(20);				
		}
		public int getColonne() {
			return colonne;
		}
		public void setColonne(int colonne) {
			this.colonne = colonne;
		}
		public int getRangee() {
			return rangee;
		}
		public void setRangee(int rangee) {
			this.rangee = rangee;
		}
	}
	
	protected ControleurGrille controleur;	
	protected Rectangle[][] grille;		// https://docs.oracle.com/javafx/2/api/javafx/scene/shape/Rectangle.html
	
	public PageGrille()
	{		
		grille = new Rectangle[3][3];
		for(int colonne = 0; colonne < 3; colonne++)
		{
			for(int rangee = 0; rangee < 3; rangee++)
			{
				Rectangle caseDeJeu = new CaseJeu();
				int c = colonne; int r = rangee;
				caseDeJeu.setOnMouseClicked(
					new EventHandler<MouseEvent>() 
					{
					    public void handle(MouseEvent evenement) {
					    	controleur.declencherCaseDeJeu(c, r);
					    }        
					}
				);
				grille[colonne][rangee] = caseDeJeu;
			}
		}		
		
		// Le controleur est genere par la vue car l'instance utile de vue est créée en arrière plan par l'engin graphique
		// On recupere ainsi la reference a cette instance pour y referer dans le reste de l'application
		this.controleur = new ControleurGrille(this); 
		// Une autre solution aurait été d'enregistrer la vue auprès d'un Registre ou Navigateur général
	}	

	protected TextField libelleNomO;
	protected TextField libelleNomX;
	protected TextField libelleTemps;
	
	
	@Override
	public void start(Stage scenePrincipale) 
	{
		VBox racine = new VBox();
		Pane panneauHaut = new Pane();
		panneauHaut.setId("panneau-haut");
		HBox panneauInfos = new HBox();
		panneauInfos.setId("panneau-infos");
		panneauHaut.getChildren().add(panneauInfos);

		Pane panneauBas = new Pane();
		panneauBas.setId("panneau-bas");
		racine.getChildren().add(panneauHaut);
		racine.getChildren().add(panneauBas);
		
		GridPane panneauGrille = new GridPane();
		panneauGrille.setId("panneau-grille");
		panneauBas.getChildren().add(panneauGrille);
		
		libelleNomO = new TextField();
		libelleNomO.setId("libelle-nom-O"); // car pas de classes
		libelleNomX = new TextField();
		libelleNomX.setId("libelle-nom-X"); // car pas de classes
		libelleTemps = new TextField();
		libelleTemps.setId("libelle-temps");
		libelleTemps.setStyle("-fx-background-color:orange"); // TODO : remplacer par une classe
		panneauInfos.getChildren().add(libelleNomO);
		panneauInfos.getChildren().add(libelleTemps);
		panneauInfos.getChildren().add(libelleNomX);
		
		for(int colonne = 0; colonne < 3; colonne++)
		{
			for(int rangee = 0; rangee < 3; rangee++)
			{
				panneauGrille.add(grille[colonne][rangee],colonne, rangee);
			}
		}
		Scene scene = new Scene(racine, 400, 350);
		String cheminCSS = "decoration/Tictactoe.css";
		scene.getStylesheets().add(PageGrille.class.getResource(cheminCSS).toExternalForm());
		scenePrincipale.setScene(scene);
		scenePrincipale.show();
		
		this.controleur.initialiser();
	}
	
	public void ecrireNomO(String nom)
	{
		this.libelleNomO.setText(nom);
	}
	public void ecrireNomX(String nom)
	{
		this.libelleNomX.setText(nom);		
	}
	public void afficherTemps(int minutes, int secondes)
	{
		this.libelleTemps.setText(minutes + ":" + secondes);
	}
	public void afficherCoupX(int colonne, int rangee)
	{
		this.grille[colonne][rangee].setFill(Color.BLUE);
	}
	public void afficherCoupO(int colonne, int rangee)
	{
		this.grille[colonne][rangee].setFill(Color.GREEN);	
	}
}
