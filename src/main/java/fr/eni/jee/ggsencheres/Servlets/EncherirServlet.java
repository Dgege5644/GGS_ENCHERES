package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.ggsencheres.bll.ArticleManager;
import fr.eni.jee.ggsencheres.bo.Article;
import fr.eni.jee.ggsencheres.bo.Enchere;
import fr.eni.jee.ggsencheres.bo.Utilisateur;

/**
 * Servlet implementation class EncherirServlet
 */
@WebServlet("/Encherir")
public class EncherirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EncherirServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO reccuperer num article en cliquand sur le lien nom de l'article sur la page accueil
		//int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		
		request.getRequestDispatcher("/WEB-INF/encherir.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Article articleAuxEncheres=null;
//		// je récupère la session de l'utilisateur en cours
//		Utilisateur userEncherisseur = (Utilisateur)request.getSession().getAttribute("userConnected");
//		// je récupère le no_utilisateur affecté à ce currentUser et laffecte à ma variable
//		String pseudoEncherisseur= userEncherisseur.getPseudo();
//		int creditEncherisseur = userEncherisseur.getCredit();
//	
//		Enchere enchereArticle;
//		int montantEnchere= Integer.parseInt(request.getParameter("proposition"));
//		
//		ArticleManager am = new ArticleManager();
//		am.enregistrerEnchere(pseudoEncherisseur,montantEnchere,creditEncherisseur);
//		request.setAttribute("succesEnchere", true);
//		request.getRequestDispatcher("/WEB-INF/encherir.jsp").forward(request, response);
	}

}
