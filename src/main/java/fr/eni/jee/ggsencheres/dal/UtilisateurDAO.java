package fr.eni.jee.ggsencheres.dal;


import java.util.List;

import fr.eni.jee.ggsencheres.bo.Utilisateur;



public interface UtilisateurDAO {


	Utilisateur getInfosUtilisateur(String identifiant) throws DALException;
	
	
	List<Utilisateur> getListeUtilisateurs () throws DALException;
	
	void updateInfosUtilisateur(int noUtilisateur) throws DALException;
	
	Utilisateur addUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) throws DALException;
	

}
