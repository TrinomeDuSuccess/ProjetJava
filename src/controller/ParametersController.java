package controller;

import java.awt.TextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ParametersController implements Initializable {

	private TextField CSVpath;
	private Button modCSVpath;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@FXML
	private void changeCSVpath(ActionEvent event) {
		
	}
	
	@FXML
	private void handleBoutonRetour(ActionEvent event) {
		try {
			//récupère le primaryStage dans MainApp 
			Stage recupPrimaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			//on définit l'url de redirection
			URL url = new File("src/controller/view/AccueilView.fxml").toURL();
			//on charge la vue
			Parent marchandiseView = FXMLLoader.load(url);
			//on affecte la vue
			Scene marchandiseScene = new Scene(marchandiseView);
			Stage marchandiseStage = recupPrimaryStage;
			//on ajoute la vue à la scene
			marchandiseStage.setScene(marchandiseScene);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
