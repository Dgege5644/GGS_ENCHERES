package fr.eni.jee.ggsencheres.bll;


import fr.eni.jee.ggsencheres.bo.Utilisateur;

import fr.eni.jee.ggsencheres.dal.UtilisateurDAO;

public class InscriptionManager {
	
	private Utilisateur userEncours;
	
	
	
	public InscriptionManager(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) throws BLLException {
		
	}

	/**
	* méthode qui valide l'inscription en utilisant 1 méthode de validation par champ et 
	* qui renvoie un utilisateur ou qui lève une BLLException qui s'affiche sur l'écran 
	* utilisateur avec l'ensemble des erreurs générées par chaque méthode
	*/
	public Utilisateur validerInscription (String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse) {
		
		
		

		return userEncours;

	
}
}
