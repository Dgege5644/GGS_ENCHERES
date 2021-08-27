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
<header>
	<%@include file="entete.html" %>
	<c:if test="${!empty erreur}">
	<p>${erreur} </p>
	</c:if>
	<c:if test="${!empty succes}">
	<p>Votre profil a bien été créé. Bienvenue ! Vous pouvez vous connecter</p>
	</c:if>
</header>
<main>
	<form action="./Connexion" method="post">
	<label for="identifiant">Identifiant: </label>
	<input type="text" name="identifiant" id="identifiant" placeholder="Pseudo ou email" autofocus="autofocus"/><br />
	
	<label for="mdp">Mot de passe: </label>
	<input type="password" name="mdp" id="mdp" placeholder="Mot de passe"/><br />
	
	<input type="submit" value="Connexion" id="boutonConnexion" class="lienbouton"/>
	
	<div id="options">
		
		
		<input type="checkbox" name="ssdm" id="ssdm"/>
		<label for="ssdm">Se souvenir de moi</label><br />
		
		<a href="#">Mot de passe oublié</a>
	</div>
	
	<a href="${pageContext.request.contextPath}/Inscription" id="boutonCompte" class="lienbouton">Creer un compte</a>
</form>
	
</main>
<footer>

</footer>	

</body>
</html>