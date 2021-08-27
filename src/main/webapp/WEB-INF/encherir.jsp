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
</header>
<main>


	<h1>D�tails vente</h1>
	
	<!-- Dans la premi�re partie de la page on affiche les infos que l'on r�cup�re 
	de la DAL, en partant de l'objet EnchereEC -->
	<h2>${articleEC.nomArticle}</h2>
	<label for="description">Description : </label>
	<textarea name="description" id="description" cols="10" rows="3">${articleEC.description}</textarea><br />
	
	<label for="categorie">Cat�gorie : ${articleEC.libelle}</label><br />
	
	
	
	<!-- S'il y a d�j� eu une ench�re sur le prix initial, c'est la derni�re faite qui s'affiche -->
	
	<label for="enchereActuelle">Meilleure offre : ${articleEC.enchereEc.montantEnchere} pts par ${articleEC.userAcheteur.pseudo}</label><br />
	

	
	<label for="prixInitial">Mise � prix : ${articleEC.prixInitial}</label><br />
	
	<label for="finEnchere">Fin de l'ench�re : ${articleEC.finEnchere}</label><br />
	
	
	<label for="retrait">Retrait : ${articleEC.rue} <br /> ${articleEC.codePostal} ${articleEC.ville}</label><br />
	
	 
	 <label for="vendeur">Vendeur : ${articleEC.userVendeur.pseudo}</label>
	 
	 
	<!--  Partie forulaire o� on r�cup�re la proposition d'ench�re du userEncherisseur -->
	
	<form action="${pageContext.request.contextPath}/Encherir" method="post">
		<label for="proposition">Ma proposition :</label>
		
		<!-- S'il n'y a pas encore d'ench�re sur le prix initial, la proposition doit au moins �tre sup�rieure au
		prixInitial de 1 pt -->
		<c:if test="${empty enchereEC.montantEnchere}">
		<input type="number" name="proposition" min="${articleEC.prixInitial+1}" value="${articleEC.prixInitial+1}" step="1"/>
		</c:if>
		<!-- S'il y a d�j� une ench�re sur le prix initial, la proposition doit au moins �tre sup�rieure � cette
		ench�re de 1 pt -->
		<c:if test="${!empty articleEC.encherEc.montantEnchere}">
		<input type="number" name="proposition" min="${enchereEC.montantEnchere+1}" value="${enchereEC.montantEnchere+1}" step="1"/>
		</c:if>
		<!--  On met un input de type hidden pour r�cup�rer le noArticle de l'enchereEC
		dont on aura besoin dans le Servelt pour acc�der � la DAL et r�cup�rer les infos
		sur l'articleEC et l'userEncherisseur -->
		<input type="hidden" value="${articleEC.noArticle}"name="noArticle"/>
		<input type="submit" name="encherir" value="Ench�rir" class="lienbouton"/>
			 
	</form>
	<c:if test="${!empty succesEnchere}">
		<p>Votre ench�re a bien �t� prise en compte.</p>
		<p>L'article sera � vous si personne ne surench�rit sur votre proposition avant la fin de l'ench�re.</p>
	</c:if>
	
	<c:if test ="${!empty erreurEnchere}">
		${erreurEnchere}
	</c:if>
</main>

<footer>

</footer>
</body>
</html>