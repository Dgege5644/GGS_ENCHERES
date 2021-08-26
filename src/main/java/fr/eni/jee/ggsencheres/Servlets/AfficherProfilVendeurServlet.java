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
 * Servlet implementation class AfficherProfilVendeurServlet
 */
@WebServlet("/AfficherProfilVendeur")
public class AfficherProfilVendeurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AfficherProfilVendeurServlet() {
        super();     
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				Utilisateur userVendeur = null;
				
				int no_utilisateur = Integer.parseInt(request.getParameter("no_utilisateur"));
				
				UtilisateurManager um = new UtilisateurManager();
				
				try {
					
					userVendeur = um.afficherInfosVendeur(no_utilisateur);
					
					request.setAttribute("userVendeur", userVendeur);
					
					
				} catch (BLLException e) {
					
					request.setAttribute("erreurUserVendeur", "userVendeur introuvable");
					e.printStackTrace();
				}
				
				request.getRequestDispatcher("/WEB-INF/afficherProfilVendeur.jsp").forward(request, response);
			}

	

}
