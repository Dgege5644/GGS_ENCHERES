package fr.eni.jee.ggsencheres.bll;


import java.util.List;

import fr.eni.jee.ggsencheres.bo.Utilisateur;
import fr.eni.jee.ggsencheres.dal.DALException;
import fr.eni.jee.ggsencheres.dal.DAOFactory;
import fr.eni.jee.ggsencheres.dal.UtilisateurDAO;

public class InscriptionManager {
	
	private UtilisateurDAO utilisateurDAO;
	private Utilisateur userAcreer;
	private boolean validation;
	private boolean validationNom;
	private List<Utilisateur> listeUtilisateurs;
	
	
	public InscriptionManager() {
        this.utilisateurDAO = DAOFactory.getUtilisateurDAO(); // utilisateurDAO représente la DAL
    }
	

	/**
	* méthode qui valide l'inscription en utilisant une méthode de validation par champ et 
	* qui renvoie un utilisateur ou qui lève une BLLException qui s'affiche sur l'écran 
	* utilisateur avec l'ensemble des erreurs générées par chaque méthode
	 * @throws DALException 
	*/
	
	public Utilisateur validerNouvelUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) throws BLLException {
		try {
			// Si toutes les méthodes appelées retournent true...
		// appelle la méthode qui valide le pseudo
			if (this.validerPseudo(pseudo)&& 		
		// appelle la méthode qui valide le nom
			validationNom == true &&
		
		// appelle la méthode qui valide le prenom
			this.validerPrenom(prenom) &&
			
		// appelle la méthode qui valide l'email
			this.validerEmail(email) &&
		
		// appelle la méthode qui valide le telephone
			this.validerTelephone(telephone) &&
		
		// appelle la méthode qui valide la rue
			this.validerRue(rue) &&
		
		// appelle la méthode qui valide le codePostal
			this.validerCodePostal(codePostal) &&
		
		// appelle la méthode qui valide la ville
			this.validerVille(ville) &&
		
		// appelle la méthode qui valide le mot de passe
			this.validerMotDePasse(motDePasse)) {
			// On ajoute le userAcreer en BDD
			userAcreer=this.utilisateurDAO.addUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse)
					;
			}	
			// Sinon on lève une BLLException
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
		// Quoi qu'il en soit on retourne l'userAcreer
		return userAcreer;
	}

	/**
	 * Méthode qui valide le pseudo ou renvoie une BLLException
	 * @param pseudo
	 * @return
	 * @throws BLLException
	 */
	private boolean validerPseudo(String pseudo) throws BLLException {
		
		try {
			//Pour valider le 1er point (n'existe pas déjà en base), il faut parcourir 
			//les pseudos de toute la base de données ==> boucle forEach
			for (Utilisateur u : this.listeUtilisateurs)
				//le pseudo est validé si :
				//1- n'est pas déjà présent dans la base de données parmi l'ensemble des utilisateurs
				//2- ET n'est pas null
				//3- ET est alphanumérique
				if (pseudo != utilisateurDAO.getInfosUtilisateur(pseudo).getPseudo() && pseudo!=null && pseudo.matches("[A-Za-z0-9]+")){
					validation = true;
			}
		} catch (Exception e){
				validation = false;
				throw new BLLException(e.getMessage());
		}
			return validation;
	}
	
	
	
	private boolean validerNom(String nom) throws BLLException {
		try {
			if (nom!= null && nom.matches("[A-Za-z]")) {
				validationNom = true;
			}
		} catch (Exception e) {
				validationNom = false;
				throw new BLLException("erreurNom"+ e.getMessage());
	
		} return validationNom;
	}
	
	private boolean validerPrenom(String prenom) throws BLLException {
		try {
			if (prenom != null && prenom.matches("[A-Za-z0-9]+")) {
				validation = true;
			}
		} catch (Exception e) {
			validation = false;
			throw new BLLException(e.getMessage());

		} return validation;
		
	}
	
	private boolean validerEmail(String email)throws BLLException {
		// c'est OK si non nul, alphanumérique et contient un @ 
		//TODO vérifier qu'il y a un @
		try {
			if (email!= null && email.matches("[A-Za-z0-9]+")) {
				validation = true;
			}
		} catch (Exception e) {
			validation = false;
			throw new BLLException(e.getMessage());

		} return validation;
	}
	
	private boolean validerTelephone(String telephone)throws BLLException {
		// c'est OK si c'est nul ou si ce sont des chiffres
		try {
			if (telephone.matches("[0-9]+")) {
				validation = true;
			}
		} catch (Exception e) {
			validation = false;
			throw new BLLException(e.getMessage());

		} return validation;
	}
	
	private boolean validerRue(String rue) throws BLLException {
		// C'est OK si c'est non nul et alphanumérique + espaces
		try {
			if (rue!= null && rue.matches("[A-Za-z0-9]+")) {
				validation = true;
			}
		} catch (Exception e) {
			validation = false;
			throw new BLLException(e.getMessage());

		} return validation;
	}
	
	private boolean validerCodePostal(String codePostal)throws BLLException {
		// C'est Ok si c'est non nul,  que ce sont des chiffres et que la taille fait 5
		try {
			if (codePostal!= null && codePostal.matches("[0-9]+")&& codePostal.length()==5) {
				validation = true;
			}
		} catch (Exception e) {
			validation = false;
			throw new BLLException(e.getMessage());

		} return validation;
	}
	
	private boolean validerVille(String ville)throws BLLException {
		// c'est OK si c'est non nul et en alphanumérique
		try {
			if (ville!= null && ville.matches("[A-Za-z0-9]+")) {
				validation = true;
			}
		} catch (Exception e) {
			validation = false;
			throw new BLLException(e.getMessage());

		} return validation;
	}
	
	private boolean validerMotDePasse(String motDePasse)throws BLLException {
		// c'est OK si c'est non déjà présent en BDD, non nul et alphanumérique 	
		try {
			for (Utilisateur u : this.listeUtilisateurs)
				if (motDePasse != utilisateurDAO.getInfosUtilisateur(motDePasse).getMotDePasse() && motDePasse!= null && motDePasse.matches("[A-Za-z0-9]+")) {
				validation = true;
			}
		} catch (Exception e) {
			validation = false;
			throw new BLLException(e.getMessage());

		} return validation;
	}
	
	private List<Utilisateur> getListeUtilisateurs() throws BLLException {
		
		try {
			this.listeUtilisateurs = utilisateurDAO.selectAllUtilisateurs();
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}return this.listeUtilisateurs;
		
	}
}
