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
     private String libelle;
     private String etatVente;
     private String fichierPhotoArticle;
     private String rue;
     private String codePostal;
     private String ville;
     private Enchere enchereEc;
     private Utilisateur userVendeur;
	 private Utilisateur userAcheteur;
     
     
     
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
	

	public Article(int noArticle, String nomArticle, String description, LocalDateTime debutEnchere,
			LocalDateTime finEnchere, int prixInitial, int prixVente, int noUtilisateur, int noCategorie,
			String etatVente, String fichierPhotoArticle, String rue, String codePostal, String ville) {
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
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	
	public Article(int noUtilisateur, String nomArticle, String description, int categorie,
			String fichierPhotoArticle, int prixInitial, LocalDateTime debutEnchere, LocalDateTime finEnchere,
			String rue, String codePostal, String ville) {
		this.noUtilisateur=noUtilisateur;
		this.nomArticle = nomArticle;
		this.description = description;
		this.debutEnchere = debutEnchere;
		this.finEnchere = finEnchere;
		this.prixInitial = prixInitial;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = categorie;
		this.fichierPhotoArticle = fichierPhotoArticle;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}


	public Article(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, int prixInitial, int prixVente, int noVendeur, int noCategorie,
			String libelle, String etatVente, String fichierPhotoArticle, String rueRetrait,
			String codePostalRetrait, String villeRetrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.debutEnchere = dateDebutEnchere;
		this.finEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noUtilisateur = noVendeur;
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.etatVente = etatVente;
		this.fichierPhotoArticle = fichierPhotoArticle;
		this.rue = rueRetrait;
		this.codePostal = codePostalRetrait;
		this.ville = villeRetrait;
	}
	
	


	public Article(int noArticle, String nomArticle, String description, int noCategorie, String libelle,
			int prixInitial, int prixVente, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere,
			String etatVente, String fichierPhotoArticle, String rueRetrait, String codePostalRetrait,
			String villeRetrait,Enchere enchereEC, Utilisateur userVendeur, Utilisateur userAcheteur) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.noCategorie = noCategorie;
		this.debutEnchere = dateDebutEnchere;
		this.finEnchere = dateFinEnchere;
		this.libelle= libelle;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.fichierPhotoArticle = fichierPhotoArticle;
		this.etatVente = etatVente;
		this.rue = rueRetrait;
		this.codePostal = codePostalRetrait;
		this.ville = villeRetrait;
		this.enchereEc = enchereEC;
		this.userVendeur = userVendeur;
		this.userAcheteur = userAcheteur;
	}


	public String getLibelle() {
		return libelle;
	}


	public String getRue() {
		return rue;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public String getVille() {
		return ville;
	}


	public int getNoArticle() {
		return noArticle;
	}

	

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
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
