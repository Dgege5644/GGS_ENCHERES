package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.ggsencheres.bll.BLLException;
import fr.eni.jee.ggsencheres.bll.ConnectionManager;
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
		
		// 3 - envoie le no_utilisateur à la BLL pour poursuite de la méthode de suppression
		ConnectionManager cm = new ConnectionManager();
		no_utilisateur = userConnected.getNo_utilisateur();
		System.out.println("no_utilisateur:" + no_utilisateur);
		cm.deleteUtilisateur(no_utilisateur);
		
		// 1 - met fin à la session en cours
		request.getSession().invalidate();
				
		// 2 - redirige vers l'accueil 
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		
	
		//TODO	Entourer d'un try/catch en cas d'erreur dans la méthode de suppression 
		
	}

}
