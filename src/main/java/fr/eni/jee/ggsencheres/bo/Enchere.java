package fr.eni.jee.ggsencheres.bo;

import java.time.LocalDateTime;

public class Enchere {

	int noUtilisateur;
	int noArticle;
	LocalDateTime dateEnchere;
	int montantEnchere;
	
	
	public Enchere(int noUtilisateur, int noArticle, LocalDateTime dateEnchere, int montantEnchere) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}


	public int getNoUtilisateur() {
		return noUtilisateur;
	}


	public int getNoArticle() {
		return noArticle;
	}


	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}


	public int getMontantEnchere() {
		return montantEnchere;
	}


	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}


	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}


	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	
	
	
}
