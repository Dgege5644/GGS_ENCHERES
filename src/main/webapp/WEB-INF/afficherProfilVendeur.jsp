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
<header>
	<%@include file="entete.html" %>
</header>
<main>
	<form action="./AfficherProfilVendeur" method="get">
	<!-- remplacé par le logo qui renvoie vers l'accueil
	<a href="${pageContext.request.contextPath}/Accueil">Retour à l'accueil</a><br />
	 -->
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
			
	</form>
</main>
<footer>

</footer>
	
</body>
</html>