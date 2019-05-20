package controller.model;

public class CodeQTE {
	private String code;
	private int quantite;

	/**
	 * Constructeur de la classe CodeQTE
	 * 
	 * @param unCode      correspond à un code d'élément
	 * @param uneQuantite correspond à la quantité d'un élément
	 */
	public CodeQTE(String unCode, int uneQuantite) {
		this.code = unCode;
		this.quantite = uneQuantite;
	}

	// getter setter
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
