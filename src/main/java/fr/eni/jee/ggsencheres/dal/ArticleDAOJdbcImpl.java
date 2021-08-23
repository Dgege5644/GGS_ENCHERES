package fr.eni.jee.ggsencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import fr.eni.jee.ggsencheres.bo.Article;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String INSERT_INTO_RETRAITS = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES(?,?,?,?);";
	private static final String INSERT_INTO_ARTICLES_VENDUS = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)VALUES(?,?,?,?,?,?,?,?,?,?);";
	
	@Override
	public void addArticle(Article articleAVendre) throws DALException {
		
		
		try (Connection cnx = ConnectionProvider.getConnection()){
		
			PreparedStatement pSt = cnx.prepareStatement(INSERT_INTO_ARTICLES_VENDUS,PreparedStatement.RETURN_GENERATED_KEYS);
			pSt.setString(1, articleAVendre.getNomArticle());
			pSt.setString(2, articleAVendre.getDescription());
			pSt.setTimestamp(3,  Timestamp.valueOf(articleAVendre.getDebutEnchere()));
			pSt.setTimestamp(4,  Timestamp.valueOf(articleAVendre.getFinEnchere()));
			pSt.setInt(5, articleAVendre.getPrixInitial());
			pSt.setInt(6,articleAVendre.getPrixVente());
			pSt.setInt(7,articleAVendre.getNoUtilisateur());
			pSt.setInt(8,articleAVendre.getNoCategorie());
			pSt.setString(9,"CR");
			pSt.setString(10,articleAVendre.getFichierPhotoArticle());
			
			pSt.executeUpdate();
			ResultSet rs = pSt.getGeneratedKeys();
			if (rs.next()) {
				articleAVendre.setNoArticle(rs.getInt(1));
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur de connexion avec la base de données. Note technique : " + e.getMessage());
		}
		
		
	}
	public void addRetrait(Article articleAVendre) throws DALException {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pSt = cnx.prepareStatement(INSERT_INTO_RETRAITS);
			pSt.setInt(1, articleAVendre.getNoArticle());
			pSt.setString(2, articleAVendre.getRue());
			pSt.setString(3, articleAVendre.getCodePostal());
			pSt.setString(4, articleAVendre.getVille());
			

			pSt.executeUpdate();
		}catch(SQLException e) {
			throw new DALException("Erreur de connexion avec la base de données. Note technique : " + e.getMessage());
		}
		
	}
}
