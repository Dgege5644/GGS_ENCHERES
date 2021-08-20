package fr.eni.jee.ggsencheres.dal;

import fr.eni.jee.ggsencheres.bo.Article;

public interface ArticleDAO {

	Article addArticle(Article articleAVendre)throws DALException;

}
