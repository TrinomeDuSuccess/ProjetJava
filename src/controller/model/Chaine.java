package controller.model;

import java.util.ArrayList;
import java.util.List;

public class Chaine {
	// attributs
	private String code;
	private String nom;
	private String entree;
	private String sortie;
	private List<CodeQTE> listeEntree;
	private List<CodeQTE> ListeSortie;
	private int temps;
	private int personnelsNonQualifies;
	private int personnelsQualifies;

	/**
	 * Constructeur de la classe Chaine
	 * 
	 * @param unCode           correspond au code la chaîne
	 * @param unNom            correspond au nom de la chaîne
	 * @param listeEntree      correspond à la liste d'éléments en entrée dont la
	 *                         chaîne a besoin
	 * @param uneSortie        correspond à l'élément produit par la chaîne
	 * @param unTps
	 * @param persNonQualifies
	 * @param persQualifies
	 * @throws Exception
	 */
	public Chaine(String unCode, String unNom, String listeEntree, String uneSortie, int unTps, int persNonQualifies,
			int persQualifies) throws Exception {
		this.code = unCode;
		this.nom = unNom;
		this.listeEntree = this.convert(listeEntree);
		this.ListeSortie = this.convert(uneSortie);
		this.temps = unTps;
		this.personnelsNonQualifies = persNonQualifies;
		this.personnelsQualifies = persQualifies;
	}

	/**
	 * Constructeur par défaut
	 */
	public Chaine() {

	}

	/**
	 * Transforme un texte en liste d'objet de type CodeQTE
	 * 
	 * @param unEntree : liste d'entree ou sortie
	 * @return
	 */
	private List<CodeQTE> convert(String uneEntree) {
		List<String> text = new ArrayList<String>();
		String[] parts = uneEntree.split(",");

		for (String unPart : parts) {
			// code
			if (unPart.contains("(")) {
				text.add(unPart.substring(1));
			}
			// quantité
			if (unPart.contains(")")) {
				text.add(unPart.substring(0, unPart.length() - 1));
			}
		}

		// tableau d'objet type CodeQTE
		List<CodeQTE> transform = new ArrayList<CodeQTE>();

		int i = 0;

		while (i < text.size()) {
			transform.add(new CodeQTE(text.get(i), Integer.valueOf(text.get(i + 1))));
			i = i + 2;
		}

		return transform;
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

	public String getEntree() {
		return entree;
	}

	public void setEntree(String entree) {
		this.entree = entree;
	}

	public String getSortie() {
		return sortie;
	}

	public void setSortie(String sortie) {
		this.sortie = sortie;
	}

	public List<CodeQTE> getListeEntree() {
		return listeEntree;
	}

	public List<CodeQTE> getListeSortie() {
		return ListeSortie;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public int getPersonnelsNonQualifies() {
		return personnelsNonQualifies;
	}

	public void setPersonnelsNonQualifies(int personnelsNonQualifies) {
		this.personnelsNonQualifies = personnelsNonQualifies;
	}

	public int getPersonnelsQualifies() {
		return personnelsQualifies;
	}

	public void setPersonnelsQualifies(int personnelsQualifies) {
		this.personnelsQualifies = personnelsQualifies;
	}
}