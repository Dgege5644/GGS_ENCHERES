<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<h1>Détail vente</h1>
	
	<!-- Dans la première partie de la page on affiche les infos que l'on récupère 
	de la DAL, en partant de l'objet EnchereEC -->
	<h2>${enchereEC.articleEC.nomArticle}</h2>
	<label for="description">Description : </label>
	<textarea name="description" id="description" cols="10" rows="3">${enchereEC.articleEC.description}</textarea><br />
	
	<label for="categorie">Catégorie :  </label><br />
	
	
	<label for="enchereActuelle">Meilleure offre : ${enchereEC.montantEnchere} pts par ${enchereEC.userEncherisseur.pseudo}</label><br />
	<!-- TODO Attention, ${enchereEC.userEncherisseur.pseudo} renvoie le 
	nomArticle à l'affichage -->
	
	
	<label for="prixInitial">Mise à prix : ${enchereEC.articleEC.prixInitial}</label><br />
	
	<label for="finEnchere">Fin de l'enchère : ${enchereEC.articleEC.finEnchere}</label><br />
	
	
	<label for="retrait">Retrait : ${enchereEC.articleEC.rue} <br /> ${enchereEC.articleEC.codePostal} ${enchereEC.articleEC.ville}</label><br />
	
	 
	 <label for="vendeur">Vendeur : ${enchereEC.articleEC.noUtilisateur}</label>
	

	 
	 
	<!--  Partie forulaire où on récupère la proposition d'enchère du userEncherisseur -->
	
	<form action="${pageContext.request.contextPath}/Encherir" method="post">
		<label for="proposition">Ma proposition :</label>
		
		<!-- S'il n'y a pas encore d'enchère sur le prix initial, la proposition doit au moins être supérieure au
		prixInitial de 1 pt -->
		<c:if test="${empty enchereEC.montantEnchere}">
		<input type="number" name="proposition" min="${enchereEC.articleEC.prixInitial+1}" value="${enchereEC.articleEC.prixInitial+1}" step="1"/>
		</c:if>
		<!-- S'il y a déjà une enchère sur le prix initial, la proposition doit au moins être supérieure à cette
		enchère de 1 pt -->
		<c:if test="${!empty enchereEC.montantEnchere}">
		<input type="number" name="proposition" min="${enchereEC.montantEnchere+1}" value="${enchereEC.montantEnchere+1}" step="1"/>
		</c:if>
		<!--  On met un input de type hidden pour récupérer le noArticle de l'enchereEC
		dont on aura besoin dans le Servelt pour accéder à la DAL et récupérer les infos
		sur l'articleEC et l'userEncherisseur -->
		<input type="hidden" value="${enchereEC.articleEC.noArticle}"name="noArticle"/>
		<input type="submit" name="encherir" value="Enchérir"/>
			 
	</form>
	<c:if test="${!empty succesEnchere}">
		<p>Votre enchère a bien été prise en compte.</p>
		<p>L'article sera à vous si personne ne surenchérit sur votre proposition avant la fin de l'enchère.</p>
	</c:if>
	
	<c:if test ="${!empty erreurEnchere}">
		${erreurEnchere}
	</c:if>
</body>
</html>