package fr.eni.jee.ggsencheres.dal;

import java.sql.SQLException;

import fr.eni.jee.ggsencheres.bo.Utilisateur;



public interface UtilisateurDAO {


	Utilisateur getInfosUtilsateur(String identifiant) throws DALException;
	

}
