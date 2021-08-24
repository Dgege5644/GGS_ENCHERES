package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.ggsencheres.bll.BLLException;
import fr.eni.jee.ggsencheres.bll.UtilisateurManager;
import fr.eni.jee.ggsencheres.bo.Utilisateur;

/**
 * Servlet implementation class ModifierMonProfilServlet
 */
@WebServlet("/ModifierMonProfil")
public class ModifierMonProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierMonProfilServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/modifierMonProfil.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// je récupère la session de l'utilisateur en cours
		Utilisateur currentUser = (Utilisateur)request.getSession().getAttribute("userConnected");
		// je récupère le no_utilisateur affecté à ce currentUser et laffecte à ma variable
		int noUtilisateur= currentUser.getNo_utilisateur();
		// je crée une variable userAmodifier pour récupérer les infos renseignées par l'utilisateur
		Utilisateur userAmodifier;
		try {
			String pseudo = request.getParameter("pseudo");
			String prenom = request.getParameter("prenom");
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("cp");
			String ville = request.getParameter("ville");
			String motDePasseActuel = request.getParameter("mdpasse");
			String newMotDePasse = request.getParameter("newmdpasse");
			String confirmation = request.getParameter("confirmationnewmdp");
			
			//Création d'une variable de type Utilisateur manager pour pouvoir appeler les méthodes et 
			// transmètre les infos récupérées de l'IHM en paramètres
			UtilisateurManager um = new UtilisateurManager();
			
			// j'j'applique la méthode modifierUtilisateur du manager
			um.modifierUtilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasseActuel, newMotDePasse,confirmation);
		
			request.setAttribute("succesModifProfil", true);
			request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			
			
		}catch(BLLException e) {
			request.setAttribute("erreurs",e.getMessages());
			request.getRequestDispatcher("/WEB-INF/modifierMonProfil.jsp").forward(request, response);
	
		}
	}
}
