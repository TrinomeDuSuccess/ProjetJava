package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import controller.AccueilController;
import controller.ElementController;
import controller.model.Achat;
import controller.model.CSVReaderChaine;
import controller.model.CSVReaderElement;
import controller.model.Chaine;
import controller.model.Element;
import controller.model.HistoriqueSimulation;
import controller.model.Stockage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Element> elementData = FXCollections.observableArrayList();

	public ObservableList<Element> getElementData() {
		return elementData;
	}

	// private ObservableList<Chaine> chaineData =
	// FXCollections.observableArrayList();
	private ObservableList<Achat> listeAchat = FXCollections.observableArrayList();
	private ObservableList<Chaine> listeChaineImpossible = FXCollections.observableArrayList();
	private ObservableList<Chaine> listeChainePossible = FXCollections.observableArrayList();
	private ObservableList<Stockage> listeStockage = FXCollections.observableArrayList();
	private ObservableList<Element> listeElementSimu = FXCollections.observableArrayList();

	private double efficacite;

	public MainApp() {
		CSVReaderElement elements = new CSVReaderElement();
		this.elementData.addAll(elements.toImport());
		
		
//
//		CSVReaderChaine chaines = new CSVReaderChaine();
//		this.chaineData.addAll(chaines.toImport());
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MIAGE APPLICATION");

		initRootLayout();
		showAccueilView();
		
		//Chargement du fichier
		File fichier = new File("historique.bin");
		byte[] data = new byte[(int)fichier.length()];
		try {
			FileInputStream fis = new FileInputStream(fichier);
			try {
				//récupération des données 
				fis.read(data);
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Chargement des données 
		HistoriqueSimulation.fromByteArray(data);
	}

	/**
	 * Initialise le layout principal.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../controller/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(this.rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * affiche la vue : AccueilView dans le layout principal.
	 */
	public void showAccueilView() {
		try {
			// chargement du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controller/view/AccueilView.fxml"));
			loader.setClassLoader(this.getClass().getClassLoader());
			AnchorPane indexView = (AnchorPane) loader.load();

			// au centre du layout
			this.rootLayout.setCenter(indexView);

			// Donne à AccueilController l'accès au MainApp
			AccueilController accueilController = loader.getController();
			accueilController.setMainApp(this);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	// getter setter

	// récupère le LayoutPrincipal de l'application
	public BorderPane getRootLayout() {
		return rootLayout;
	}

	// récupère la liste des éléments
//	public ObservableList<Element> getElementData() {
//		return elementData;
//	}

	// récupère la liste des achats
	public ObservableList<Achat> getListeAchat() {
		return listeAchat;
	}

	public void setListeAchat(ObservableList<Achat> listeAchat) {
		this.listeAchat = listeAchat;
	}

	public ObservableList<Chaine> getListeChaineImpossible() {
		return listeChaineImpossible;
	}

	public void setListeChaineImpossible(ObservableList<Chaine> listeChaineImpossible) {
		this.listeChaineImpossible = listeChaineImpossible;
	}

	public ObservableList<Chaine> getListeChainePossible() {
		return listeChainePossible;
	}

	public void setListeChainePossible(ObservableList<Chaine> listeChainePossible) {
		this.listeChainePossible = listeChainePossible;
	}

//	public ObservableList<Chaine> getChaineData() {
//		return chaineData;
//	}
//
//	public void setChaineData(ObservableList<Chaine> chaineData) {
//		this.chaineData = chaineData;
//	}

	public ObservableList<Stockage> getListeStockage() {
		return listeStockage;
	}

	public void setListeStockage(ObservableList<Stockage> listeStockage) {
		this.listeStockage = listeStockage;
	}

	public ObservableList<Element> getListeElementSimu() {
		return listeElementSimu;
	}

	public void setListeElementSimu(ObservableList<Element> listeElementSimu) {
		this.listeElementSimu = listeElementSimu;
	}

	public double getEfficacite() {
		return efficacite;
	}

	public void setEfficacite(double efficacite) {
		this.efficacite = efficacite;
	}
}
