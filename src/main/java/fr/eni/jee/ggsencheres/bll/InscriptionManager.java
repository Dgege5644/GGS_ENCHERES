package fr.eni.jee.ggsencheres.bll;


import java.util.List;

import fr.eni.jee.ggsencheres.bo.Utilisateur;
import fr.eni.jee.ggsencheres.dal.DALException;
import fr.eni.jee.ggsencheres.dal.DAOFactory;
import fr.eni.jee.ggsencheres.dal.UtilisateurDAO;

public class InscriptionManager {
	
	private UtilisateurDAO utilisateurDAO;
	private Utilisateur userAcreer;
	
	
	
	
	
	
	
	 
	private List<Utilisateur> listeUtilisateurs;
	
	
	public InscriptionManager() {
        this.utilisateurDAO = DAOFactory.getUtilisateurDAO(); // utilisateurDAO représente la DAL
    }
	
	
	public Utilisateur addSansVerif (String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) throws BLLException {
		Utilisateur userNonVerifie = null;
		try {
			userNonVerifie = this.utilisateurDAO.addUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userNonVerifie;
	}

	/**
	* méthode qui valide l'inscription en utilisant une méthode de validation par champ et 
	* qui renvoie un utilisateur ou qui lève une BLLException qui s'affiche sur l'écran 
	* utilisateur avec l'ensemble des erreurs générées par chaque méthode
	 * @throws DALException 
	*/
	
	public Utilisateur creerNouvelUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, String confirmation) throws BLLException {
		try {
			// Si un des méthodes appelées retourne false ..
			if (validerPseudo(pseudo) == false || validerEmail(email) == false || validerMotDePasse(motDePasse, confirmation) == false) {
					
				// on lève une BLLException
				throw new BLLException("il y a une erreur dans un des champs saisis");
			}
			// Sinon on ajoute le userAcreer en BDD avec les paramètres récupérés du formulaire de saisie
			userAcreer=this.utilisateurDAO.addUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
				
			
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
		// Quoi qu'il en soit on retourne l'userAcreer (qui est soit null soit créé avec les infos entrées en paramètres)
		return userAcreer;
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
				//1- il est nul n'est pas déjà présent dans la base de données parmi l'ensemble des utilisateurs
				//2- ou 
				//3- ET est alphanumérique
				
				//TODO vérifier que c'est en alphanumérique
				if (pseudo == null || u != null||  !pseudo.matches("^[a-zA-Z0-9]+$")){
					throw new BLLException("le pseudo n'est pas valide");
			}
				validationPseudo = true;
		} catch (Exception e){
			throw new BLLException(e.getMessage());
		}
			return validationPseudo;
	}
	
	public boolean validerEmail(String email)throws BLLException {
		// c'est OK si non nul, alphanumérique et contient un @ 
		//TODO vérifier que c'est en alphanumérique + @
		boolean validationEmail = false;
		
		try {
			Utilisateur u = utilisateurDAO.getInfosUtilisateur(email);
			if (email== null || u!=null)/* || !email.matches("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b"))*/ {
				throw new BLLException("le nom n'est pas valide");
				
			} validationEmail = true;
		} catch (Exception e) {
			
			
			throw new BLLException(e.getMessage());

		} return validationEmail;
	}
	
	public boolean validerMotDePasse(String motDePasse, String confirmation)throws BLLException {
		// c'est OK si c'est non déjà présent en BDD, non nul et alphanumérique
		//TODO vérifier que c'est en alphanumérique
		boolean validationMotDePasse;
		try {
					
			if (motDePasse== null || !motDePasse.equals(confirmation)) {
					throw new BLLException("le mot de passe n'est pas valide");
					
			} validationMotDePasse = true;
		} catch (Exception e) {
			
			throw new BLLException(e.getMessage());

		} return validationMotDePasse;
	}
	
	public boolean validerNom(String nom) throws BLLException {
		boolean validationNom = false;
		
		try {
			//TODO vérifier que c'est en alphanumérique
			if (nom == null || !nom.matches("^[a-zA-Z0-9]+$")) {
				throw new BLLException("le nom n'est pas valide");
				
			}validationNom = true;
			
		} catch (Exception e) {
				
				throw new BLLException("erreurNom"+ e.getMessage());
	
		} return validationNom;
	}
	
	public boolean validerPrenom(String prenom) throws BLLException {
		boolean validationPrenom = false;
		try {
			//TODO vérifier que c'est en alphanumérique
			if (prenom == null)/* || !prenom.matches("[A-Za-z0-9]+"))*/ {
				throw new BLLException("le nom n'est pas valide");
				
			} validationPrenom = true;
			
		} catch (Exception e) {
			throw new BLLException(e.getMessage());

		} return validationPrenom;
		
	}
	
	
	public boolean validerTelephone(String telephone)throws BLLException {
		// c'est OK si c'est nul ou si ce sont des chiffres
		//TODO vérifier que c'est en numérique
		boolean validationTelephone;
		try {
			if (!telephone.matches("[0-9]")) {
				throw new BLLException("le téléphone n'est pas valide");
				
			} validationTelephone = true;
		} catch (Exception e) {
			//throw new BLLException(e.getMessage());
			throw new BLLException(e.getMessage());

		} return validationTelephone;
	}
	
	public boolean validerRue(String rue) throws BLLException {
		// C'est OK si c'est non nul et alphanumérique + espaces
		//TODO vérifier que c'est en alphanumérique
		boolean validationRue = false;
		try {
			if (rue == null)/* || rue.matches("[A-Za-z0-9]+"))*/ {
				throw new BLLException("la rue n'est pas valide");
				
			} validationRue = true;
		} catch (Exception e) {
			
			throw new BLLException(e.getMessage());

		} return validationRue;
	}
	
	public boolean validerCodePostal(String codePostal)throws BLLException {
		// C'est Ok si c'est non nul,  que ce sont des chiffres et que la taille fait 5
		//TODO vérifier que c'est en numérique
		boolean validationCodePostal = false;
		
		try {
			if (codePostal == null || codePostal.length()!=5)/* || !codePostal.matches("[0-9]+"))*/ {
				throw new BLLException("le code postal n'est pas valide");
				
			} validationCodePostal = true;
		} catch (Exception e) {
			
			throw new BLLException(e.getMessage());

		} return validationCodePostal;
	}
	
	public boolean validerVille(String ville)throws BLLException {
		// c'est OK si c'est non nul et en alphanumérique
		//TODO vérifier que c'est en alphanumérique
		boolean validationVille = false;
		
		try {
			if (ville == null)/* || !ville.matches("[A-Za-z0-9]+"))*/ {
				throw new BLLException("la ville n'est pas valide");
				
			} validationVille = true;
		} catch (Exception e) {
			
			throw new BLLException(e.getMessage());

		} return validationVille;
	}
	
	
	
	
}
