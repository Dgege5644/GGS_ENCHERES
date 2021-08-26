<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<form action="./AfficherProfilVendeur" method="get">
	
	<a href="${pageContext.request.contextPath}/Accueil">Retour à l'accueil</a><br />
	
		<label for="pseudo">Pseudo: </label>
		
		<input type="text" name="pseudo" id="pseudo" readonly="readonly" value="${userVendeur.pseudo}"/><br />
		
		<label for="nom">Nom: </label>
		<input type="text" name="nom" id="nom" readonly="readonly" value="${userVendeur.nom}"/><br />
		
		<label for="prenom">Prénom: </label>
		<input type="text" name="prenom" id="prenom" readonly="readonly" value="${userVendeur.prenom}"/><br />
		
		<label for="email">Email: </label>
		<input type="email" name="email" id="email" readonly="readonly" value="${userVendeur.email}"/><br />
		
		<label for="telephone">Téléphone: </label>
		<input type="text" name="telephone" id="telephone" readonly="readonly" value="${userVendeur.telephone}"/><br />
		
		<label for="rue">Rue: </label>
		<input type="text" name="rue" id="rue" readonly="readonly" value="${userVendeur.rue}"/><br />
		
		<label for="cp">Code postal: </label>
		<input type="text" name="cp" id="cp" readonly="readonly" value="${userVendeur.codePostal}"/><br />
		
		<label for="ville">Ville: </label>
		<input type="text" name="ville" id="ville" readonly="readonly" value="${userVendeur.ville}"/><br />
		
	</form>
</body>
</html>