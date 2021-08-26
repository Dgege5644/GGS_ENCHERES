package fr.eni.jee.ggsencheres.bll;


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
	

	private void validerInfosCreationUtilisateur (String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, String confirmation) throws BLLException {
	
			//on initialise le conteneur des erreurs (il est donc vide à ce moment là)
			exceptions = new BLLException();
			// Si la méthode appelée retourne false ...
			if (validerCreationPseudo(pseudo) == false){  	
				// on lève une BLLException
				exceptions.addMessage("il y a une erreur dans la saisie du pseudo");
			}
			//Si la méthode appelée retourne false ...	
			if (validerCreationEmail(email) == false) {
				// on lève une BLLException
				exceptions.addMessage("il y a une erreur dans la saisie de l'email");
			}
			//Si la méthode appelée retourne false ...		
			if (validerCreationMotDePasse(motDePasse, confirmation) == false) {
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
	 * méthode qui crée un nouvel utilisateur en utilisant la méthode de validation 
	 * ci- dessus (nommée validerInfosCreationUtilisateur) qui valide la création
	 * des champs (pseudo, email et motDePasse) ou qui lève des BLLException
	 * qui s'affichent en liste sur inscription.jsp
	 * @return userAcreer de type Utilisateur
	 * @throws BLLException 
	 */
	public Utilisateur creerNouvelUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, String confirmation) throws BLLException {
		Utilisateur userAcreer = null;
		try {
			// on appelle la méthode validerInfosUtilisateur
			this.validerInfosCreationUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, confirmation);
			
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

	/**
	 * méthode qui valide la modification possible d'un profil en appelant les 
	 * méthodes de validation sur chacun des champs à controler (pseudo, email, 
	 * mot de passe) ou qui lève des BLLException qui s'afficheront en liste 
	 * sur afficherMonProfil.jsp
	 * @return rien
	 * @throws BLLException 
	 */
	private void validerInfosModifUtilisateur(int noUtilisateur, String pseudo, String pseudoActuel, String nom, String prenom, String email, 
			String emailActuel, String telephone, String rue, String codePostal, String ville, String motDePasseActuel, 
			String newMotDePasse, String confirmation2) throws BLLException {
	
			//on initialise le conteneur des erreurs (il est donc vide à ce moment là)
			exceptions = new BLLException();
			// Si la méthode appelée retourne false ...
			if (validerModifPseudo(noUtilisateur, pseudo, pseudoActuel) == false){  	
				// on lève une BLLException
				exceptions.addMessage("il y a une erreur dans la saisie du pseudo");
			}
			//Si la méthode appelée retourne false ...	
			if (validerModifEmail(noUtilisateur, email, emailActuel) == false) {
				// on lève une BLLException
				exceptions.addMessage("il y a une erreur dans la saisie de l'email");
			}
			//Si la méthode appelée retourne false ...		
			if (validerModifMotDePasse(motDePasseActuel, newMotDePasse, confirmation2) == false) {
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
	 * méthode qui modifie un utilisateur en utilisant la méthode de validation 
	 * ci- dessus (nommée validerInfosModifUtilisateur) qui valide la création
	 * des champs (pseudo, email et motDePasse) ou qui lève des BLLException
	 * qui s'affichent en liste sur inscription.jsp
	 * @return userAcreer de type Utilisateur
	 * @throws BLLException 
	 */
	public void modifierUtilisateur(int noUtilisateur, String pseudo, String pseudoActuel, String nom, String prenom, String email, String emailActuel, String telephone, String rue,
			String codePostal, String ville, String motDePasseActuel, String newMotDePasse, String confirmationNewMdp) throws BLLException {
	 
	 	try {
	 		// on appelle la méthode validerInfosUtilisateur
	 		this.validerInfosModifUtilisateur(noUtilisateur, pseudo, pseudoActuel, nom, prenom, email, emailActuel, telephone, rue, codePostal, ville, motDePasseActuel, newMotDePasse, confirmationNewMdp);
	 		
	 		// si la méthode valide, on modifie dans la BDD, l'utilisateur dont le noUtilisateur est remonté en 
	 		//paramètre, avec les paramètres récupérés du formulaire de saisie
	 		this.utilisateurDAO.updateInfosUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasseActuel, newMotDePasse);
	 		
		} catch (DALException e) {
			exceptions.addMessage(e.getMessage());
	 		throw exceptions;
	 	}
	 
	}
	/**
	 * Méthode qui valide la création du pseudo ou renvoie une BLLException
	 * @param pseudo
	 * @return validationPseudo
	 * @throws BLLException
	 */
	public boolean validerCreationPseudo(String pseudo) throws BLLException {
		boolean validationPseudo=false;
		try {
			//On cherche si un utilisateur de la BDD existe avec ce pseudo
				Utilisateur u = utilisateurDAO.getInfosUtilisateur(pseudo);
				
				//le pseudo ne peut pas etre validé si :
				//1- il est nul 
				//2- un utilisateur existe avec ce pseudo
				//3- ou n'est pas alphanumérique
				if (pseudo == null || u!=null||  !pseudo.matches("^[a-zA-Z0-9]+$")){
					exceptions.addMessage("il y a une erreur dans la saisie du pseudo");
			}
				validationPseudo = true;
		} catch (Exception e){
			throw new BLLException(e.getMessage());
		}
			return validationPseudo;
	}
	
	/**
	 * Méthode qui valide la création de l'email ou renvoie une BLLException
	 * @param email
	 * @return validationEmail
	 * @throws BLLException
	 */
	public boolean validerCreationEmail(String email)throws BLLException {
		
		boolean validationEmail = false;
		
		try {
			//On cherche si un utilisateur de la BDD esxiste avec cet email
			Utilisateur u = utilisateurDAO.getInfosUtilisateur(email);
			
			if (email== null || u!=null) {
				exceptions.addMessage("il y a une erreur dans la saisie de l'email");
				
			} validationEmail = true;
		} catch (Exception e) {
			
			
			throw new BLLException(e.getMessage());

		} return validationEmail;
	}
	/**
	 * Méthode qui valide la création du mot de passe ou renvoie une BLLException
	 * @param motDePasse et confirmation
	 * @return validationMotDePasse
	 * @throws BLLException
	 */
	public boolean validerCreationMotDePasse(String motDePasse,String confirmation)throws BLLException {

		boolean validationMotDePasse;
		try {
			
			// conditions pour que la création soit impossible lors de l'inscription:
			// motDePasse est nul ou motDePasse non égal à confirmation
			if (motDePasse== null || !motDePasse.equals(confirmation)) {
				exceptions.addMessage("il y a une erreur dans la saisie du mot de passe");
					
			} validationMotDePasse = true;
		} catch (Exception e) {
			
			throw new BLLException(e.getMessage());

		} return validationMotDePasse;
	}
	
	/**
	 * Méthode qui valide la modif du pseudo ou renvoie une BLLException
	 * @param noUtilisateur et pseudo
	 * @return validationPseudo
	 * @throws BLLException
	 */
	public boolean validerModifPseudo(int noUtilisateur, String pseudo, String pseudoActuel) throws BLLException {
		boolean validationPseudo=false;
		try {
			//On cherche si un utilisateur de la BDD esxiste avec ce pseudo
				Utilisateur u = utilisateurDAO.getInfosUtilisateur(pseudoActuel);
				// on crée une variable de type boolean pour savoir si la modf est possible ou non
				boolean modifPossible = true;
				// conditions pour que la modif soit impossible en UPDATE:
				// un utilisateur existe avec ce pseudo ET son noUtilisateur n'est pas celui récupéré en paramètre
				if (!(noUtilisateur!=0 && u !=null && noUtilisateur == u.getNo_utilisateur())) {
					modifPossible = false;
				}
//				// conditions pour que la modif soit impossible en INSERT:
//				// un utilisateur existe avec cet email ET l'utilisateur n'existe pas encore (cad noUtilisateur ==0)
//				if (noUtilisateur == 0 && u!= null) {
//					modifPossible= false;
//				}
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
	/**
	 * Méthode qui valide la modif de l'email ou renvoie une BLLException
	 * @param noUtilisateur et email
	 * @return validationEmail
	 * @throws BLLException
	 */
	public boolean validerModifEmail(int noUtilisateur, String email, String emailActuel)throws BLLException {
		
		boolean validationEmail = false;
		
		try {
			//On cherche si un utilisateur de la BDD esxiste avec cet email
			Utilisateur u = utilisateurDAO.getInfosUtilisateur(emailActuel);
			// on crée une variable de type boolean pour savoir si la modf est possible ou non
			boolean modifPossible = true;
			// conditions pour que la modif soit impossible en UPDATE:
			// un utilisateur existe avec cet email ET son noUtilisateur n'est pas celui récupéré en paramètre
			if (!(noUtilisateur!=0 && u !=null && noUtilisateur == u.getNo_utilisateur())) {
				modifPossible = false;
			}
//			// conditions pour que la modif soit impossible en INSERT:
//			// un utilisateur existe avec cet email ET l'utilisateur n'existe pas encore (cad noUtilisateur ==0)
//			if (noUtilisateur == 0 && u!= null) {
//				modifPossible= false;
//			}
//			
			if (email== null || !modifPossible) {
				exceptions.addMessage("il y a une erreur dans la saisie de l'email");
				
			} validationEmail = true;
		} catch (Exception e) {
			
			
			throw new BLLException(e.getMessage());

		} return validationEmail;
	}
	
	
	/**
	 * Méthode qui valide la modif de l'email ou renvoie une BLLException
	 * @param noUtilisateur et email
	 * @return validationEmail
	 * @throws BLLException
	 */
	private boolean validerModifMotDePasse(String motDePasseActuel, String newMotDePasse, String confirmationNewMdp) throws BLLException {
		boolean validationMotDePasse;
		try {
			
			// conditions pour que la modification soit impossible :
			// motDePasse est nul ou motDePasse non égal à confirmation
			if (motDePasseActuel == null && !newMotDePasse.equals(confirmationNewMdp)) {
				exceptions.addMessage("il y a une erreur dans la saisie du nouveau mot de passe");
					
			} validationMotDePasse = true;
		} catch (Exception e) {
			
			throw new BLLException(e.getMessage());

		} return validationMotDePasse;
	}
	
	
	// 2 autres méthodes de validation sur téléphone et code postal, non utilisées
	// car non demandées par le client
	
	/*
	 * Méthode de validation du téléphone
	 */
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
	
	/*
	 * Méthode de validation du téléphone
	 */
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
	
	public Utilisateur afficherInfosVendeur(int no_utilisateur) throws BLLException{
		Utilisateur userVendeur = null;
		
		try {
			
			userVendeur = utilisateurDAO.selectUserByID(no_utilisateur);
			
		}catch(DALException e) {
			throw new BLLException("Erreur dans la méthode afficherInfosVendeur. Note technique:" + e.getMessage()); 
		}
		return userVendeur;
	}
	
	public void supprimerUtilisateur(int no_utilisateur) throws BLLException{
		Utilisateur userASupprimer = null;
	
		try {
			
			this.utilisateurDAO.deleteUtilisateur(no_utilisateur);
		
		}catch(DALException e) {
			
			throw new BLLException("Erreur dans la méthode supprimerUtilisateur. Note technique:" + e.getMessage());
		}
		
	}
	
}


