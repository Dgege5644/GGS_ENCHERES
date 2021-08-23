package fr.eni.jee.ggsencheres.bll;


import java.util.List;

import fr.eni.jee.ggsencheres.bo.Utilisateur;
import fr.eni.jee.ggsencheres.dal.DALException;
import fr.eni.jee.ggsencheres.dal.DAOFactory;
import fr.eni.jee.ggsencheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;
	private BLLException exceptions;	
	
	
	
	 
	private List<Utilisateur> listeUtilisateurs;
	
	
	public UtilisateurManager() {
        this.utilisateurDAO = DAOFactory.getUtilisateurDAO(); // utilisateurDAO représente la DAL
    }
	

	private void validerInfosUtilisateur (String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, String confirmation) throws BLLException {
	
			//on initialise le conteneur des erreurs (il est donc vide à ce moment là)
			exceptions = new BLLException();
			// Si la méthode appelée retourne false ...
			if (validerPseudo(pseudo) == false){  	
				// on lève une BLLException
				exceptions.addMessage("il y a une erreur dans la saisie du pseudo");
			}
			//Si la méthode appelée retourne false ...	
			if (validerEmail(email) == false) {
				// on lève une BLLException
				exceptions.addMessage("il y a une erreur dans la saisie de l'email");
			}
			//Si la méthode appelée retourne false ...		
			if (validerMotDePasse(motDePasse, confirmation) == false) {
				// on lève une BLLException
				exceptions.addMessage("il y a une erreur dans la saisie du mot de passe");
			}
			
			// si la liste d'exceptions n'est pas vide (= s'il y a eu des erreurs) on affiche 
			//la liste des execptions
			if (!exceptions.isEmpty()) {
				//on remonte les erreurs à la servlet. Le scénario en cours s'arrete !
				throw exceptions;
			}			
	}
	
	
	/**
	* méthode qui crée un nouvel utilisateur en utilisant une méthode de validation par champ et 
	* qui renvoie un utilisateur ou qui lève une BLLException qui s'affiche sur l'écran 
	* utilisateur avec l'ensemble des erreurs générées par chaque méthode
	 * @throws DALException 
	*/
	
	public Utilisateur creerNouvelUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, String confirmation) throws BLLException {
		Utilisateur userAcreer = null;
		try {
			// on appelle la méthode validerInfosUtilisateur
				this.validerInfosUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, confirmation);
			
			// Sinon on ajoute le userAcreer en BDD avec les paramètres récupérés du formulaire de saisie
			userAcreer=this.utilisateurDAO.addUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
			
			
		} catch (DALException e) {
			//en cas d'erreur sur la maniulation des données persistantes, 
			//le manager inclue ce message dans le conteneur des erreurs
			//puis propage le tout à la servlet
			exceptions.addMessage(e.getMessage());
			throw exceptions;
		}
		// si tout s'est bien passé on retourne l'userAcreer
		return userAcreer;
	}

	public Utilisateur modifierUtilisateur() {
	 return null;
	}
	
	
	/**
	 * Méthode qui valide le pseudo ou renvoie une BLLException
	 * @param pseudo
	 * @return
	 * @throws BLLException
	 */
	public boolean validerPseudo(String pseudo) throws BLLException {
		boolean validationPseudo=false;
		try {
			
			//Pour valider le 1er point (n'existe pas déjà en base), il faut parcourir 
			//les pseudos de toute la base de données ==> boucle forEach
			//for (Utilisateur u : this.listeUtilisateurs)
				Utilisateur u = utilisateurDAO.getInfosUtilisateur(pseudo);
				//le pseudo ne peut etre validé si :
				//1- il est nul 
				//2- ou n'est pas déjà présent dans la base de données parmi l'ensemble des utilisateurs
				//3- ou n'est pa alphanumérique
				if (pseudo == null || u != null||  !pseudo.matches("^[a-zA-Z0-9]+$")){
					exceptions.addMessage("il y a une erreur dans la saisie du pseudo");
			}
				validationPseudo = true;
		} catch (Exception e){
			throw new BLLException(e.getMessage());
		}
			return validationPseudo;
	}
	
	public boolean validerEmail(String email)throws BLLException {
		// c'est OK si non nul, et non déjà présente en base de données 
		boolean validationEmail = false;
		
		try {
			Utilisateur u = utilisateurDAO.getInfosUtilisateur(email);
			if (email== null || u!=null) {
				exceptions.addMessage("il y a une erreur dans la saisie de l'email");
				
			} validationEmail = true;
		} catch (Exception e) {
			
			
			throw new BLLException(e.getMessage());

		} return validationEmail;
	}
	
	public boolean validerMotDePasse(String motDePasse, String confirmation)throws BLLException {
		// c'est OK si motDePasse est bien le même que confirmation
		
		boolean validationMotDePasse;
		try {
					
			if (motDePasse== null || !motDePasse.equals(confirmation)) {
				exceptions.addMessage("il y a une erreur dans la saisie du mot de passe");
					
			} validationMotDePasse = true;
		} catch (Exception e) {
			
			throw new BLLException(e.getMessage());

		} return validationMotDePasse;
	}
	
	public boolean validerNom(String nom) throws BLLException {
		boolean validationNom = false;
		
		try {
			if (nom == null || !nom.matches("^[a-zA-Z0-9]+$")) {
				exceptions.addMessage("le nom n'est pas valide");
				
			}validationNom = true;
			
		} catch (Exception e) {
				
				throw new BLLException("erreurNom"+ e.getMessage());
	
		} return validationNom;
	}
	
	public boolean validerPrenom(String prenom) throws BLLException {
		boolean validationPrenom = false;
		try {
			
			if (prenom == null || !prenom.matches("^[a-zA-Z0-9]+$")) {
				exceptions.addMessage("le nom n'est pas valide");
				
			} validationPrenom = true;
			
		} catch (Exception e) {
			throw new BLLException(e.getMessage());

		} return validationPrenom;
		
	}
	
	
	public boolean validerTelephone(String telephone)throws BLLException {
		// c'est OK si c'est nul ou si ce sont des chiffres
		
		boolean validationTelephone;
		try {
			if (!telephone.matches("^[0-9]+$")) {
				exceptions.addMessage("le téléphone n'est pas valide");
				
			} validationTelephone = true;
		} catch (Exception e) {
			
			throw new BLLException(e.getMessage());

		} return validationTelephone;
	}
	
	public boolean validerRue(String rue) throws BLLException {
		// C'est OK si c'est non nul et alphanumérique + espaces
		
		boolean validationRue = false;
		try {
			if (rue == null || rue.matches("^[a-zA-Z0-9]+$")) {
				exceptions.addMessage("la rue n'est pas valide");
				
			} validationRue = true;
		} catch (Exception e) {
			
			throw new BLLException(e.getMessage());

		} return validationRue;
	}
	
	public boolean validerCodePostal(String codePostal)throws BLLException {
		// C'est Ok si c'est non nul,  que ce sont des chiffres et que la taille fait 5
		boolean validationCodePostal = false;
		
		try {
			if (codePostal == null || codePostal.length()!=5 || !codePostal.matches("^[0-9]+$")) {
				exceptions.addMessage("le code postal n'est pas valide");
				
			} validationCodePostal = true;
		} catch (Exception e) {
			
			throw new BLLException(e.getMessage());

		} return validationCodePostal;
	}
	
	public boolean validerVille(String ville)throws BLLException {
		// c'est OK si c'est non nul et en alphanumérique
		boolean validationVille = false;
		
		try {
			if (ville == null || !ville.matches("^[a-zA-Z0-9]+$")) {
				exceptions.addMessage("la ville n'est pas valide");
				
			} validationVille = true;
		} catch (Exception e) {
			
			throw new BLLException(e.getMessage());

		} return validationVille;
	}
	
	
	
	
}
