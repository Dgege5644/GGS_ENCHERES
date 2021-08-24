package fr.eni.jee.ggsencheres.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Enchere {
	
	// Se référer aux constituants de la table de jointure(Les 2 clés primaires) pour créer la classe
	
	Utilisateur utilisateur;
	Article article;
	LocalDateTime dateEnchere;
	int montantEnchere;
	
	public Enchere(Utilisateur utilisateur, Article article, LocalDateTime dateEnchere, int montantEnchere) {
		super();
		this.utilisateur = utilisateur;
		this.article = article;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public Article getArticle() {
		return article;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}


	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	
}
