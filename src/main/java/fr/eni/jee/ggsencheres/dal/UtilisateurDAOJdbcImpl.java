package fr.eni.jee.ggsencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.jee.ggsencheres.bo.Utilisateur;


public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	// instruction SQL pour récupérer toutes les infos utilisateur
	final static String SELECT_MOT_DE_PASSE = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur from UTILISATEURS WHERE pseudo = ? OR email= ?;";
	
	
	
	
	
	
	@Override

	public Utilisateur getInfosUtilsateur(String identifiant) throws  DALException {

		
		
		Utilisateur utilisateur = null;
		
		// 1ère étape qui consiste à récupérer le mot de passe saisi par l'utilisateur en fonction du pseudo ou de l'email
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_MOT_DE_PASSE);
			
			pStmt.setString(1, identifiant);
			pStmt.setString(2, identifiant);

			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				 
				int noUtilisateur = rs.getInt("no_utilisateur");
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				String motDePasse = rs.getString("mot_de_passe");
				int credit = rs.getInt("credit");
				boolean administrateur = rs.getBoolean("administrateur");
				utilisateur = new Utilisateur(
						noUtilisateur,pseudo,nom,prenom,email,telephone,rue,codePostal,ville,motDePasse,credit,administrateur);
			}
				
			


		} catch (SQLException e) {
			e.printStackTrace();

			throw new DALException("Erreur de connexion avec la base de données. Note technique : " + e.getMessage());

		}
		
		return utilisateur; // 2-on renvoie le mot de passe de la BDD
	}
	
	

}
