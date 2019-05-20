package controller.model;

public class Element {
	// attributs
	private String code;
	private String nom;
	private int quantite;
	private String unite;
	private String achat;
	private String vente;
	private String stockage;
	private int demande;
	private int percent;
	private int conso;

	/**
	 * Constructeur de la classe Element
	 * 
	 * @param unCode      correspond au code de l'élement
	 * @param unNom       correspond au nom de l'élément
	 * @param uneQuantite correspond à la quantité de l'élément
	 * @param uneUnite    correspond à l'unité de mesure de l'élément
	 * @param unPrixAchat correspond au prix d'achat de l'élément
	 * @param unPrixVente correspond au prix de vente de l'élément
	 * @param unStockage  correspond au type de stockage de l'élément
	 * @param uneDemande  correspond au pourcentage de la demande par rapport au
	 *                    stock de l'élément
	 */
	public Element(String unCode, String unNom, int uneQuantite, String uneUnite, String unPrixAchat,
			String unPrixVente, String unStockage, int uneDemande) {
		this.code = unCode;
		this.nom = unNom;
		this.quantite = uneQuantite;
		this.unite = uneUnite;
		this.achat = unPrixAchat;
		this.vente = unPrixVente;
		this.stockage = unStockage;
		this.demande = uneDemande;
	}

	/**
	 * Constructeur par défaut
	 */
	public Element() {

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

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getAchat() {
		return achat;
	}

	public void setAchat(String achat) {
		this.achat = achat;
	}

	public String getVente() {
		return vente;
	}

	public void setVente(String vente) {
		this.vente = vente;
	}

	public String getStockage() {
		return stockage;
	}

	public void setStockage(String stockage) {
		this.stockage = stockage;
	}

	public int getDemande() {
		return demande;
	}

	public void setDemande(int demande) {
		this.demande = demande;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public int getConso() {
		return conso;
	}

	public void setConso(int conso) {
		this.conso = conso;
	}
}