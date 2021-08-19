package fr.eni.jee.ggsencheres.dal;

import java.time.LocalDateTime;

import fr.eni.jee.ggsencheres.bo.Article;

public interface ArticleDAO {
	Article addArticle(int noArticle, String nomArticle, String description,
			LocalDateTime debutEnchere, LocalDateTime finEnchere, int prixInitial,
			int prixVente, int noUtilisateur, int noCategorie, String etatVente, String fichierPhotoArticle);
}
