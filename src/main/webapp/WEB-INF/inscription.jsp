<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>

<h1>Mon profil</h1>

<form action="./Inscription" method="post">
	<label for="pseudo">Pseudo: </label>
	<input type="text" name="pseudo" id="pseudo" required="required"/>
	
	<label for="nom">Nom: </label>
	<input type="text" name="nom" id="nom" required="required"/><br/>
	
	<label for="prenom">Prénom: </label>
	<input type="text" name="prenom" id="prenom" required="required"/>
	
	<label for="email">Email: </label>
	<input type="email" name="email" id="email" required="required"/><br />
	
	<label for="telephone">Téléphone: </label>
	<input type="text" name="telephone" id="telephone" />
	
	<label for="rue">Rue: </label>
	<input type="text" name="rue" id="rue" required="required"/><br />
	
	<label for="cp">Code postal: </label>
	<input type="text" name="cp" id="cp" required="required"/>
	
	<label for="ville">Ville: </label>
	<input type="text" name="ville" id="ville" required="required"/><br />
	
	<label for="mdpasse">Mot de passe: </label>
	<input type="password" name="mdpasse" id="mdpasse" required="required"/>
	
	<label for="repeat">Confirmation: </label>
	<input type="password" name="repeat" id="repeat" required="required"/><br />
	
	<!-- S'il y a des erreurs à afficher, on parcours les messages -->
	<c:if test="${!empty erreurs}">
		<ul style="color: red;">
			<c:forEach var="erreur" items="${erreurs}">
				<li>${erreur }</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<input type="submit" value="Créer"/>
	<a href="${pageContext.request.contextPath}/Accueil">Annuler</a>
	
</form>
</body>
</html>