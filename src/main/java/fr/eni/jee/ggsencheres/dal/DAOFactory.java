package fr.eni.jee.ggsencheres.dal;

public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	
	
}
