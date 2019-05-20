package controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import application.MainApp;
import controller.model.Achat;
import controller.model.Chaine;
import controller.model.OpenCSVWriter;
import controller.model.Stockage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RecapController implements Initializable {
	// reference à l'application principale : mainApp
	private MainApp mainApp;

	@FXML
	private Label efficacite;

	@FXML
	private TableView<Chaine> chaineOK;

	@FXML
	private TableView<Chaine> chaineKO;

	@FXML
	private TableView<Achat> listeAchat;

	@FXML
	private TableView<Stockage> stockage;

	/**
	 * Initialise RecapController
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * Constructeur par défault
	 */
	public RecapController() {

	}

	/***
	 * Permet d'accéder au tableau d'élément avec le pourcentage de demande dans un
	 * modal (listeElementDemandeView)
	 */
	@FXML
	private void handleAccesDemandeView() {
		try {
			// chargement du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controller/view/listeElementDemandeView.fxml"));
			loader.setClassLoader(this.getClass().getClassLoader());
			AnchorPane elements = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.mainApp.getPrimaryStage());

			Scene scene = new Scene(elements);
			dialogStage.setScene(scene);

			// Donne à ListeElementController l'accès au MainApp
			ListeElementDemandeController listeElementDemandeController = loader.getController();
			listeElementDemandeController.setMainApp(this.mainApp);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * Ouvre la vue MenuChaineView. Bouton "retour"
	 */
	@FXML
	private void handleMenuSimulationView() {
		try {
			// chargement du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controller/view/MenuChaineView.fxml"));
			loader.setClassLoader(this.getClass().getClassLoader());
			AnchorPane elements = (AnchorPane) loader.load();

			this.mainApp.getRootLayout().setCenter(elements);

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
	 * Permet d'exporter la liste d'achat sous forme de csv
	 * Bouton "Exporter la liste d'achat"
	 */
	@FXML
	private void handleExport() {
		OpenCSVWriter w = new OpenCSVWriter();

		try {
			if (w.exportAchat(this.mainApp.getListeAchat())) {
				Alert alertMsg = new Alert(AlertType.INFORMATION);
				alertMsg.initOwner(this.mainApp.getPrimaryStage());
				alertMsg.setTitle("Résultat de l'exportation");
				alertMsg.setHeaderText("L'export a bien été effectué !");
				alertMsg.showAndWait();
			} else {
				Alert alertMsg = new Alert(AlertType.WARNING);
				alertMsg.initOwner(this.mainApp.getPrimaryStage());
				alertMsg.setTitle("Résultat de l'exportation");
				alertMsg.setHeaderText("Erreur lors de l'exportation !");
				alertMsg.showAndWait();
			}
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Fait référence à l'application principale : mainApp
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainController) {
		this.mainApp = mainController;
		chaineOK.getItems().addAll(this.mainApp.getListeChainePossible());
		chaineKO.getItems().addAll(this.mainApp.getListeChaineImpossible());
		listeAchat.getItems().addAll(this.mainApp.getListeAchat());
		stockage.getItems().addAll(this.mainApp.getListeStockage());
	}

	/**
	 * Permet d'affecter la valeur de l'efficacité dans le Label de la vue RecapSimulationView
	 * @param efficacite
	 */
	public void setEfficacite(String efficacite) {
		this.efficacite.setText(efficacite + "€");
	}
}