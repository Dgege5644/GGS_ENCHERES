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

	/**
	 * Méthode doGet qui permet à l'utilisateur qui clique sur un article sur la page d'accueil
	 * d'arriver sur la jsp enchérir et d'avoir l'ensemble des infos concernant l'enchère en cours sur l'article 
	 * sélectionné
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// on crée une variable locale de type Enchere
		Enchere enchereEC=null;
		// on récupère le noArticle grâce à l'input de type hidden
		int noArticle=Integer.parseInt(request.getParameter("noArticle"));
		// on crée une variable de type ArticleManager pour pouvoir appeler les méthodes
		//de la BLL
		ArticleManager am = new ArticleManager();
		try {
			// on applique à notre variable de type Enchere, la méthode selsectArticleById avec le 
			// noArticle en paramètre
			enchereEC = am.selectArticleById(noArticle);
			// on attribue les données récupérées dans enchereEC à "enchereEC" utilisée dans notre jsp
			request.setAttribute("enchereEC", enchereEC);
			
			
		} catch (BLLException e) {
			// on définit un message d'erreur à afficher dans la jsp si la reconstitution d'un article
			// à partir du noArticle n'est pas possible
			request.setAttribute("erreurEnchere", "enchere impossible");
			e.printStackTrace();
		}
		// on redirige vers encherir.jsp
		request.getRequestDispatcher("/WEB-INF/encherir.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// je récupère la session de l'utilisateur en cours
		Utilisateur userEncherisseur = (Utilisateur)request.getSession().getAttribute("userConnected");
		// je récupère les infos récupérées dans le formulaire : 
		// le noArticle de l'input de type hidden nommé "noArticle"
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		// le montant de l'enchère de l'input nommée "proposition"
		int montantEnchere= Integer.parseInt(request.getParameter("proposition"));
		// je crée les variables locales dont je vais avoir besoin pour récupérer
		// des infos de la DAL que je devrai afficher dans la jsp
		Enchere enchereEC = null;
		String pseudoEncherisseur= userEncherisseur.getPseudo();
		int creditEncherisseur = userEncherisseur.getCredit();
		
		// je crée une varaiable de type ArticleManager pour appeler des méthodes 
		// de la BLL et transmettre les infos en paramètres
		ArticleManager am = new ArticleManager();
		try {
			
			
			// j'appelle la méthode validerEnchere
			am.validerEnchere(montantEnchere,noArticle,pseudoEncherisseur,creditEncherisseur);
			
			// j'appelle la méthode selectArtilceById
			enchereEC = am.selectArticleById(noArticle);
						
			// j'affecte la valeur d'enchereEC à l'attribut "enchereEC"
			request.setAttribute("enchereEC", enchereEC);
			request.setAttribute("succesNouvelleEnchere", "Votre enchère a été prise en compte!");
			
			
		} catch (BLLException e) {
			// en cas d'erreur, j'affecte le message "enchere impossible" à l'attribut
			//"erreurEnchere" afin de pouvoir l'afficher sur encherir.jsp
			request.setAttribute("erreurEnchere", "enchere impossible");
			e.printStackTrace();
		}
		//am.enregistrerEnchere(pseudoEncherisseur,montantEnchere,creditEncherisseur);
		
		// j'affecte la valeur true à l'attribut "succesEnchere", qui permettre d'afficher
		// un message de succès sur encherir.jsp
		request.setAttribute("succesEnchere", true);
		// Je redirige vers encherir.jsp
		request.getRequestDispatcher("/WEB-INF/encherir.jsp").forward(request, response);
	}

}
