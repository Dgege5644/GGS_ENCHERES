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

				
		
		chain.doFilter(request, response);
		return; // pour ne pas exécuter le reste de la fonction
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
