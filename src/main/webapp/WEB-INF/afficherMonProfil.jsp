<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./AfficherMonProfil" method="get">
	
		<label for="pseudo">Pseudo: </label>
		<input type="text" name="pseudo" id="pseudo" readonly="readonly" value="${userConnected.pseudo}"/><br />
		
		<label for="nom">Nom: </label>
		<input type="text" name="nom" id="nom" readonly="readonly" value="${userConnected.nom}"/><br />
		
		<label for="prenom">Prénom: </label>
		<input type="text" name="prenom" id="prenom" readonly="readonly" value="${userConnected.prenom}"/><br />
		
		<label for="email">Email: </label>
		<input type="email" name="email" id="email" readonly="readonly" value="${userConnected.email}"/><br />
		
		<label for="telephone">Téléphone: </label>
		<input type="text" name="telephone" id="telephone" readonly="readonly" value="${userConnected.telephone}"/><br />
		
		<label for="rue">Rue: </label>
		<input type="text" name="rue" id="rue" readonly="readonly" value="${userConnected.rue}"/><br />
		
		<label for="cp">Code postal: </label>
		<input type="text" name="cp" id="cp" readonly="readonly" value="${userConnected.codePostal}"/><br />
		
		<label for="ville">Ville: </label>
		<input type="text" name="ville" id="ville" readonly="readonly" value="${userConnected.ville}"/><br />
		
		<button><a href="${pageContext.request.contextPath}/ModifierMonProfil">Modifier</a></button>
	</form>
	
</body>
</html>