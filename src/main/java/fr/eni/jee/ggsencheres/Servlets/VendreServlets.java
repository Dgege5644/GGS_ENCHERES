package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.ggsencheres.bll.ArticleManager;
import fr.eni.jee.ggsencheres.bo.Article;
import fr.eni.jee.ggsencheres.bo.Utilisateur;

/**
 * Servlet implementation class VendreServlets
 */
@WebServlet("/Vendre")
public class VendreServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private String nomArticle;
       private String description;
       private String categorie;
       private String fichierPhotoArticle;
       private int prixInitial;
       private LocalDateTime debutEnchere;
       private LocalDateTime finEnchere;
       private String rue;
       private String codePostal;
       private String ville;
       
       private Article articleAVendre;
       private Utilisateur userConnected;
       private int noUtilisateur = userConnected.getNo_utilisateur();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendreServlets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		nomArticle = request.getParameter("article");
		description = request.getParameter("description");
		categorie = request.getParameter("categorie");
		fichierPhotoArticle = request.getParameter("fichierPhotoArticle");
		prixInitial = Integer.parseInt(request.getParameter("miseAPrix"));
		debutEnchere =LocalDateTime.parse(request.getParameter("dateDebut"));
		finEnchere =LocalDateTime.parse(request.getParameter("dateFin"));
		rue = request.getParameter("rue");
		codePostal = request.getParameter("cp");
		ville = request.getParameter("ville");
		
		ArticleManager am = new ArticleManager();
		
		articleAVendre = am.validerArticle(noUtilisateur,nomArticle, description, categorie, fichierPhotoArticle, prixInitial, debutEnchere, finEnchere);
		
		
		
	}

}
