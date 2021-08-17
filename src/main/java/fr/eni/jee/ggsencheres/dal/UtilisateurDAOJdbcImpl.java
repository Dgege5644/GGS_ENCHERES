package fr.eni.jee.ggsencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	final static String SELECT_MOT_DE_PASSE = "SELECT mot_de_passe from UTILISATEURS WHERE pseudo LIKE ? OR email LIKE ?;";
	
	
	private String motDePasse = null;
	@Override
	public String getMotDePasse(String identifiant) throws SQLException {
		
		// 1ère étape étape qui consiste à récupérer le mot de passe saisi par l'utilisateur en fonction du pseudo ou de l'email
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_MOT_DE_PASSE);
			pStmt.setString(1, "pseudo");
			pStmt.setString(2, "email");

			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				this.motDePasse = rs.getString("mot_de_passe");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("erreur dans la méthode getMotDePasse");
			
		}
		
		return motDePasse; // on re,nvoie le mote de passe de la BDD
	}

}
