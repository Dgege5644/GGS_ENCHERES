package fr.eni.jee.ggsencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.jee.ggsencheres.bo.Article;
import fr.eni.jee.ggsencheres.bo.Enchere;

public class ArticleDAOJdbcImpl implements ArticleDAO {


	private static final String SELECT_ALL_ENCHERES = "SELECT * FROM ENCHERES "
														+ "INNER JOIN ARTICLES_VENDUS ON ENCHERES.no_article = ARTICLES_VENDUS.no_article "
														+ "INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "
														+ "INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie;";

	private static final String INSERT_INTO_ARTICLES_VENDUS = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)VALUES(?,?,?,?,?,?,?,?,?,?);";


	@Override
	public Article addArticle(Article articleAVendre) throws DALException {
		Article article=null;
		
		try (Connection cnx = ConnectionProvider.getConnection()){
		
			PreparedStatement pSt = cnx.prepareStatement(INSERT_INTO_ARTICLES_VENDUS);
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
			
			
		}catch (SQLException e){
			throw new DALException("Erreur de connexion avec la base de données. Note technique : " + e.getMessage());
		}
		
		return article;
	}

	@Override
	public List<Enchere> selectAllEncheres() throws DALException {
		
		List<Enchere> listeEncheres = new ArrayList<>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement reqSelectAllEncheres = cnx.prepareStatement(SELECT_ALL_ENCHERES);
			
			ResultSet rs = reqSelectAllEncheres.executeQuery();
			
			while (rs.next()) {
				
					int noUtilisateur			= rs.getInt("no_utilisateur");
					int noArticle 				= rs.getInt("no_article");
					LocalDateTime dateEnchere 	= LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()),rs.getTime("date_enchere").toLocalTime());
					int montantEnchere 			= rs.getInt("montant_enchere");  // TO DO Afficher le montant de l'enchère la plus haute qui sera (UPDATE) à chaque nouvelle enchère.
								
			Enchere enchere = new Enchere(noUtilisateur, noArticle, dateEnchere, montantEnchere);
			listeEncheres.add(enchere);
			}
			
		}catch(SQLException e) {			
			throw new DALException("Erreur de connexion avec la base de données. Note technique : " + e.getMessage());
		}
				return listeEncheres;
		
		
	}
	
}
	
