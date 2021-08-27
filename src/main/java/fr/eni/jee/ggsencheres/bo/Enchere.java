package fr.eni.jee.ggsencheres.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Enchere {
	
	// Se référer aux constituants de la table de jointure(Les 2 clés primaires) pour créer la classe
	
	Utilisateur userEncherisseur;
	Article articleEC;
	
	
	
	public Enchere(Utilisateur userEncherisseur, Article articleEC, LocalDateTime dateEnchere, int montantEnchere) {
		super();
		this.userEncherisseur = userEncherisseur;
		this.articleEC = articleEC;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		
	}


	public int getNoAcheteur() {
		return noAcheteur;
	}


	
	

	public void setNoAcheteur(int noAcheteur) {
		this.noAcheteur = noAcheteur;
	}



	public int getNoArticle() {
		return noArticle;
	}



	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	LocalDateTime dateEnchere;
	int montantEnchere;
	int noAcheteur;
	int noArticle;
	
	
	public Enchere(int noAcheteur, int noArticle, LocalDateTime dateEnchere, int montantEnchere) {
		super();
		this.noAcheteur = noAcheteur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	

	public Utilisateur getUserEncherisseur() {
		return userEncherisseur;
	}
	public void setUserEncherisseur(Utilisateur userEncherisseur) {
		this.userEncherisseur = userEncherisseur;
	}
	public Article getArticleEC() {
		return articleEC;
	}
	public void setArticleEC(Article articleEC) {
		this.articleEC = articleEC;
	}
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	
	
	

	
}
