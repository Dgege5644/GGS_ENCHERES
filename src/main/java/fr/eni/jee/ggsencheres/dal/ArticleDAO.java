package fr.eni.jee.ggsencheres.dal;

import java.time.LocalDateTime;

import fr.eni.jee.ggsencheres.bo.Article;

public interface ArticleDAO {
	void addArticle(Article articleAVendre)throws DALException;
	void addRetrait(Article articleAVendre)throws DALException;
}
