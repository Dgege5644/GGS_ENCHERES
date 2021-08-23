package fr.eni.jee.ggsencheres.bll;


import java.util.List;

import fr.eni.jee.ggsencheres.bo.Utilisateur;
import fr.eni.jee.ggsencheres.dal.DALException;
import fr.eni.jee.ggsencheres.dal.DAOFactory;
import fr.eni.jee.ggsencheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;
	private BLLException exceptions;	

	
	
	public UtilisateurManager() {
        this.utilisateurDAO = DAOFactory.getUtilisateurDAO(); // utilisateurDAO représente la DAL
    }
	

	private void validerInfosUtilisateur (int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, String newMotDePasse, String confirmation) throws BLLException {
	
			//on initialise le conteneur des erreurs (il est donc vide à ce moment là)
			exceptions = new BLLException();
			// Si la méthode appelée retourne false ...
			if (validerPseudo(noUtilisateur, pseudo) == false){  	
				// on lève une BLLException
				exceptions.addMessage("il y a une erreur dans la saisie du pseudo");
			}
			//Si la méthode appelée retourne false ...	
			if (validerEmail(noUtilisateur, email) == false) {
				// on lève une BLLException
				exceptions.addMessage("il y a une erreur dans la saisie de l'email");
			}
			//Si la méthode appelée retourne false ...		
			if (validerMotDePasse(motDePasse, newMotDePasse, confirmation) == false) {
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
			this.validerInfosUtilisateur(0, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, null, confirmation);
			
			// si la méthode valide, on ajoute le userAcreer en BDD avec les paramètres récupérés du formulaire de saisie
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

	public void modifierUtilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, String newMotDePasse, String confirmation) throws BLLException {
	 
	 	try {
	 		// on appelle la méthode validerInfosUtilisateur
	 		this.validerInfosUtilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, newMotDePasse, confirmation);
	 		
	 		// si la méthode valide, on modifie dans la BDD, l'utilisateur dont le noUtilisateur est remonté en 
	 		//paramètre, avec les paramètres récupérés du formulaire de saisie
	 		this.utilisateurDAO.updateInfosUtilisateur(noUtilisateur);
	 		
		} catch (DALException e) {
			exceptions.addMessage(e.getMessage());
	 		throw exceptions;
	 	}
	 
	}
	
	
	/**
	 * Méthode qui valide le pseudo ou renvoie une BLLException
	 * @param pseudo
	 * @return
	 * @throws BLLException
	 */
	public boolean validerPseudo(int noUtilisateur, String pseudo) throws BLLException {
		boolean validationPseudo=false;
		try {
			//On cherche si un utilisateur de la BDD esxiste avec ce pseudo
				Utilisateur u = utilisateurDAO.getInfosUtilisateur(pseudo);
				// on crée une variable de type boolean pour savoir si la modf est possible ou non
				boolean modifPossible = true;
				// conditions pour que la modif soit impossible en UPDATE:
				// un utilisateur existe avec ce pseudo ET son noUtilisateur n'est pas celui récupéré en paramètre
				if (!(noUtilisateur!=0 && u !=null && noUtilisateur == u.getNo_utilisateur())) {
					modifPossible = false;
				}
				// conditions pour que la modif soit impossible en INSERT:
				// un utilisateur existe avec cet email ET l'utilisateur n'existe pas encore (cad noUtilisateur ==0)
				if (noUtilisateur == 0 && u!= null) {
					modifPossible= false;
				}
				//le pseudo ne peut pas etre validé si :
				//1- il est nul 
				//2- ou la modif n'est pas possible
				//3- ou n'est pas alphanumérique
				if (pseudo == null || !modifPossible||  !pseudo.matches("^[a-zA-Z0-9]+$")){
					exceptions.addMessage("il y a une erreur dans la saisie du pseudo");
			}
				validationPseudo = true;
		} catch (Exception e){
			throw new BLLException(e.getMessage());
		}
			return validationPseudo;
	}
	
	public boolean validerEmail(int noUtilisateur, String email)throws BLLException {
		
		boolean validationEmail = false;
		
		try {
			//On cherche si un utilisateur de la BDD esxiste avec cet email
			Utilisateur u = utilisateurDAO.getInfosUtilisateur(email);
			// on crée une variable de type boolean pour savoir si la modf est possible ou non
			boolean modifPossible = true;
			// conditions pour que la modif soit impossible en UPDATE:
			// un utilisateur existe avec cet email ET son noUtilisateur n'est pas celui récupéré en paramètre
			if (!(noUtilisateur!=0 && u !=null && noUtilisateur == u.getNo_utilisateur())) {
				modifPossible = false;
			}
			// conditions pour que la modif soit impossible en INSERT:
			// un utilisateur existe avec cet email ET l'utilisateur n'existe pas encore (cad noUtilisateur ==0)
			if (noUtilisateur == 0 && u!= null) {
				modifPossible= false;
			}
			
			if (email== null || !modifPossible) {
				exceptions.addMessage("il y a une erreur dans la saisie de l'email");
				
			} validationEmail = true;
		} catch (Exception e) {
			
			
			throw new BLLException(e.getMessage());

		} return validationEmail;
	}
	
	public boolean validerMotDePasse(String motDePasse, String newMotDePasse, String confirmation)throws BLLException {

		boolean validationMotDePasse;
		try {
			
			// on crée une variable de type boolean pour savoir si la modf est possible ou non
			boolean modifPossible = true;
			// conditions pour que la modif soit impossible en UPDATE:
			// newMotDePasse différent de confirmation
			if (!newMotDePasse.equals(confirmation)) {
				exceptions.addMessage("il y a une erreur dans la saisie du mot de passe");
			}
			
			// conditions pour que la modif soit impossible en INSERT:
			// motDePasse est nul ou motDePasse non égal à confirmation
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
