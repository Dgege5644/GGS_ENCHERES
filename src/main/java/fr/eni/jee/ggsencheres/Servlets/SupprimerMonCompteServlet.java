package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.ggsencheres.bll.BLLException;
import fr.eni.jee.ggsencheres.bll.ConnectionManager;
import fr.eni.jee.ggsencheres.bll.UtilisateurManager;
import fr.eni.jee.ggsencheres.bo.Utilisateur;

/**
 * Servlet implementation class SupprimerMonCompteServlet
 */
@WebServlet("/SupprimerMonCompte")
public class SupprimerMonCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur userConnected;
	private int no_utilisateur;
       
	
    public SupprimerMonCompteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getRequestDispatcher("/WEB-INF/supprimerMonCompte.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1 - envoie le no_utilisateur à la BLL pour poursuite de la méthode de suppression

		int no_utilisateur = Integer.parseInt(request.getParameter("no_utilisateur"));
		Utilisateur userASupprimer = null;
		UtilisateurManager um = new UtilisateurManager();
		
		try {
			
			um.supprimerUtilisateur(no_utilisateur);
			
			// 2 - met fin à la session en cours et envoie le message de confirmation de suppression du compte utilisateur.
			
			request.getSession().invalidate();
			request.setAttribute("suppressionCompte", true);
			
		}catch(BLLException e) {
			request.setAttribute("erreur",e.getMessage());
		}
		
		// 3 - redirige vers l'accueil 
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		
	
		
		
	}

}
