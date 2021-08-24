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
	<h2>${article.nomArticle}</h2>
	<label for="description">Description : </label>
	<textarea name="description" id="description" cols="30" rows="10">"${article.description}"</textarea>
	
	<label for="categorie">Catégorie :</label>
	<p>${article.noCategorie.libelle}</p>
	
	<label for="enchereActuelle">Meilleure offre : </label>
	<p>${article.enchere.montantEnchere} pts par ${article.enchere.utilisateur.pseudo}</p>
	
	<label for="prixInitial">Mise à prix :</label>
	<p>${article.prixInitial}</p>
	<label for="finEnchere">Fin de l'enchère : </label>
	<p>${article.finEnchere}</p>
	
	<label for="retrait">Retrait : </label>
	 <p>${article.rue}</p>
	 <p>${article.codePostal} ${article.ville}</p>
	 
	 <label for="vendeur">Vendeur : </label>
	 <p>${article.utilisateur}</p>
	 
	 
	
	
	<form action="${pageContext.request.contextPath}/Encherir">
		<label for="proposition">Ma proposition :</label>
		<!-- S'il n'y a pas encore d'enchère sur le prix initial, la proposition doit au moins être supérieure au
		prixInitial de 1 pt -->
		
		<c:if test="${empty article.enchere.montantEnchere}">
		<input type="number" name="proposition" min="${article.prixInitial+1}" value="${article.prixInitial+1}" step="1"/>
		</c:if>
		<!-- S'il y a déjà une enchère sur le prix initial, la proposition doit au moins être supérieure à cette
		enchère de 1 pt -->
		<c:if test="${!empty article.enchere.montantEnchere}">
		<input type="number" name="proposition" min="${article.enchere.montantEnchere+1}" value="${article.enchere.montantEnchere+1}" step="1"/>
		</c:if>
		
		
		<input type="submit" name="encherir" value="Enchérir"/>
	</form>
	<c:if test="${!empty succesEnchere}">
		Votre enchère a bien été prise en compte
	</c:if>
</body>
</html>