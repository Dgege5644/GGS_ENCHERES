package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.ggsencheres.bll.ArticleManager;
import fr.eni.jee.ggsencheres.bll.BLLException;
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
		
	List<Enchere> listeEncheres=null;
	
	
	try {
		listeEncheres = am.afficherEncheres();
		
		if(listeEncheres==null) {
			request.setAttribute("listeEncheresNulle", "Aucune enchère en cours, Revenez ultérieurement!");	
			
		}else {			
			request.setAttribute("listeEncheres", listeEncheres);	
		}
	}catch(BLLException e) {
		
		e.printStackTrace(); //Indispensable pour obtenir les erreurs au lancement de l'application...
		request.setAttribute("erreurListeEncheres", "Erreur lors de l'affichage de la liste");
	}
			
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}