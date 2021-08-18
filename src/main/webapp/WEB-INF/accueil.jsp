<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil GGS Ench�res</title>
</head>
<body>
	<!-- Adresse = servlet de connexion -->
	<a href="#">S'inscrire - Se connecter</a> 
	<h1>Liste des ench�res</h1>
<form action="/Accueil" method="POST">
	<label for="filtre">Filtres : </label><br />
	<input type="search" placeholder="Le nom de l'article contient" /><br />
	<label for="categorie">Cat�gorie : </label><br />
	<select name="categorie" id="categorie">
		<optgroup label ="cat�gories">
			<option value ="Toutes">Toutes</option>
			<option value ="Informatique">Informatique</option>
			<option value ="Ameublement">Ameublement</option>
			<option value ="Vetement">V�tement</option>
			<option value ="Sport&Loisirs">Sport et Loisirs</option>
		</optgroup>
	</select>
	<input type="submit" value="Rechercher"/>

</form>	
</body>
</html>