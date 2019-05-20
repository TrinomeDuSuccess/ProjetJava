package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.MainApp;
import controller.model.CSVReaderElement;
import controller.model.Element;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ElementController implements Initializable {
	// reference à l'application principale : mainApp
	private MainApp mainApp;

	private ObservableList<Element> elementData = FXCollections.observableArrayList();

	@FXML
	private TableView<Element> matierePremiereTable;

	/**
	 * Constructeur par défault
	 */
	public ElementController() {

	}

	/**
	 * Initialise ElementController
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// va charger les données élement dans le CSV
		CSVReaderElement elements = new CSVReaderElement();
		elementData.addAll(elements.toImport());
		matierePremiereTable.getItems().addAll(elementData);
	}
	
	/**
	 * Ouvre la vue AccueilView
	 * Bouton "Retour"
	 */
	@FXML
	private void handleBoutonRetour(ActionEvent event) {
		try {
			// récupération de la vue
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controller/view/AccueilView.fxml"));
			loader.setClassLoader(this.getClass().getClassLoader());
			AnchorPane elements = (AnchorPane) loader.load();

			// changement de scene (RootLayout dans MainApp)
			this.mainApp.getRootLayout().setCenter(elements);

			// Donne à AccueilController l'accès au MainApp
			AccueilController accueilController = loader.getController();
			accueilController.setMainApp(this.mainApp);

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
		this.mainApp = mainController;
	}

}
