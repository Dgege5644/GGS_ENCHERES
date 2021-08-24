package fr.eni.jee.ggsencheres.dal;

import java.util.List;

import fr.eni.jee.ggsencheres.bo.Article;
import fr.eni.jee.ggsencheres.bo.Enchere;

public interface ArticleDAO {

	void addArticle(Article articleAVendre)throws DALException;
	void addRetrait(Article articleAVendre)throws DALException;
	List<Enchere> selectEncheresEC() throws DALException;


}
