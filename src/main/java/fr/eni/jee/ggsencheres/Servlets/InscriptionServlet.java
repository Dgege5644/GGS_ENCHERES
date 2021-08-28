package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.ggsencheres.bll.BLLException;
import fr.eni.jee.ggsencheres.bll.ConnectionManager;
import fr.eni.jee.ggsencheres.bll.UtilisateurManager;
import fr.eni.jee.ggsencheres.bo.Utilisateur;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/Inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * 
     */
    public InscriptionServlet() {
        super();
        
    }

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur userAcreer;	
		try {
			// Je récupère  les infos saisies dans les champs du formulaire
			String pseudo = request.getParameter("pseudo");
			String prenom = request.getParameter("prenom");
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("cp");
			String ville = request.getParameter("ville");
			String motDePasse = request.getParameter("mdpasse");
			String confirmation = request.getParameter("repeat");
	
			UtilisateurManager im = new UtilisateurManager();
			
			// j'applique la méthode de création d'un utilisateur définie dans InscriptionManager
			userAcreer = im.creerNouvelUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, confirmation );
			
			request.setAttribute("succes",true);
			request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
			
			 
		}catch(BLLException e) {
			request.setAttribute("erreurs",e.getMessages());
			request.getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
		
		}
	}
}
