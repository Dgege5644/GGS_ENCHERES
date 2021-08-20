package fr.eni.jee.ggsencheres.bo;

import java.time.LocalDateTime;

public class Article {
	 private int noArticle;
	 private String nomArticle;
     private String description;
     private LocalDateTime debutEnchere;
     private LocalDateTime finEnchere;
     private int prixInitial;
     private int prixVente;
     private int noUtilisateur;
     private int noCategorie;
     private String etatVente;
     private String fichierPhotoArticle;
     
     
     
     public Article(String nomArticle, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere,
			int prixInitial, int prixVente, int noUtilisateur, int noCategorie, String etatVente,
			String fichierPhotoArticle) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.debutEnchere = debutEnchere;
		this.finEnchere = finEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.etatVente = etatVente;
		this.fichierPhotoArticle = fichierPhotoArticle;
		
		
	}


 	public Article(int noArticle, String nomArticle, String description, LocalDateTime debutEnchere,
 			LocalDateTime finEnchere, int prixInitial, int prixVente, int noUtilisateur, int noCategorie,
 			String etatVente, String fichierPhotoArticle) {
 		super();
 		this.noArticle = noArticle;
 		this.nomArticle = nomArticle;
 		this.description = description;
 		this.debutEnchere = debutEnchere;
 		this.finEnchere = finEnchere;
 		this.prixInitial = prixInitial;
 		this.prixVente = prixVente;
 		this.noUtilisateur = noUtilisateur;
 		this.noCategorie = noCategorie;
 		this.etatVente = etatVente;
 		this.fichierPhotoArticle = fichierPhotoArticle;
 	}

	public Article(int noUtilisateur2, String nomArticle2, String description2, int categorie,
			String fichierPhotoArticle2, int prixInitial2, LocalDateTime debutEnchere2, LocalDateTime finEnchere2) {
		this.noUtilisateur=noUtilisateur2;
		this.nomArticle=nomArticle2;
		this.description=description2;
		this.noCategorie=categorie;
		this.fichierPhotoArticle=fichierPhotoArticle2;
		this.prixInitial=prixInitial2;
		this.debutEnchere=debutEnchere2;
		this.finEnchere=finEnchere2;
	}


	public int getNoArticle() {
		return noArticle;
	}



	public String getNomArticle() {
		return nomArticle;
	}



	public String getDescription() {
		return description;
	}



	public LocalDateTime getDebutEnchere() {
		return debutEnchere;
	}



	public LocalDateTime getFinEnchere() {
		return finEnchere;
	}



	public int getPrixInitial() {
		return prixInitial;
	}



	public int getPrixVente() {
		return prixVente;
	}



	public int getNoUtilisateur() {
		return noUtilisateur;
	}



	public int getNoCategorie() {
		return noCategorie;
	}



	public String getEtatVente() {
		return etatVente;
	}



	public String getFichierPhotoArticle() {
		return fichierPhotoArticle;
	}



	
     
    
     
}
