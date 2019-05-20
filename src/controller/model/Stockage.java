package controller.model;

import java.util.ArrayList;
import java.util.List;

public class Stockage {
	// attributs
	private String code;
	private String nom;
	private int capacite;
	private int quantiteDisponible;
	private List<Element> elementStock;

	/**
	 * Constructeur par défaut
	 */
	public Stockage() {
	}

	/**
	 * Constructeur de la classe Stockage
	 * 
	 * @param unCode      correspond au code du moyen de stockage
	 * @param unNom       correspond au nom du moyen de stockage
	 * @param uneCapacite correspond à la capacité de stockage maximum du moyen de
	 *                    stockage
	 * @param uneQteDispo correspond à la quantité disponible du moyen de stockage
	 */
	public Stockage(String unCode, String unNom, int uneCapacite, int uneQteDispo) {
		this.code = unCode;
		this.nom = unNom;
		this.capacite = uneCapacite;
		this.quantiteDisponible = uneQteDispo;
		this.elementStock = new ArrayList<>();
	}

	/**
	 * Permet d'ajouter un élément dans la liste. Cette liste nous permet de
	 * connaître quels éléments sont concernés par un moyen de stockage
	 * 
	 * @param element
	 */
	public void ajouter(Element element) {
		this.elementStock.add(element);
	}

	// getter setter
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public int getQuantiteDisponible() {
		return quantiteDisponible;
	}

	public void setQuantiteDisponible(int quantiteDisponible) {
		this.quantiteDisponible = quantiteDisponible;
	}

	public List<Element> getElementStock() {
		return elementStock;
	}
}