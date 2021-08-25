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
	<h2>${enchereEC.articleEC.nomArticle}</h2>
	<label for="description">Description : </label>
	<textarea name="description" id="description" cols="30" rows="10">"${enchereEC.articleEC.description}"</textarea>
	
	<label for="categorie">Catégorie :</label>
	
	
	<label for="enchereActuelle">Meilleure offre : </label>
	<p>${enchereEC.montantEnchere} pts par ${enchereEC.userEncherisseur.pseudo}</p>
	
	<label for="prixInitial">Mise à prix :</label>
	<p>${enchereEC.articleEC.prixInitial}</p>
	<label for="finEnchere">Fin de l'enchère : </label>
	<p>${enchereEC.articleEC.finEnchere}</p>
	
	<label for="retrait">Retrait : </label>
	 <p>${enchereEC.articleEC.rue}</p>
	 <p>${enchereEC.articleEC.codePostal} ${enchereEC.articleEC.ville}</p>
	 
	 <label for="vendeur">Vendeur : </label>
	 <p>${enchereEC.userEncherisseur.pseudo}</p>
	 
	 
	
	
	<form action="${pageContext.request.contextPath}/Encherir">
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
		
		
		<input type="submit" name="encherir" value="Enchérir"/>
		<input type="hidden" value="${enchereEC.articleEC.noArticle}"name="noArticle"/> 
	</form>
	<c:if test="${!empty succesEnchere}">
		Votre enchère a bien été prise en compte
	</c:if>
</body>
</html>