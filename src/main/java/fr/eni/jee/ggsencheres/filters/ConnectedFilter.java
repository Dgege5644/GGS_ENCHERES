package fr.eni.jee.ggsencheres.filters;
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
 * Servlet Filter implementation class ConnectedFilter
 */
@WebFilter("/*")
public class ConnectedFilter implements Filter {
    /**
     * Default constructor. 
     */
    public ConnectedFilter() {
        // TODO Auto-generated constructor stub
    }
    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest=(HttpServletRequest)request;
        HttpServletResponse httpResponse=(HttpServletResponse)response;
        if(httpRequest.getServletPath().contains("Accueil")|| httpRequest.getServletPath().contains("Connexion")|| httpRequest.getServletPath().contains("Inscription")){
            chain.doFilter(httpRequest, httpResponse);
        }
        if (httpRequest.getSession().getAttribute("userConnected") != null) {
            //On laisse passer si l'utilisateur est présent en session
            chain.doFilter(request, response); 
            return; // permet de ne pas executer le reste de la fonction
        }
        
        httpResponse.sendRedirect("${pageContext.request.contextPath}/accueil.jsp");    
    }
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
}