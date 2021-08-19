package fr.eni.jee.ggsencheres.bo;

public class Categorie {
	private int noCategorie;
	private String libelle;
	
	
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}


	public int getNoCategorie() {
		return noCategorie;
	}


	public String getLibelle() {
		return libelle;
	}
	
	
}
