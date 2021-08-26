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
	Enchere selectArticleById(int noArticle)throws DALException;
	void updateEnchereEC(int montantEnchere, int noArticle, int noEncherisseur, int creditEncherisseur) throws DALException;
	void insertIntoEncheres(int noEncherisseur, int noArticle, LocalDateTime dateEnchere, int montantEnchere)throws DALException;

}
