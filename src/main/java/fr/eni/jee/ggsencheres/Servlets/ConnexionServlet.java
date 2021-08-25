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

	//Cliquer sur "s'incrire - se connecter" envoie vers la page de connexion
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			 identifiant = request.getParameter("identifiant");
			 motDePasse = request.getParameter("mdp");
			 
			 
			 
			 ConnectionManager cm = new ConnectionManager();
			 
			 userConnected = cm.validerConnexion(identifiant, motDePasse);
			
				request.getSession().setAttribute("userConnected", userConnected);//request.setAttribute("userConnected", userConnected);
				request.getSession().setAttribute("succes", true);//request.setAttribute("succes",true);
			 
			 
			 request.getRequestDispatcher("${pageContext.request.contextPath}/Accueil").forward(request, response);
			 
		}catch(BLLException e) {
			request.setAttribute("erreur",e.getMessage());
			request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		
		}
		
	}

}
