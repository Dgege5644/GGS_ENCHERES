package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.ggsencheres.bll.ArticleManager;
import fr.eni.jee.ggsencheres.bll.BLLException;
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
		Utilisateur currentUser = (Utilisateur)request.getSession().getAttribute("userConnected");
		int noUtilisateur= currentUser.getNo_utilisateur();
	try {
		
		String nomArticle = request.getParameter("article");
		String description = request.getParameter("description");
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		String fichierPhotoArticle = request.getParameter("fichierPhotoArticle");
		int prixInitial = Integer.parseInt(request.getParameter("miseAPrix"));
		LocalDateTime debutEnchere =LocalDateTime.parse(request.getParameter("dateDebut"));
		LocalDateTime finEnchere =LocalDateTime.parse(request.getParameter("dateFin"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("cp");
		String ville = request.getParameter("ville");
		
		System.out.println(ville);
		ArticleManager am = new ArticleManager();
	
		articleAVendre = am.creerAticleAVendre(noUtilisateur,nomArticle, description, categorie, fichierPhotoArticle, prixInitial, debutEnchere, finEnchere,rue,codePostal,ville);
		request.setAttribute("succesVendreUnArticle", true);
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}catch(BLLException e) {
			request.setAttribute("erreurs",e.getMessages());
			request.getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
	
		}
		
	}

}
