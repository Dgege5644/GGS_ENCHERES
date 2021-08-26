<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>

<!-- S'il y a des erreurs à afficher, on parcours les messages -->
	<c:if test="${!empty erreurs}">
		<ul style="color: red;">
			<c:forEach var="erreur" items="${erreurs}">
				<li>${erreur }</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<a href="${pageContext.request.contextPath}/Accueil">Retour à l'accueil</a>
	
	<form action="${pageContext.request.contextPath}/ModifierMonProfil" method="post">
	
		<label for="pseudo">Pseudo: </label>
		<input type="text" name="pseudo" id="pseudo" value="${userConnected.pseudo}"/>
		<input type="hidden" name ="pseudoActuel" value ="${userConnected.pseudo}">
		<label for="nom">Nom: </label>
		<input type="text" name="nom" id="nom" value="${userConnected.nom}"/><br />
		
		<label for="prenom">Prénom: </label>
		<input type="text" name="prenom" id="prenom" value="${userConnected.prenom}"/>
		
		<label for="email">Email: </label>
		<input type="email" name="email" id="email" value="${userConnected.email}"/><br />
		<input type="hidden" name ="emailActuel" value ="${userConnected.email}">
		<label for="telephone">Téléphone: </label>
		<input type="text" name="telephone" id="telephone" value="${userConnected.telephone}"/>
		
		<label for="rue">Rue: </label>
		<input type="text" name="rue" id="rue" value="${userConnected.rue}"/><br />
		
		<label for="cp">Code postal: </label>
		<input type="text" name="cp" id="cp" value="${userConnected.codePostal}"/>
		
		<label for="ville">Ville: </label>
		<input type="text" name="ville" id="ville" value="${userConnected.ville}"/><br />
		
		<label for="mdpasse">Mot de passe actuel: </label>
		<input type="password" name="mdpasse" id="mdpasse" value="${userConnected.motDePasse}"/><br />
		
		<label for="newmdpasse">Nouveau mot de passe: </label>
		<input type="password" name="newmdpasse" id="newmdpasse" value="${userConnected.motDePasse}"/>
		
		<label for="repeat">Confirmation: </label>
		<input type="password" name="confirmationnewmdp" id="confirmationnewmdp" value="${userConnected.motDePasse}"/><br />
		
		<label for="credit">Crédit: </label>
		<input type="number" name="credit" id="credit" readonly="readonly" value="${userConnected.credit}"/><br />
			

		<input type="reset" value="Annuler les modifications"/>

		<input type="submit" value="Enregistrer"/>
		<button><a href="${pageContext.request.contextPath}/SupprimerMonCompte">Supprimer Mon compte</a></button>
		
	</form>
	
</body>
</html>

