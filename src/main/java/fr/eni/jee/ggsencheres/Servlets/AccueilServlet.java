package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.ggsencheres.bll.ArticleManager;
import fr.eni.jee.ggsencheres.bo.Enchere;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/Accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccueilServlet() {
        super(); 
    }

	/**
	 * La Servlet envoie l'utilisateur vers la page d'accueil
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleManager am = new ArticleManager();
		List<Enchere> listeEnchere = null;
	
		
		// TODO Ecrire
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}
	

}
