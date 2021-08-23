package fr.eni.jee.ggsencheres.bo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Enchere {
	
	// Se référer aux constituants de la table de jointure(Les 2 clés primaires) pour créer la classe
	
	Utilisateur utilisateur;
	Article article;
	LocalDate dateEnchere;
	LocalTime heureEnchere;
	int montantEnchere;
	
	public Enchere(Utilisateur utilisateur, Article article, LocalDate dateEnchere, LocalTime heureEnchere,
			int montantEnchere) {
		super();
		this.utilisateur = utilisateur;
		this.article = article;
		this.dateEnchere = dateEnchere;
		this.heureEnchere = heureEnchere;
		this.montantEnchere = montantEnchere;
		
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public Article getArticle() {
		return article;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public LocalTime getHeureEnchere() {
		return heureEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public void setHeureEnchere(LocalTime heureEnchere) {
		this.heureEnchere = heureEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	
}
