package fr.eni.jee.ggsencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.jee.ggsencheres.bo.Article;
import fr.eni.jee.ggsencheres.bo.Enchere;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String SELECT_ALL_ENCHERE = "SELECT ";

	@Override
	public Article addArticle(int noArticle, String nomArticle, String description, LocalDateTime debutEnchere,
			LocalDateTime finEnchere, int prixInitial, int prixVente, int noUtilisateur, int noCategorie,
			String etatVente, String fichierPhotoArticle) {
		Article article=null;
		
		
		return article;
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		List<Enchere> listeEncheres = new ArrayList<>();
		Enchere enchere;
		
		//try (Connection cnx = ConnectionProvider.getConnection()){
		//	PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL_ENCHERE);
		//}
		
		return listeEncheres;
	}

}
