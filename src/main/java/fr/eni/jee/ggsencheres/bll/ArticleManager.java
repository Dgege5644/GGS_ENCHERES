package fr.eni.jee.ggsencheres.bll;

import java.time.LocalDateTime;

import fr.eni.jee.ggsencheres.bo.Article;
import fr.eni.jee.ggsencheres.dal.ArticleDAO;
import fr.eni.jee.ggsencheres.dal.DALException;
import fr.eni.jee.ggsencheres.dal.DAOFactory;


public class ArticleManager {
	private ArticleDAO articleDAO;
	
	
	
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}



	public Article validerArticle(int noUtilisateur, String nomArticle, String description, int categorie,
			String fichierPhotoArticle, int prixInitial, LocalDateTime debutEnchere, LocalDateTime finEnchere)throws BLLException {
		
		if(nomArticle == null || nomArticle.equalsIgnoreCase("")) {
			throw new BLLException("Erreur dans le nom de l'article");
		}
		if(description == null || description.equalsIgnoreCase("")) {
			throw new BLLException("Erreur dans la description de l'article");
		}
//		if(articleAVendre.getNoCategorie()==null) {
//			throw new BLLException("Erreur dans le numero de categorie de l'article");
//		}
		if(debutEnchere == null) {
			throw new BLLException("Erreur dans le debut de l'enchere de l'article");
		}
		if(finEnchere == null) {
			throw new BLLException("Erreur dans la fin de l'enchere de l'article");
		}
		Article articleAVendre = new Article(noUtilisateur, nomArticle,description,categorie,fichierPhotoArticle,prixInitial,debutEnchere,finEnchere);
		
		try {
			articleDAO.addArticle(articleAVendre);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return articleAVendre;
	}
	
}
