package fr.eni.jee.ggsencheres.dal;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.jee.ggsencheres.bo.Article;
import fr.eni.jee.ggsencheres.bo.Enchere;

public interface ArticleDAO {
	
	Article addArticle(int noArticle, String nomArticle, String description,
			LocalDateTime debutEnchere, LocalDateTime finEnchere, int prixInitial,
			int prixVente, int noUtilisateur, int noCategorie, String etatVente, String fichierPhotoArticle);
	
	List<Enchere> selectAll() throws DALException;
}
