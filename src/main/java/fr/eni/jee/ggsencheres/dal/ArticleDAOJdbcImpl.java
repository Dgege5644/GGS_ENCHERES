package fr.eni.jee.ggsencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.jee.ggsencheres.bo.Article;
import fr.eni.jee.ggsencheres.bo.Enchere;
import fr.eni.jee.ggsencheres.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	
	private static final String INSERT_INTO_ENCHERES = "INSERT INTO ENCHERES(no_utilisateur,no_article,date_enchere,montant_enchere)VALUES(?,?,?,?)";


	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET montant_enchere=?,UTILISATEURS.pseudo=?,UTILISATEURS.credit=? "
			+ "INNER JOIN UTILISATEURS ON ENCHERES.no_utilisateur= UTILISATEURS.no_utilisateur "
			+ "WHERE ENCHERES.no_article = ?";


	private static final String SELECT_ARTICLE_BY_ID = "SELECT ARTICLES_VENDUS.no_article as no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur as no_vendeur, etat_vente, image,ENCHERES.date_enchere as date_enchere, ENCHERES.montant_enchere as montant_enchere ,ENCHERES.no_utilisateur as no_user_detenteur, CATEGORIES.no_categorie as no_categorie, CATEGORIES.libelle as libelle, UTILISATEURS.no_utilisateur as no_vendeur, prenom, nom, UTILISATEURS.pseudo as pseudo_vendeur, email, telephone, UTILISATEURS.rue as rue_v, UTILISATEURS.code_postal as code_postal_v, UTILISATEURS.ville as ville_v, mot_de_passe, UTILISATEURS.credit as credit_v, administrateur, RETRAITS.rue as retrue, RETRAITS.code_postal as retcode_postal, RETRAITS.ville as retville "
														+ "FROM ARTICLES_VENDUS "
														+ "LEFT OUTER JOIN ENCHERES ON ENCHERES.no_article = ARTICLES_VENDUS.no_article  "
														+ "LEFT OUTER JOIN UTILISATEURS ON ENCHERES.no_utilisateur = UTILISATEURS.no_utilisateur  "
														+ "INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "
														+ "INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie "
														+ "INNER JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article  WHERE ARTICLES_VENDUS.no_article=?;";


	private static final String INSERT_INTO_RETRAITS = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES(?,?,?,?);";


	//Préciser les colonnes permet d'éviter la redondance de certaines informations notamment les clés étrangères(colonnes)
	private static final String SELECT_ENCHERES_EC = "SELECT ARTICLES_VENDUS.no_article as no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, etat_vente, image, libelle, date_enchere, montant_enchere, CATEGORIES.no_categorie as no_categorie, CATEGORIES.libelle as libelle, UTILISATEURS.no_utilisateur as no_utilisateur, prenom, nom, pseudo, email, telephone, UTILISATEURS.rue as rue, UTILISATEURS.code_postal as code_postal, UTILISATEURS.ville as ville, mot_de_passe, credit, administrateur, RETRAITS.rue as retrue, RETRAITS.code_postal as retcode_postal, RETRAITS.ville as retville "
													+ "FROM ARTICLES_VENDUS "
													+ "LEFT OUTER JOIN ENCHERES ON ENCHERES.no_article = ARTICLES_VENDUS.no_article "
													+ "INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "
													+ "INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie "
													+ "INNER JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article "
													+ "WHERE ARTICLES_VENDUS.date_debut_enchere <= GETDATE() AND ARTICLES_VENDUS.date_fin_enchere > GETDATE();";
	
	private static final String SELECT_ENCHERES_CR = "SELECT ARTICLES_VENDUS.no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, etat_vente, image, libelle, date_enchere, montant_enchere, CATEGORIES.no_categorie, CATEGORIES.libelle, UTILISATEURS.no_utilisateur, prenom, nom, pseudo, email, telephone, UTILISATEURS.rue, UTILISATEURS.code_postal, UTILISATEURS.ville, UTILISATEURS.mot_de_passe, UTILISATEURS.credit, UTILISATEURS.administrateur, RETRAITS.rue, RETRAITS.code_postal, RETRAITS.ville "
													+ "FROM ARTICLES_VENDUS "
													+ "LEFT OUTER JOIN ENCHERES ON ENCHERES.no_article = ARTICLES_VENDUS.no_article "
													+ "INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "
													+ "INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie "
													+ "INNER JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article "
													+ "WHERE ARTICLES_VENDUS.date_debut_enchere < GETDATE();";
	
	private static final String SELECT_ENCHERES_VD = "SELECT ARTICLES_VENDUS.no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, etat_vente, image, libelle, date_enchere, montant_enchere, CATEGORIES.no_categorie, CATEGORIES.libelle, UTILISATEURS.no_utilisateur, prenom, nom, pseudo, email, telephone, UTILISATEURS.rue, UTILISATEURS.code_postal, UTILISATEURS.ville, UTILISATEURS.mot_de_passe, UTILISATEURS.credit, UTILISATEURS.administrateur, RETRAITS.rue, RETRAITS.code_postal, RETRAITS.ville "
													+ "FROM ARTICLES_VENDUS "
													+ "LEFT OUTER JOIN ENCHERES ON ENCHERES.no_article = ARTICLES_VENDUS.no_article "
													+ "INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "
													+ "INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie "
													+ "INNER JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article "
													+ "WHERE ARTICLES_VENDUS.date_fin_enchere < GETDATE();";



	private static final String INSERT_INTO_ARTICLES_VENDUS = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)VALUES(?,?,?,?,?,?,?,?,?,?);";

	private static final String SELECT_INFOS_USER = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?;";
													
	
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

	@Override
	public List<Enchere> selectEncheresEC() throws DALException { //TODO AJOUTER LES PARAM JAVA dans la méthode
			Enchere enchereEC = null;
			List<Enchere> listeEncheres = new ArrayList<Enchere>();
			Utilisateur userEncherisseur = null;
			Article articleEC = null;
			
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement reqSelectEncheresEC = cnx.prepareStatement(SELECT_ENCHERES_EC);
			
			ResultSet rs = reqSelectEncheresEC.executeQuery();
			
			while (rs.next()) {
				
				// La table ENCHERES est une table de jointure entre les tables ARTICLES_VENDUS et UTILISATEURS,
				// Il en va de même pour la classe Enchere
				// Nous devons récupérer toutes les informations nécessaires à la création du constructeur de la classe Enchère en bo.
				
				
				// TABLE UTILISATEURS
					int noUtilisateur			= rs.getInt("no_utilisateur");
					String pseudo				= rs.getString("pseudo");
					String nom					= rs.getString("nom");
					String prenom				= rs.getString("prenom");
					String email				= rs.getString("email");
					String telephone			= rs.getString("telephone");
					String rue					= rs.getString("rue");
					String codePostal			= rs.getString("code_postal");
					String ville				= rs.getString("ville");
					String motDePasse			= rs.getString("mot_de_passe");
					int credit					= rs.getInt("credit");
					boolean administrateur		= rs.getBoolean("administrateur");
					
				// TABLE ARTICLES_VENDUS
					int noArticle 					= rs.getInt("no_article");
					String nomArticle				= rs.getString("nom_article");
				    String description				= rs.getString("description");
				    LocalDateTime dateDebutEnchere 	= LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),rs.getTime("date_debut_enchere").toLocalTime()); //Le type DateTime (SQL) est converti en 2 variables: LocalDate et LocalTime
				    LocalDateTime dateFinEnchere 	= LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),rs.getTime("date_fin_enchere").toLocalTime());
				    int prixInitial					= rs.getInt("prix_initial");
				    int prixVente					= rs.getInt("prix_vente");
				    String etatVente				= rs.getString("etat_vente");
				    String fichierPhotoArticle		= rs.getString("image");
				    
				 // TABLE CATEGORIES
				    int noCategorie					= rs.getInt("no_categorie");
				    String libelle					= rs.getString("libelle");
				    
				 // TABLE RETRAITS 
				    String rueRetrait				= rs.getString("retrue");
				    String codePostalRetrait		= rs.getString("retcode_postal");
				    String villeRetrait				= rs.getString("retville");
				    
				 // TABLE ENCHERES   
					int montantEnchere 				= rs.getInt("prix_initial"); //TODO Mettre le prix_initial? Si oui quelles conséquences pour les futures enchères?
					
					LocalDateTime dateEnchere 		= LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),rs.getTime("date_debut_enchere").toLocalTime());
						
					
					userEncherisseur 	= new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
					
					articleEC 			= new Article(noArticle, nomArticle, description, dateDebutEnchere, dateFinEnchere, prixInitial, prixVente, noUtilisateur, noCategorie, etatVente, fichierPhotoArticle, rueRetrait, codePostalRetrait, villeRetrait);
					
					enchereEC			= new Enchere(userEncherisseur, articleEC, dateEnchere, montantEnchere);
					
					
					listeEncheres.add(enchereEC);
			}
			
		}catch(SQLException e) {			
			throw new DALException("Erreur de connexion avec la base de données. Note technique : " + e.getMessage());
		}
				return listeEncheres;
		
	}
	/**
	 * Méthode qui permet de sélectionner toutes les infos concernant un article 
	 * mis aux enchères selon son noArticle (en utilsant les liens entre les
	 * différentes tables de la BDD
	 * Ellle prend un noArticle (récupéré dans la jsp) en paramètre et retourne
	 * une enchereEC de type Enchere qui sera utilisée dans la jsp
	 */
	public Article selectArticleById(int noArticle) throws DALException {
		Article articleEC = null;
		Enchere enchereEC = null;
		Utilisateur userVendeur=null;
		Utilisateur userAcheteur = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pSt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);
			
			pSt.setInt(1, noArticle);
			
			ResultSet rs = pSt.executeQuery();
			
			while (rs.next()) {
				//TABLE ARTICLES_VENDUS
				String nomArticle				= rs.getString("nom_article");
			    String description				= rs.getString("description");
			    LocalDateTime dateDebutEnchere 	= LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),rs.getTime("date_debut_enchere").toLocalTime()); //Le type DateTime (SQL) est converti en 2 variables: LocalDate et LocalTime
			    LocalDateTime dateFinEnchere 	= LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),rs.getTime("date_fin_enchere").toLocalTime());
			    int prixInitial					= rs.getInt("prix_initial");
			    int prixVente					= rs.getInt("prix_vente");
			    int noVendeur					=rs.getInt("no_vendeur");
			    String etatVente				= rs.getString("etat_vente");
			    String fichierPhotoArticle		= rs.getString("image");
			    
			    // TABLE ENCHERES   
				LocalDateTime dateEnchere 		= LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),rs.getTime("date_fin_enchere").toLocalTime());
				int montantEnchere 				= rs.getInt("montant_enchere"); //TODO Mettre le prix_initial? Si oui quelles conséquences pour les futures enchères?   
				int noUserDetenteur				= rs.getInt("no_user_detenteur");
				
				// TABLE CATEGORIES
			    int noCategorie					= rs.getInt("no_categorie");
			    String libelle					= rs.getString("libelle");
			    
				//table utilisateur vendeur
				int noUtilisateurVD			= rs.getInt("no_vendeur");
				String pseudoVD				= rs.getString("pseudo_vendeur");
				String nomV					= rs.getString("nom");
				String prenomV				= rs.getString("prenom");
				String emailV				= rs.getString("email");
				String telephoneV			= rs.getString("telephone");
				String rueV				    = rs.getString("rue_v");
				String codePostalV			= rs.getString("code_postal_v");
				String villeV				= rs.getString("ville_v");
				String motDePasseV			= rs.getString("mot_de_passe");
				int creditV					= rs.getInt("credit_v");
				boolean administrateurV		= rs.getBoolean("administrateur");
				
				// TABLE UTILISATEURS as acheteurs
				int noAcheteur				= rs.getInt("no_acheteur");
				String pseudoAcheteur	= rs.getString("pseudo_acheteur");
				String nom					= rs.getString("nom");
				String prenom				= rs.getString("prenom");
				String email				= rs.getString("email");
				String telephone			= rs.getString("telephone");
				String rue					= rs.getString("rue_v");
				String codePostal			= rs.getString("code_postal_v");
				String ville				= rs.getString("ville_v");
				String motDePasse			= rs.getString("mot_de_passe");
				int credit					= rs.getInt("credit_v");
				boolean administrateur		= rs.getBoolean("administrateur");
			
			    // TABLE RETRAITS 
			    String rueRetrait				= rs.getString("retrue");
			    String codePostalRetrait		= rs.getString("retcode_postal");
			    String villeRetrait				= rs.getString("retville");
			   
				
				
				// On affecte à une variable userEncherisseur de type Utilisateur l'ensemble des infos dont on aura besoin en jsp
				//userEncherisseur 	= new Utilisateur(noUtilisateurEC, pseudoEncherisseur, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
			    userVendeur		= new Utilisateur(noUtilisateurVD, pseudoVD, nomV, prenomV, emailV, telephoneV, rueV, codePostalV, villeV, motDePasseV, creditV, administrateurV);
				
			    userAcheteur	= new Utilisateur(noAcheteur, pseudoAcheteur, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
			    
				// On affecte à une variable enchereEC de type Enchere l'ensemble des infos dont on aura besoin en jsp
				// elle reprend en paramètre les 2 objets créés précéndemment en plus de dateEnchere et montantEnchere
				enchereEC		= new Enchere(noUserDetenteur, noArticle, dateEnchere, montantEnchere);
				
				// On affecte à une variable articleEC de type Article l'ensemble des infos dont on aura besoin en jsp
				// le noArticle est récupéré dans le paramètres de la méthode (il arrive de la jsp)
				articleEC 		= new Article(noArticle, nomArticle, description, noCategorie, libelle, prixInitial, prixVente, dateDebutEnchere, dateFinEnchere, etatVente, fichierPhotoArticle, rueRetrait, codePostalRetrait, villeRetrait, enchereEC);
				
				
			}
			
		}catch(SQLException e) {
			throw new DALException("erreur dans la récupération d'une enchère en fonction du noArticle");
		}
		return articleEC;
	}
	

	
