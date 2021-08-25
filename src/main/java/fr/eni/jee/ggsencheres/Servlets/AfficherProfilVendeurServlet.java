package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AfficherProfilVendeurServlet
 */
@WebServlet("/AfficherProfilVendeur")
public class AfficherProfilVendeurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AfficherProfilVendeurServlet() {
        super();     
    }
    //Utiliser afficherInfosVendeur(); ?
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/afficherProfilVendeur.jsp").forward(request, response);
	}

}
