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
		Article articleEC=null;
		
		// on récupère le noArticle grâce à l'input de type hidden
		int noArticle=Integer.parseInt(request.getParameter("noArticle"));
		// on crée une variable de type ArticleManager pour pouvoir appeler les méthodes
		//de la BLL
		ArticleManager am = new ArticleManager();
		try {
			// on applique à notre variable de type Enchere, la méthode selsectArticleById avec le 
			// noArticle en paramètre
			articleEC = am.selectArticleById(noArticle);
			// on attribue les données récupérées dans enchereEC à "enchereEC" utilisée dans notre jsp
			request.setAttribute("articleEC", articleEC);
			
			
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
		try {
		// je récupère la session de l'utilisateur en cours
		Utilisateur userEncherisseur = (Utilisateur)request.getSession().getAttribute("userConnected");
		// je récupère les infos récupérées dans le formulaire : 
		// le noArticle de l'input de type hidden nommé "noArticle"
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		// le montant de l'enchère de l'input nommée "proposition"
		System.out.println(noArticle);
		int montantEnchere= Integer.parseInt(request.getParameter("proposition"));
		System.out.println(montantEnchere);
		int noUtilisateur=userEncherisseur.getNo_utilisateur();
		// je crée les variables locales dont je vais avoir besoin pour récupérer
		// des infos de la DAL que je devrai afficher dans la jsp
		/*
		Enchere enchereEC = null;
		String pseudoEncherisseur= userEncherisseur.getPseudo();
		int noEncherisseur=userEncherisseur.getNo_utilisateur();
		int creditEncherisseur = userEncherisseur.getCredit();
		int prixInitial =0;
		LocalDateTime dateEnchere=null;
		*/
		
		// je crée une varaiable de type ArticleManager pour appeler des méthodes 
		// de la BLL et transmettre les infos en paramètres
		ArticleManager am = new ArticleManager();
		
	
			/*
			enchereEC = am.selectArticleById(noArticle,userEncherisseur);
			prixInitial=enchereEC.getArticleEC().getPrixInitial();
			dateEnchere=enchereEC.getDateEnchere();
			*/
			// j'appelle la méthode validerEnchere (etape zappée pour la présentation du projet
			//am.validerEnchere(montantEnchere,noArticle,noEncherisseur,creditEncherisseur,prixInitial,dateEnchere);
			
			// j'appelle la méthode udpdate
			//enchereEC = am.selectArticleById(noArticle,userEncherisseur);
					
			am.update(noArticle, noUtilisateur, montantEnchere);
			// j'affecte la valeur d'enchereEC à l'attribut "enchereEC"
			//request.setAttribute("enchereEC", articleEC);
			request.setAttribute("succesNouvelleEnchere", "Votre enchère a été prise en compte!");
			
			
		} catch (BLLException e) {
			// en cas d'erreur, j'affecte le message "enchere impossible" à l'attribut
			//"erreurEnchere" afin de pouvoir l'afficher sur encherir.jsp
			e.printStackTrace();
			request.setAttribute("erreurEnchere", "enchere impossible");
			
			
		}
		//am.enregistrerEnchere(pseudoEncherisseur,montantEnchere,creditEncherisseur);
		
		// j'affecte la valeur true à l'attribut "succesEnchere", qui permettre d'afficher
		// un message de succès sur encherir.jsp
		request.setAttribute("succesEnchere", true);
		// Je redirige vers encherir.jsp
		request.getRequestDispatcher("/WEB-INF/encherir.jsp").forward(request, response);
	}

}
