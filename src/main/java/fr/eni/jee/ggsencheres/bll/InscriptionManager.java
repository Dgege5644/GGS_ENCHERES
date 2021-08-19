package fr.eni.jee.ggsencheres.bll;


import java.util.List;

import fr.eni.jee.ggsencheres.bo.Utilisateur;
import fr.eni.jee.ggsencheres.dal.DALException;
import fr.eni.jee.ggsencheres.dal.DAOFactory;
import fr.eni.jee.ggsencheres.dal.UtilisateurDAO;

public class InscriptionManager {
	
	private UtilisateurDAO utilisateurDAO;
	private Utilisateur userAcreer;
	private boolean ok;
	private List<Utilisateur> listeUtilisateur;
	
	
	public InscriptionManager() {
        this.utilisateurDAO = DAOFactory.getUtilisateurDAO(); // utilisateurDAO représente la DAL
    }
	
	
	public InscriptionManager(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) throws BLLException {
		
	}

	/**
	* méthode qui valide l'inscription en utilisant 1 méthode de validation par champ et 
	* qui renvoie un utilisateur ou qui lève une BLLException qui s'affiche sur l'écran 
	* utilisateur avec l'ensemble des erreurs générées par chaque méthode
	*/
	
	public Utilisateur validerInfosUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) {
		userAcreer = new Utilisateur(pseudo, nom, prenom, email, rue, codePostal, ville, motDePasse, 0, ok);
		
		// appelle la méthode qui valide le pseudo
		
		
		// appelle la méthode qui valide le nom
		
		
		// appelle la méthode qui valide le prenom
		
		
		// appelle la méthode qui valide l'email
		
		
		// appelle la méthode qui valide le telephone
		
		
		// appelle la méthode qui valide la rue
		
		
		// appelle la méthode qui valide le codePostal
		
		
		// appelle la méthode qui valide la ville
		
		
		// appelle la méthode qui valide le mot de passe
		return userAcreer;
	}

	/**
	 * Méthode qui valide le pseudo ou renvoie une BLLException
	 * @param pseudo
	 * @return
	 * @throws BLLException
	 */
	private boolean validerPseudo(String pseudo) throws BLLException {
			
			//3- 1 : il faut parcourir les pseudos de toute la base de données
			
			//3 - 2 : il faut comparer avec le pseudo entré en paramètre
			
			
			//1 le pseudo est validé si : 1 le pseudo n'est pas null ET
			//2 le pseudo est en alphanumérique ET
			//3 le pseudo n'est pas déjà présent dans la base de données parmi l'ensemble
			// des utilisateurs
			if(pseudo!=null && pseudo.matches("[A-Za-z0-9]+")){
					ok = true;
				}
	
			return ok;
	}
	
	
	
	private boolean validerNom(String nom) throws BLLException {
		if (nom != null && nom.matches("[A-Za-z0-9]+")) {
				ok = true;
		} else {
				ok = false;
				throw new BLLException("Cet utilisateur n'existe pas !");

		} return ok;
	}
	
	private boolean validerPrenom(String prenom) throws BLLException {
		if (prenom != null && prenom.matches("[A-Za-z0-9]+")) {
			ok = true;
	} else {
			throw new BLLException("Cet utilisateur n'existe pas !");

	} return ok;
		
	}
	
	private boolean validerEmail(String email)throws BLLException {
		// c'est OK si non nul, alphanumérique et contient un @ 
		return ok;
	}
	
	private boolean validerTelephone(String telephone)throws BLLException {
		// c'est OK si c'est nul ou si ce sont des chiffres
		return ok;
	}
	
	private boolean validerRue(String rue) throws BLLException {
		// C'est OK si c'est non nul et alphanumérique + espaces
		return ok;
	}
	
	private boolean validerCodePostal(String codePostal)throws BLLException {
		// C'est Ok si c'est non nul,  que ce sont des chiffres et que la taille fait 5
		return ok;
	}
	
	private boolean validerVille(String ville)throws BLLException {
		// c'est OK si c'est non nul et en alphanumérique
		
		return ok;
	}
	
	private boolean validerMotDePasse(String motDePasse)throws BLLException {
		// c'est OK si c'est non nul, alphanumérique et non déjà présent en BDD
		return ok;
	}
}
