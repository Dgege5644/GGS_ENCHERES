package fr.eni.jee.ggsencheres.bll;

import java.time.LocalDateTime;

import fr.eni.jee.ggsencheres.bo.Article;
import fr.eni.jee.ggsencheres.dal.ArticleDAO;
import fr.eni.jee.ggsencheres.dal.DALException;
import fr.eni.jee.ggsencheres.dal.DAOFactory;


public class ArticleManager {
	private ArticleDAO articleDAO;
	private BLLException exceptions;
	
	
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}



	public void validerArticle(int noUtilisateur, String nomArticle, String description, int categorie,
			String fichierPhotoArticle, int prixInitial, LocalDateTime debutEnchere, LocalDateTime finEnchere)throws BLLException {
		exceptions= new BLLException();
		if(nomArticle == null || nomArticle.equalsIgnoreCase("")) {
			exceptions.addMessage("Erreur dans le nom de l'article");
		}
		if(description == null || description.equalsIgnoreCase("")) {
			exceptions.addMessage("Erreur dans la description de l'article");
		}
//		if(articleAVendre.getNoCategorie()==null) {
//			throw new BLLException("Erreur dans le numero de categorie de l'article");
//		}
		if(debutEnchere == null && debutEnchere.compareTo(finEnchere)> 0  && debutEnchere.compareTo(LocalDateTime.now())<0) {
			exceptions.addMessage("Erreur dans le debut de l'enchere de l'article");
		}
		if(finEnchere == null) {
			exceptions.addMessage("Erreur dans la fin de l'enchere de l'article");
		}
		
	}
	
	public Article creerAticleAVendre(int noUtilisateur, String nomArticle, String description, int categorie,
			String fichierPhotoArticle, int prixInitial, LocalDateTime debutEnchere, LocalDateTime finEnchere)throws BLLException {
		Article articleAVendre = new Article(noUtilisateur, nomArticle,description,categorie,fichierPhotoArticle,prixInitial,debutEnchere,finEnchere);
		try {
			this.validerArticle(noUtilisateur, nomArticle, description, categorie, fichierPhotoArticle, prixInitial, debutEnchere, finEnchere);
			
			articleDAO.addArticle(articleAVendre);
			
		}catch(DALException e) {
			throw new BLLException(e.getMessage());
		}
		
		return articleAVendre;
	}
	
}
