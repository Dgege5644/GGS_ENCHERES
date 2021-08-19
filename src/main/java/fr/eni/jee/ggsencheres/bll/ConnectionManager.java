package fr.eni.jee.ggsencheres.bll;

import java.sql.SQLException;

import org.apache.catalina.User;

import fr.eni.jee.ggsencheres.bo.Utilisateur;
import fr.eni.jee.ggsencheres.dal.DALException;
import fr.eni.jee.ggsencheres.dal.DAOFactory;
import fr.eni.jee.ggsencheres.dal.UtilisateurDAO;

public class ConnectionManager {
	private UtilisateurDAO utilisateurDAO;
	private Utilisateur userEnCours;
	
	public ConnectionManager() {
        this.utilisateurDAO = DAOFactory.getUtilisateurDAO(); // utilisateurDAO représente la DAL
    }
	
	
	public Utilisateur validerConnexion(String identifiant, String motDePasse) throws BLLException {
		
		// Comparer le couple mot de passe/identifiant récupéré dans la Servlet 
		//avec le mot de passe retourné par la méthode getMotDePasse qui 
		//a pour paramètre "identifiant" 
		try {
			userEnCours=this.utilisateurDAO.getInfosUtilsateur(identifiant);
			System.out.println("utilisateur = "+ userEnCours);
			if(userEnCours==null || !userEnCours.getMotDePasse().equals(motDePasse)) {
				
			//prevoir les messsages d'erreurs	
				throw new BLLException("Cet utilisateur n'existe pas !");
			}
			
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e.getMessage());
		}
		
		return userEnCours;
		
			
		// Si mot de passe pas OK, on lève une exception et on affiche un 
		//message d'erreur dans la Connexion.jsp
		
	}	
	
	public void updateInfos(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, int no_utilisateur) {
		
		
	}
	
	
	
}
