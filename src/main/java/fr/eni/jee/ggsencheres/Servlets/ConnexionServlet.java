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
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/Connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String identifiant;
	private String motDePasse;
	private Utilisateur userConnected;
    public ConnexionServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			 identifiant = request.getParameter("identifiant");
			 motDePasse = request.getParameter("mdp");
			 
			 System.out.println(identifiant + ": " + motDePasse); //Vérification récupération de l'identifiant et du mdp
			 
			 ConnectionManager cm = new ConnectionManager();
			 
			 userConnected = cm.validerConnexion(identifiant, motDePasse);
			 //TODO placer le user dans le contexte de session
			// 2 - On va ajouter un utilisateur à la session à partir du prenom/nom
			
				request.getSession().setAttribute("userConnected", userConnected);//request.setAttribute("userConnected", userConnected);
				request.getSession().setAttribute("succes", true);//request.setAttribute("succes",true);
			 
			 
			 request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			 
		}catch(BLLException e) {
			request.setAttribute("erreur",e.getMessage());
			request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		
		}
		
	}

}
