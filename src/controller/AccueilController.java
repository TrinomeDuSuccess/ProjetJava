package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import application.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AccueilController implements Initializable {
	// reference à l'application principale : mainApp
	private MainApp mainApp;
	
	/**
	 * Constructeur par défault
	 */
	public AccueilController() {

	}

	/**
	 * Initialise AccueilController
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	/**
	 * Ouvre la vue StockMatierePremiereView
	 * Bouton "Marchandises"
	 */
	@FXML
	private void handleElementView() {
		try {
			// chargement du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controller/view/StockMatierePremiereView.fxml"));
			loader.setClassLoader(this.getClass().getClassLoader());
			AnchorPane elements = (AnchorPane) loader.load();

			this.mainApp.getRootLayout().setCenter(elements);

			// Donne à ElementController l'accès au MainApp
			ElementController elementController = loader.getController();
			elementController.setMainApp(this.mainApp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 
	 * Ouvre la vue MenuChainesView 
	 * Sur le bouton "Simulation"
	 */
	@FXML
	private void handleSimulationView() {
		try {
			// chargement du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controller/view/MenuChaineView.fxml"));
			loader.setClassLoader(this.getClass().getClassLoader());
			AnchorPane simulation = (AnchorPane) loader.load();

			this.mainApp.getRootLayout().setCenter(simulation);

			// Donne à MenuSimulationController l'accès au MainApp
			MenuSimulationController menuSimulationController = loader.getController();
			menuSimulationController.setMainApp(this.mainApp);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ouvre la vue ParametersView
	 * Sur le bouton "Paramètres"
	 */
	@FXML
	private void handleParametersView(ActionEvent event) {
		try {
			// récupère le primaryStage dans MainApp
			Stage recupPrimaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			// on définit l'url de redirection
			@SuppressWarnings("deprecation")
			URL url = new File("src/controller/view/ParametersView.fxml").toURL();
			// on charge la vue
			Parent marchandiseView = FXMLLoader.load(url);
			// on affecte la vue
			Scene marchandiseScene = new Scene(marchandiseView);
			Stage marchandiseStage = recupPrimaryStage;
			// on ajoute la vue à la scene
			marchandiseStage.setScene(marchandiseScene);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
     * Fait référence à l'application principale : mainApp
     * @param mainApp
     */
	public void setMainApp(MainApp mainController) {
		// pass the main app to the drawerContentController:
		this.mainApp = mainController;
	}
}