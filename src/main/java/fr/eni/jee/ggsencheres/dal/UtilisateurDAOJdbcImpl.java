package fr.eni.jee.ggsencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.jee.ggsencheres.bo.Utilisateur;


public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	
	private int no_utilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private boolean administrateur;

	// instruction SQL pour récupérer toutes les infos d'un utilisateur selon son indentifiant
	final static String SELECT_INFOS_UTILISATEUR = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur from UTILISATEURS WHERE pseudo = ? OR email= ?;";
	
	// instruction SQL pour modifier les infos utilisateur
	final static String UPDATE_INFOS_UTILISATEUR = "UPDATE UTILISATEURS set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?;";
	
	// instruction SQL pour inscrire un nouvel utilisateur
	final static String INSERT_INTO_UTILISATEURS = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?, ?,?,?,?,?,?,?,?);";
	
	// instruction SQL pour récupérer l'ensemble des utilisateurs de la BDD
	private static final String SELECT_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur from UTILISATEURS;";
	
	@Override
	/**
	 * Méthode qui ajoute un utilisateur en base de données
	 */
	public Utilisateur addUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) throws DALException {
		Utilisateur nouvelUtilisateur= null;
		
			try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(INSERT_INTO_UTILISATEURS);
			
			pStmt.setString(1, pseudo);
			pStmt.setString(2, nom);
			pStmt.setString(3, prenom);
			pStmt.setString(4, email);
			if (telephone.length() == 0) {
				pStmt.setNull(5, Types.VARCHAR);
            } else {
            	pStmt.setString(5, telephone);
            }
			pStmt.setString(6, rue);
			pStmt.setString(7, codePostal);
			pStmt.setString(8, ville);
			pStmt.setString(9, motDePasse);
			pStmt.setInt(10, 100);
			pStmt.setBoolean(11,false);
			
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion avec la base de données. Note technique : " + e.getMessage());
		}
		
		return nouvelUtilisateur;
	}
	
	
	@Override
	/**
	 * Méthode qui récupère l'ensemble des infos d'un utilisateur en fonction de l'identifiant saisi
	 */
	public Utilisateur getInfosUtilisateur(String identifiant) throws  DALException {

		Utilisateur utilisateur = null;
		
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_INFOS_UTILISATEUR);
			
			pStmt.setString(1, identifiant);
			pStmt.setString(2, identifiant);

			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				 
				no_utilisateur = rs.getInt("no_utilisateur");
				pseudo = rs.getString("pseudo");
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				email = rs.getString("email");
				telephone = rs.getString("telephone");
				rue = rs.getString("rue");
				codePostal = rs.getString("code_postal");
				ville = rs.getString("ville");
				motDePasse = rs.getString("mot_de_passe");
				credit = rs.getInt("credit");
				administrateur = rs.getBoolean("administrateur");
				
				utilisateur = new Utilisateur(
						no_utilisateur,pseudo,nom,prenom,email,telephone,rue,codePostal,ville,motDePasse,credit,administrateur);
			}
				
			


		} catch (SQLException e) {
			e.printStackTrace();

			throw new DALException("Erreur de connexion avec la base de données. Note technique : " + e.getMessage());

		}
		
		return utilisateur; // 2-on renvoie le mot de passe de la BDD
	}

	@Override
	/*
	 * Méthode qui modifie les infos d'un utilisateur selon son no_utilisateur
	 */
	public void updateInfosUtilisateur(int no_utilisateur) throws DALException {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_INFOS_UTILISATEUR);
			
			pStmt.setString(1, pseudo);
			pStmt.setString(2, nom);
			pStmt.setString(3, prenom);
			pStmt.setString(4, email);
			
			if (telephone.length() == 0) {
				pStmt.setNull(5, Types.VARCHAR);
            } else {
            	pStmt.setString(5, telephone);
            }
			
			pStmt.setString(6, rue);
			pStmt.setString(7, codePostal);
			pStmt.setString(8, ville);
			pStmt.setString(9, motDePasse);
			pStmt.setInt(10, no_utilisateur);
			
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion avec la base de données. Note technique : " + e.getMessage());
		}	
	}

	/**
	 * Méthoque qui permet d'afficher la liste de l'ensemble des utilisateurs de la BDD
	 */
	public List<Utilisateur> getListeUtilisateurs() throws DALException {
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();// liste des utilisateurs à renvoyer
		Utilisateur utilisateur;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				 
				no_utilisateur = rs.getInt("no_utilisateur");
				pseudo = rs.getString("pseudo");
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				email = rs.getString("email");
				telephone = rs.getString("telephone");
				rue = rs.getString("rue");
				codePostal = rs.getString("code_postal");
				ville = rs.getString("ville");
				motDePasse = rs.getString("mot_de_passe");
				credit = rs.getInt("credit");
				administrateur = rs.getBoolean("administrateur");
				
				utilisateur = new Utilisateur(
						no_utilisateur,pseudo,nom,prenom,email,telephone,rue,codePostal,ville,motDePasse,credit,administrateur);
				listeUtilisateurs.add(utilisateur);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DALException("Erreur de connexion avec la base de données. Note technique : " + e.getMessage());
		}
	
		return listeUtilisateurs ;
	}

	

}
