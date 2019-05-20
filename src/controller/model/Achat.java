package controller.model;

public class Achat {
	// attributs
	private String code;
	private double quantite;
	private double prix;
	private String codeChaine;
	private double total;

	/**
	 * Constructeur de la classe Achat
	 * 
	 * @param unCode       correspond au code élément à acheter
	 * @param uneQuantite  correspond à la quantité de l'élément à acheter
	 * @param unPrix       correspond au prix de l'élément à acheter
	 * @param unCodeChaine correspond à la chaine à qui cet achat est concerné
	 */
	public Achat(String unCode, double uneQuantite, double unPrix, String unCodeChaine) {
		this.code = unCode;
		this.quantite = uneQuantite;
		this.prix = unPrix;
		this.codeChaine = unCodeChaine;
		this.total = this.quantite * this.prix;
	}

	// getter setter
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getCodeChaine() {
		return codeChaine;
	}

	public void setCodeChaine(String codeChaine) {
		this.codeChaine = codeChaine;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}