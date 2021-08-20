<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
</head>
<body>

<h1>Mon profil</h1>

<form action="./Inscription" method="post">
	<label for="pseudo">Pseudo: </label>
	<input type="text" name="pseudo" id="pseudo"/>
	
	<label for="nom">Nom: </label>
	<input type="text" name="nom" id="nom"/><br />
	
	<label for="prenom">Prénom: </label>
	<input type="text" name="prenom" id="prenom"/>
	
	<label for="email">Email: </label>
	<input type="email" name="email" id="email"/><br />
	
	<label for="telephone">Téléphone: </label>
	<input type="text" name="telephone" id="telephone"/>
	
	<label for="rue">Rue: </label>
	<input type="text" name="rue" id="rue"/><br />
	
	<label for="cp">Code postal: </label>
	<input type="text" name="cp" id="cp"/>
	
	<label for="ville">Ville: </label>
	<input type="text" name="ville" id="ville"/><br />
	
	<label for="mdpasse">Mot de passe: </label>
	<input type="password" name="mdpasse" id="mdpasse"/>
	
	<label for="repeat">Confirmation: </label>
	<input type="password" name="repeat" id="repeat"/><br />
	
	<input type="submit" value="Créer"/>
	<a href="${pageContext.request.contextPath}/Accueil">Annuler</a>
	
</form>
</body>
</html>