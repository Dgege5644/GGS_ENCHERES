package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.ggsencheres.bll.ArticleManager;
import fr.eni.jee.ggsencheres.bll.BLLException;
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
		Enchere enchereEC=null;
		int noArticle=Integer.parseInt(request.getParameter("noArticle"));
		ArticleManager am = new ArticleManager();
		try {
			enchereEC=am.selectArticleById(noArticle);
			request.setAttribute("enchereEC", enchereEC);
		} catch (BLLException e) {
			request.setAttribute("erreurEnchere", "enchere impossible");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/encherir.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// je récupère la session de l'utilisateur en cours
		Utilisateur userEncherisseur = (Utilisateur)request.getSession().getAttribute("userConnected");
		// je récupère le no_utilisateur affecté à ce currentUser et laffecte à ma variable
		Enchere enchereEC=null;
		String pseudoEncherisseur= userEncherisseur.getPseudo();
		int creditEncherisseur = userEncherisseur.getCredit();
		int montantEnchere= Integer.parseInt(request.getParameter("proposition"));
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		ArticleManager am = new ArticleManager();
		try {
			enchereEC=am.selectArticleById(noArticle);
			
			enchereEC = am.validerEnchere(montantEnchere,noArticle,pseudoEncherisseur,creditEncherisseur);
			request.setAttribute("enchereEC", enchereEC);
			
		} catch (BLLException e) {
			request.setAttribute("erreurEnchere", "enchere impossible");
			e.printStackTrace();
		}
		
		
		//am.enregistrerEnchere(pseudoEncherisseur,montantEnchere,creditEncherisseur);
		request.setAttribute("succesEnchere", true);
		request.getRequestDispatcher("/WEB-INF/encherir.jsp").forward(request, response);
	}

}