//	public void updateEnchereEC(int montantEnchere, int noArticle, int noEncherisseur, int creditEncherisseur) throws DALException {
//		
//		try(Connection cnx = ConnectionProvider.getConnection()){
//			PreparedStatement pSt= cnx.prepareStatement(UPDATE_ENCHERE);
//			pSt.setInt(1, montantEnchere);
//			pSt.setInt(2, noEncherisseur);
//			pSt.setInt(3, creditEncherisseur);
//			pSt.setInt(4, noArticle);
//			pSt.executeUpdate();
//		}catch(SQLException e) {
//			throw new DALException("erreur de l'update de l'enchere");
//		}
//		
//	}
	/**
	 * Méthode qui modifie les colonnes concernées avec ce qu'elle utilise en paramètre
	 * Et qui retourne une enchereUpdated de type Enchere ????? 
	 * TODO passer la méthode en void ?????
	 */
	@Override
	public void updateEnchereEC(int noEncherisseur, int noArticle, LocalDateTime dateEnchere, int montantEnchere)
			throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pSt= cnx.prepareStatement(INSERT_INTO_ENCHERES);
			pSt.setInt(1, noEncherisseur);
			pSt.setInt(2, noArticle);
			pSt.setTimestamp(3,  Timestamp.valueOf(dateEnchere));
			pSt.setInt(4, montantEnchere);
			pSt.executeUpdate();
		}catch(SQLException e) {
			throw new DALException("erreur de l'update de l'enchere");
		}
		
		
	}
	
	/*
	public Enchere selectArticleByIdDOGET(int noArticle) throws DALException {
		Article articleEC = null;
		Enchere enchereEC = null;
		Utilisateur userVendeur=null;
		
		
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pSt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);
			
			pSt.setInt(1, noArticle);
			
			ResultSet rs = pSt.executeQuery();
			
			while (rs.next()) {
				// TABLE UTILISATEURS encherisseur
				
			
				
				//table utilisateur vendeur

				int noUtilisateurVD			= rs.getInt("no_vendeur");

				String pseudoVD				= rs.getString("pseudo_vendeur");
				String nomV					= rs.getString("nom");
				String prenomV				= rs.getString("prenom");
				String emailV				= rs.getString("email");
				String telephoneV			= rs.getString("telephone");
				String rueV				    = rs.getString("rue_v");
				String codePostalV			= rs.getString("code_postal_v");
				String villeV				= rs.getString("ville_v");
				String motDePasseV			= rs.getString("mot_de_passe");
				int creditV					= rs.getInt("credit_v");
				boolean administrateurV		= rs.getBoolean("administrateur");
				//TABLE ARTICLES_VENDUS
				String nomArticle				= rs.getString("nom_article");
			    String description				= rs.getString("description");
			    int noVendeur					= rs.getInt("no_vendeur");// TODO 
			    LocalDateTime dateDebutEnchere 	= LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),rs.getTime("date_debut_enchere").toLocalTime()); //Le type DateTime (SQL) est converti en 2 variables: LocalDate et LocalTime
			    LocalDateTime dateFinEnchere 	= LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),rs.getTime("date_fin_enchere").toLocalTime());
			    int prixInitial					= rs.getInt("prix_initial");
			    int prixVente					= rs.getInt("prix_vente");
			    String etatVente				= rs.getString("etat_vente");
			    String fichierPhotoArticle		= rs.getString("image");
			       
			    // TABLE CATEGORIES
			    int noCategorie					= rs.getInt("no_categorie");
			    String libelle					= rs.getString("libelle");
			    
			    // TABLE RETRAITS 
			    String rueRetrait				= rs.getString("retrue");
			    String codePostalRetrait		= rs.getString("retcode_postal");
			    String villeRetrait				= rs.getString("retville");
			    // TABLE ENCHERES   
				int montantEnchere 				= rs.getInt("prix_initial"); //TODO Mettre le prix_initial? Si oui quelles conséquences pour les futures enchères?
				
				LocalDateTime dateEnchere 		= LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),rs.getTime("date_fin_enchere").toLocalTime());
				
				
				// On affecte à une variable userEncherisseur de type Utilisateur l'ensemble des infos dont on aura besoin en jsp
				
				userVendeur			= new Utilisateur(noUtilisateurVD, pseudoVD, nomV, prenomV, emailV, telephoneV, rueV, codePostalV, villeV, motDePasseV, creditV, administrateurV);
				// On affecte à une variable articleEC de type Article l'ensemble des infos dont on aura besoin en jsp
				// le noArticle est récupéré dans le paramètres de la méthode (il arrive de la jsp)
				articleEC 			= new Article(noArticle, nomArticle, description, dateDebutEnchere, dateFinEnchere, prixInitial, prixVente, noVendeur, noCategorie,libelle, etatVente, fichierPhotoArticle, rueRetrait, codePostalRetrait, villeRetrait);
				enchereEC			= new Enchere(userVendeur, articleEC, dateEnchere, montantEnchere);
				articleEC.setEnchere()
				// On affecte à une variable enchereEC de type Enchere l'ensemble des infos dont on aura besoin en jsp
				// elle reprend en paramètre les 2 objets créés précéndemment en plus de dateEnchere et montantEnchere
				//enchereEC			= new Enchere(userVendeur, articleEC, dateEnchere, montantEnchere);
				
				
			}
			
		}catch(SQLException e) {
			throw new DALException("erreur dans la récupération d'une enchère en fonction du noArticle");
		}
		return enchereEC;
	}
*/
	
}
	
