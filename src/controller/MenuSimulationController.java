package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import application.MainApp;
import controller.model.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MenuSimulationController implements Initializable {
	// reference à l'application principal : mainApp
	private MainApp mainApp;

	private ObservableList<Stockage> stockageDataSimu = FXCollections.observableArrayList();
	private ObservableList<Chaine> chainesData = FXCollections.observableArrayList();
	private ObservableList<Element> elementDataSimu = FXCollections.observableArrayList();

	@FXML
	private ScrollPane scrollChaine;
	@FXML
	private GridPane gridChaine;

	/**
	 * Initialise MenuSimulationController
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.stockageDataSimu.clear();

		CSVReaderChaine chaines = new CSVReaderChaine();
		chainesData.addAll(chaines.toImport());

		CSVReaderElement elementsSimu = new CSVReaderElement();
		elementDataSimu.addAll(elementsSimu.toImport());
		this.tableChaine();

		CSVReaderStockage stockagesSimu = new CSVReaderStockage();
		stockageDataSimu.addAll(stockagesSimu.toImport());
	}

	/**
	 * Fait référence à l'application principale : mainApp
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainController) {
		this.mainApp = mainController;

	}

	/**
	 * Ouvre la vue AccueilView Bouton "Retour"
	 */
	@FXML
	private void handleBoutonRetour(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controller/view/AccueilView.fxml"));
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
	 * Répartit les éléments dans les moyens de stockage dédiés
	 * 
	 * @param uneListeElement : liste de l'état final des éléments après la
	 *                        simulation
	 */
	public void repartitionStock(List<Element> uneListeElement) {
		for (Stockage unStockage : this.stockageDataSimu) {
			for (Element unElement : uneListeElement) {
				// on met les éléments selon son stockage
				if (unStockage.getCode().equals(unElement.getStockage())) {
					unStockage.ajouter(unElement);
				}
			}
		}

		// MAJ du nombre dispo
		for (Stockage unStockage : this.stockageDataSimu) {
			int qteDispoConso = 0;
			for (Element unElement : unStockage.getElementStock()) {
				int res = 0;
				// calcul de nombre de quantiteDispo consommé
				if (unElement.getQuantite() > 0) {
					res = unElement.getQuantite() / unStockage.getCapacite();
					// si on a un reste, on ajoute un stockage
					if ((unElement.getQuantite() % unStockage.getCapacite() > 0)) {
						res += 1;
					}
					qteDispoConso += res;
				}
			}
			// maj
			unStockage.setQuantiteDisponible(unStockage.getQuantiteDisponible() - qteDispoConso);
			this.mainApp.getListeStockage().add(unStockage);
		}

		// affichage quantité des moyens de stockage dans le terminal
		for (Stockage unStockage : this.stockageDataSimu) {
			if (unStockage.getCode().equals("p"))
				;
			System.out.println("stockage : " + unStockage.getNom());
			System.out.println("qte dispo : " + unStockage.getQuantiteDisponible());
			for (Element unElement : unStockage.getElementStock()) {
				System.out.println(unElement.getNom());
			}
			System.out.println("\n");
		}
	}

	/**
	 * Affiche les chaînes de production avec un checkBox et input
	 */
	public void tableChaine() {
		// Entête
		Label chaineLabel = new Label("Chaine de Production");
		Label nivLabel = new Label("Niveau");

		// position labels
		this.gridChaine.add(chaineLabel, 0, 0); // (label, colonne, ligne)
		this.gridChaine.add(nivLabel, 1, 0);

		// Données
		int i = 1, j = 1;
		for (Chaine uneChaine : this.chainesData) {
			// le checkBox
			CheckBox unCheckBox = new CheckBox();
			unCheckBox.setText(uneChaine.getCode() + " " + uneChaine.getNom());
			this.gridChaine.add(unCheckBox, 0, i++);

			// le input
			TextField unInput = new TextField();
			unInput.setText("0");
			this.gridChaine.add(unInput, 1, j++);
		}

		this.scrollChaine.setContent(gridChaine);
		this.scrollChaine.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.scrollChaine.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.scrollChaine.setFitToHeight(true);
		this.scrollChaine.setPannable(true);
	}

	/**
	 * Calcul la demande en pourcentage
	 * 
	 * @param uneListeElement : liste de l'état final des éléments après la
	 *                        simulation
	 */
// calcul % demande (en supposant que l'élément en entrée est saisi qu'une fois
//	  La demande est satisfaite si la quantite de l'element est >= à la demande sinon elle est insatisfaite 
//	  et il faut indiquer à combien de % elle est satisfaite 
//	  ex : si la demande est de 10 et qu'on a que 8 elements on dit qu'elle est satisfaite à 80 %
	public void percentDemande(List<Element> uneListeElement) {
		for (Element unElement : uneListeElement) {
			double res = 0;
			if (unElement.getQuantite() >= unElement.getDemande()) {
				res = 100;
			} else {
				if (unElement.getQuantite() > 0) {
					res = ((double) unElement.getQuantite() / (double) unElement.getDemande()) * 100;
					System.out.println(res);
				}
			}
			unElement.setPercent((int) res);
			this.mainApp.getListeElementSimu().add(unElement);
		}
	}

	/**
	 * Effectue la simulation après sélection de chaînes de production Bouton
	 * "Simuler"
	 * 
	 * @param event
	 */
	@FXML
	public void launchSimulation() {
		// clear les listes
		this.mainApp.getListeStockage().clear();
		this.mainApp.getListeAchat().clear();
		this.mainApp.getListeChaineImpossible().clear();
		this.mainApp.getListeChainePossible().clear();
		this.mainApp.getListeElementSimu().clear();
		int efficacite = 0;

		// Creation du string de résultat
		String resultat = "";
		boolean verifCase = false;
		boolean verifValue = false;

		for (int i = 0; i < this.chainesData.size(); i++) {
			for (Node uneCase : this.gridChaine.getChildren()) {
				if (GridPane.getRowIndex(uneCase) == i + 1 && GridPane.getColumnIndex(uneCase) == 0) {
					CheckBox unCheckBox = (CheckBox) uneCase;
					if (unCheckBox.isSelected()) {
						verifCase = true;
					}
				}
			}
		}

		if (!verifCase) {
			Alert alertMsg = new Alert(AlertType.WARNING);
			alertMsg.initOwner(this.mainApp.getPrimaryStage());
			alertMsg.setTitle("Erreur de sélection");
			alertMsg.setHeaderText("Erreur sélection chaîne");
			alertMsg.setContentText("Veuillez cocher au moins une chaîne a simulé !");
			alertMsg.showAndWait();
		} else {
			// pour chaque case du gridPane
			for (Node uneCase : this.gridChaine.getChildren()) {
				CheckBox unCheckBox;

				for (int i = 0; i < this.chainesData.size(); i++) {

					if (GridPane.getRowIndex(uneCase) == i + 1 && GridPane.getColumnIndex(uneCase) == 0) {
						unCheckBox = (CheckBox) uneCase;

						if (unCheckBox.isSelected()) {
							TextField recupInput = null;

							for (Node uneCase2 : this.gridChaine.getChildren()) {
								if (GridPane.getRowIndex(uneCase2) == i + 1 && GridPane.getColumnIndex(uneCase2) == 1) {
									// récupération du niveau
									recupInput = (TextField) uneCase2;
								}
							}

							// chaine selectionné
							Chaine chaineSelect = this.chainesData.get(i);

							if (Integer.parseInt(recupInput.getText()) > 0) {
								// simulation
								// MAJ stock consommé : élément en entrée
								for (CodeQTE unElementEntree : chaineSelect.getListeEntree()) {
									boolean na = false;
									// MAJ données
									for (Element unElementListe : this.elementDataSimu) {
										if (unElementEntree.getCode().equals(unElementListe.getCode())) {
											int diff = 0;
											diff = unElementListe.getQuantite() - (unElementEntree.getQuantite()
													* Integer.parseInt(recupInput.getText()));
											unElementListe.setQuantite(diff);
											unElementListe.setConso((unElementEntree.getQuantite()
													* Integer.parseInt(recupInput.getText())));
										}
									}
								}

								// MAJ stock produit stocké : élément en sortie
								for (CodeQTE unElementSortie : chaineSelect.getListeSortie()) {
									boolean na = false;
									// MAJ données
									for (Element unElementListe : this.elementDataSimu) {
										if (unElementSortie.getCode().equals(unElementListe.getCode())) {
											int ajout = 0;
											ajout = (unElementListe.getQuantite() + (unElementSortie.getQuantite()
													* Integer.parseInt(recupInput.getText())));

											System.out.println("Elément sortie ajouté : " + unElementListe.getNom()
													+ " - quantité final élément (Stock initial + prod) : " + ajout
													+ "\n");
											unElementListe.setQuantite(ajout);
											unElementListe.setConso(unElementSortie.getQuantite()
													* Integer.parseInt(recupInput.getText()));
										}
									}
								}

								// analyse du stock après production de la chaine
								for (Element unElementListe : this.elementDataSimu) {
									if (unElementListe.getQuantite() < 0) {
										// si l'élement ne possède pas de prix d'achat (NA)
										if (unElementListe.getAchat().equals("NA")) {
											// chaine de production impossible
											if (!this.mainApp.getListeChaineImpossible().contains(chaineSelect)) {
												this.mainApp.getListeChaineImpossible().add(chaineSelect);
											}
										} else {
											// si l'élément possède un prix d'achat
											// ajout à la liste d'achat
											this.mainApp.getListeAchat()
													.add(new Achat(unElementListe.getCode(),
															Math.abs(unElementListe.getConso()),
															Integer.parseInt(unElementListe.getAchat()),
															chaineSelect.getCode()));

//												if(this.mainApp.getListeAchat().isEmpty()) {
//													this.mainApp.getListeAchat().add(new Achat(unElementListe.getCode(),
//															Math.abs(unElementListe.getQuantite()),
//															Integer.parseInt(unElementListe.getAchat()), chaineSelect.getCode()));
//												}else {
//													boolean present = false;
//													//vérification de la présence de l'élément dans la liste
//													for(Achat unAchatListe : this.mainApp.getListeAchat()) {
//														if(unElementListe.getCode().equals(unAchatListe.getCode())) {
//															present = true;
//															//maj liste achat
//															unAchatListe.setQuantite(Math.abs(unElementListe.getQuantite()));
//														}
//													}
//													
//													if(!present) {
//														this.mainApp.getListeAchat().add(new Achat(unElementListe.getCode(),
//																Math.abs(unElementListe.getQuantite()),
//																Integer.parseInt(unElementListe.getAchat()), chaineSelect.getCode()));
//													}
//												}

										}
									}
								}

								// ##########################################################################################
								// définition chaine possible
								// ##########################################################################################

								// si la chaine n'est pas dans la liste d'achat
								// c'est que la chaine de production est possible
								boolean present = false;

								for (Achat unAchat : this.mainApp.getListeAchat()) {
									if (unAchat.getCodeChaine().equals(chaineSelect.getCode())) {
										present = true;
									}
								}

								// si la chaîne n'est pas dans la liste des chaines KO
								// c'est que la chaine de production est possible
								for (Chaine uneChaine : this.mainApp.getListeChaineImpossible()) {
									if (uneChaine.getCode().equals(chaineSelect.getCode())) {
										present = true;
									}
								}

								// ajout à la liste de chaine possible
								if (!present) {
									this.mainApp.getListeChainePossible().add(chaineSelect);
								}

								// calcul de l'efficacité
								// valeurs de vente de tous les stocks dispo après production - montant total de
								// la liste d'achats
								double stocksVentes = 0;

								for (CodeQTE unCodeQte : chaineSelect.getListeSortie()) {
									for (Element unElement : this.elementDataSimu) {
										if (unCodeQte.getCode().equals(unElement.getCode())) {
											// valeurs de vente de tous les stocks disponibles
											if (!unElement.getVente().equals("NA") && unElement.getQuantite() >= 0) {
												stocksVentes += (Double.parseDouble(unElement.getVente())
														* unElement.getQuantite());
											}
										}
									}
								}

								System.out.println("##############################");
								System.out.println("Stock vente : " + stocksVentes);
								System.out.println("##############################");

								// montant total des achats à effectuer
								double totalAchat = 0;
								for (Achat unAchat : this.mainApp.getListeAchat()) {
									if (unAchat.getCodeChaine().equals(chaineSelect.getCode())) {
										totalAchat += unAchat.getPrix() * unAchat.getQuantite();
									}
								}

								// efficacité
								efficacite += (stocksVentes - totalAchat);
							}
						}

					}
				}
			}
			// affichage des stock après simulation dans le terminal
			System.out.println("Etat des stocks après simulation");
			for (Element unElement : this.elementDataSimu) {
				System.out.println(unElement.getCode() + " - " + unElement.getNom() + " - " + unElement.getQuantite());
			}

			// répartition des éléments dans les stockages après simulation
			this.repartitionStock(this.elementDataSimu);

			// pourcentage de la demande
			this.percentDemande(this.elementDataSimu);

//				this.mainApp.setListeElementSimu(this.elementDataSimu);

			// redirection vers la vue RecapSimulationView (controllerRecap)
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("/controller/view/RecapSimulationView.fxml"));
				loader.setClassLoader(this.getClass().getClassLoader());
				AnchorPane recapSimu = (AnchorPane) loader.load();

				this.mainApp.getRootLayout().setCenter(recapSimu);

				// Donne à RecapController l'accès au MainApp
				RecapController recapController = loader.getController();
				recapController.setMainApp(this.mainApp);
				recapController.setEfficacite(Double.toString(efficacite));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Gestion de l'historique

			// on renseigne le String récapitulatif
			resultat = "EFFICACITE : " + this.mainApp.getEfficacite();
			resultat += " / ACHATS : ";
			for (int i = 0; i < this.mainApp.getListeAchat().size(); i++) {
				resultat += this.mainApp.getListeAchat().get(i).toString() + ";";
			}
			resultat += " / IMPOSSIBLES : ";
			for (int i = 0; i < this.mainApp.getListeChaineImpossible().size(); i++) {
				resultat += this.mainApp.getListeChaineImpossible().get(i).toString() + ";";
			}
			resultat += " / POSSIBLES : ";
			for (int i = 0; i < this.mainApp.getListeChaineImpossible().size(); i++) {
				resultat += this.mainApp.getListeChaineImpossible().get(i).toString() + ";";
			}
			// On ajoute le String resultat à notre historique
			HistoriqueSimulation h = HistoriqueSimulation.getInstance();
			h.add(resultat);

			// Creation du FileOutputStream
			try {
				FileOutputStream fileOutputStream = new FileOutputStream("historique.bin");
				fileOutputStream.write(h.toByteArray());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}