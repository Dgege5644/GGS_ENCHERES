package fr.eni.jee.ggsencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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

	// instruction SQL pour récupérer toutes les infos utilisateur
	final static String SELECT_INFOS_UTILISATEUR = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur from UTILISATEURS WHERE pseudo = ? OR email= ?;";
	
	// instruction SQL pour modifier les infos utilisateur
	final static String UPDATE_INFOS_UTILISATEUR = "UPDATE UTILISATEURS set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?;";
	
	
	
	
	
	@Override

	public Utilisateur getInfosUtilsateur(String identifiant) throws  DALException {

		Utilisateur utilisateur = null;
		
		// 1ère étape qui consiste à récupérer le mot de passe saisi par l'utilisateur en fonction du pseudo ou de l'email
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
	
	

}
