<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>vendre un artcile</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<%@include file="header.html" %>
<!-- 
<a href="${pageContext.request.contextPath}/Accueil">Retour à l'accueil</a><br />
 -->
<h1>Nouvelle vente</h1>
<form action="${pageContext.request.contextPath}/Vendre" method="post">

	<label for="article">Article: </label>
	<input type="text" name="article" placeholder="nom de l'article" required="required"/><br />
	
	<label for="description">Description: </label>
	<textarea name="description" id="descriptionArticle" cols="30" rows="5"  placeholder="description de l'article" required="required"></textarea><br />
	
	<label for="categorie">Catégorie: </label>
	<select name="categorie" id="categorie" required="required">
		<optgroup label ="catégories">
			<option name ="categorie" value="0">Toutes</option>
			<option name="categorie" value ="1">Informatique</option>
			<option name="categorie" value ="2">Ameublement</option>
			<option name="categorie" value ="3">Vêtement</option>
			<option name="categorie" value ="4">Sport et Loisirs</option>
		</optgroup>
	</select><br />
	
	<label for="fichierPhotoArticle">Photo de l'article: </label>
	<input type="file" id="fichierPhotoArticle" name="fichierPhotoArticle" accept="image/png, image/jpeg"/> <br />
	
	<label for="miseAPrix">Mise à prix: </label>
	<input type="number" name="miseAPrix" id="miseAPrix" min="0" step="1" required="required"/><br />
	
	<label for="dateDebut">Début de l'enchère: </label>
	<input type="datetime-local" name="dateDebut" id="dateDebut" required="required"/><br />
	
	<label for="dateFin">Fin de l'enchère: </label>
	<input type="datetime-local" name="dateFin" id="dateFin" required="required"/>
	
	<fieldset>
		<legend>Retrait</legend>
		
		<label for="rue">Rue: </label>
		<input type="text" name="rue" id="rue" value="${userConnected.rue}" required="required"/><br />
		
		<label for="cp">Code postal: </label>
		<input type="text" name="cp" id="cp" value="${userConnected.codePostal}" required="required"/><br />
		
		<label for="ville">Ville: </label>
		<input type="text" name="ville" id="ville" value="${userConnected.ville}" required="required"/>
		
	</fieldset><br />
	<!-- S'il y a des erreurs à afficher, on parcours les messages -->
	<c:if test="${!empty erreurs}">
		<ul style="color: red;">
			<c:forEach var="erreur" items="${erreurs}">
				<li>${erreur }</li>
			</c:forEach>
		</ul>
	</c:if>
	<input type="submit" id="enregistrerArticle" value="Enregistrer"/>
	<input type="reset" value="Annuler">
	



</form>
</body>
</html>