package fr.eni.jee.ggsencheres.dal;

import java.util.List;

import fr.eni.jee.ggsencheres.bo.Article;
import fr.eni.jee.ggsencheres.bo.Enchere;

public interface ArticleDAO {

	Article addArticle(Article articleAVendre)throws DALException;

	List<Enchere> selectAllEncheres() throws DALException;

}
