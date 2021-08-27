package fr.eni.jee.ggsencheres.dal;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.jee.ggsencheres.bo.Article;
import fr.eni.jee.ggsencheres.bo.Enchere;
import fr.eni.jee.ggsencheres.bo.Utilisateur;

public interface ArticleDAO {

	void addArticle(Article articleAVendre)throws DALException;
	void addRetrait(Article articleAVendre)throws DALException;
	List<Enchere> selectEncheresEC() throws DALException;
	Article selectArticleById(int noArticle)throws DALException;
	void updateEnchereEC(int noUtilisateur, int noArticle, int montantEnchere)throws DALException;
	void addEnchere(Article articleAVendre)throws DALException;
	
}
