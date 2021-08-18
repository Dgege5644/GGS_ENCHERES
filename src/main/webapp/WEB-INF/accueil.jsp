<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil GGS Enchères</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
</head>
<body>
	<!-- Adresse = servlet de connexion -->
		<c:if test="${empty succes}">
	<a href="${pageContext.request.contextPath}/Connexion">S'inscrire - Se connecter</a>
	</c:if>
	<c:if test="${!empty erreur}">
	<p>${erreur}</p>
	</c:if>
	<c:if test="${!empty succes}">
	<a href="${pageContext.request.contextPath}/Deconnexion">Se Déconnecter</a>
	<p>${userConnected.prenom} ${userConnected.nom} est connecté(e)</p>
	</c:if>  
	<h1>Liste des enchères</h1>

	
	<form action="/Accueil" method="POST">

	

	<label for="filtre">Filtres : </label><br />
	<input type="search" placeholder="Le nom de l'article contient" /><br />
	<label for="categorie">Catégorie : </label><br />
	<select name="categorie" id="categorie">
		<optgroup label ="catégories">
			<option value ="Toutes">Toutes</option>
			<option value ="Informatique">Informatique</option>
			<option value ="Ameublement">Ameublement</option>
			<option value ="Vetement">Vêtement</option>
			<option value ="Sport&Loisirs">Sport et Loisirs</option>
		</optgroup>
	</select>
	<input type="submit" value="Rechercher"/>

</form>



</body>
</html>