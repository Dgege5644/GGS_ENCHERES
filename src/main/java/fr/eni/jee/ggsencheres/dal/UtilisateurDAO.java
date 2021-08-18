package fr.eni.jee.ggsencheres.dal;

import java.sql.SQLException;

public interface UtilisateurDAO {

	String getMotDePasse(String identifiant) throws DALException;
}
