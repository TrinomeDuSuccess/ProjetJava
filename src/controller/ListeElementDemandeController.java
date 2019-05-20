package controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import application.MainApp;
import controller.model.Element;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ListeElementDemandeController implements Initializable {
	// reference à l'application principale : mainApp
	private MainApp mainApp;

	@FXML
	private TableView<Element> listeElementDemande;

	/**
	 * Initialise ListeElementDemandeController
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	/**
	 * Ouvre la vue RecapSimulationView Bouton "Retour"
	 */
	@FXML
	private void handleBoutonRetour(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controller/view/RecapSimulationView.fxml"));
			loader.setClassLoader(this.getClass().getClassLoader());
			AnchorPane retour = (AnchorPane) loader.load();

			this.mainApp.getRootLayout().setCenter(retour);
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
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		listeElementDemande.getItems().addAll(this.mainApp.getListeElementSimu());
	}
}
