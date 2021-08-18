package fr.eni.jee.ggsencheres.Servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltreVerifUserConnecte
 */
@WebFilter("/connecte/*")
public class FiltreVerifUserConnecte implements Filter {

    /**
     * Default constructor. 
     */
    public FiltreVerifUserConnecte() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * On passe préalablement dans cette méthode avant chaque appel à une url qui contient /connecte/*
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// On va devoir convertir les objets de type ServletRequest/ServletResponse en HttpServletRequest/HttpServletResponse pour pouvoir avoir accès à toutes les méthodes
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				HttpServletResponse httpResponse = (HttpServletResponse) response;

		// 1 - Si jamais l'utilisateur est connecté, on laisse passer 
		if (httpRequest.getSession().getAttribute("user") != null) {
			//On laisse passer si l'utilisateur est présent en session
			chain.doFilter(request, response); 
			return; // permet de ne pas executer le reste de la fonction	
		}
		

	// 2 - Si jamais on essaye de rediriger sur la page d'erreur, onlaisse passer
	// IMPORTANT : Ne pas utiliser le filtre sur la page sur laquelle le filtre redirige : sinon BOUCLE INFINIE
			if (httpRequest.getServletPath().contains("erreurUtilisateurConnecte")) {
				chain.doFilter(request, response);
				return; // permet de ne pas executer le reste de la fonction
			}
			//3 - Sinon (utilisateur non connecté et on n'est pas sur la redirection), on redirige vers la page d'information
			httpResponse.sendRedirect("./erreurUtilisateurConnecte.jsp");
}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
