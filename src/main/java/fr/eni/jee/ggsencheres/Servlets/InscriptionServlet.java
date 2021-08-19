package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.ggsencheres.bll.BLLException;
import fr.eni.jee.ggsencheres.bll.ConnectionManager;
import fr.eni.jee.ggsencheres.bll.InscriptionManager;
import fr.eni.jee.ggsencheres.bo.Utilisateur;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/Inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur userAcreer;
       
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
		// Je récupère  les infos saisies dans les champs du formulaire
		try {
		String pseudo = request.getParameter("pseudo");
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("mot-de_passe");
		String confirmation = request.getParameter("confirmation");
		
		
		// j'applique la méthode de validation définie InscriptionManager
		InscriptionManager im = new InscriptionManager(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
	
			 
			 userAcreer = im.validerInfosUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
			 
			request.setAttribute("succes",true);
			request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			 
		}catch(BLLException e) {
			request.setAttribute("erreur",e.getMessages());
			request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		
		}
	}
}
