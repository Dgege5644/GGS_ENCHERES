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
	<c:if test="${empty succes}"> <!-- l'utilsateur arrive à l'accueil pour la première fois   -->
		<a href="${pageContext.request.contextPath}/Connexion">S'inscrire - Se connecter</a>
	</c:if>
	
	<c:if test="${!empty erreur}">  <!-- Si utilisateur n'existe pas en base de données(après une tentative de connexion, utilisateur == null) alors on affiche un message d'erreur sur la page d'accueil  -->
		<p>${erreur}</p>
	</c:if>
	
	<c:if test="${!empty succes}"> <!-- l'utilsateur existe en base de données et est connecté; il a alors accès aux autres fonctionnalités du site   -->
		<nav>
			<a href="${pageContext.request.contextPath}/Vendre">Vendre un article</a>
			<a href="${pageContext.request.contextPath}/AfficherMonProfil">Mon Profil</a>
			<a href="${pageContext.request.contextPath}/Deconnexion">Se Déconnecter</a>
		</nav>
		<p>${userConnected.prenom} ${userConnected.nom} est connecté(e)</p> <!-- les prénom et nom de l'utilisateur sont récupérés en base de données   -->
	</c:if> 
	 
	<h1>Liste des enchères</h1>
	
	<form action="/Accueil" method="POST">
	
		<label for="filtre">Filtres : </label><br />
		<input type="search" placeholder="Le nom de l'article contient" /><br />
		<label for="categorie">Catégorie : </label><br/>
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
	
	<c:if test="${!empty listeEncheres}"> <!-- Si la liste créée des (articles mis en vente) n'est pas vide,...   -->
	
		<c:forEach items ="${listeEncheres}" var ="enchere"> <!-- pour chaque enchère présente dans la liste des enchères   -->
			<ul>
				<li>
					${enchere.article.nomArticle} <!-- Mettre le nom de l'article sur une ligne, puis transformer en un lien qui conduira vers le détail de l'enchère lorsque l'utilisateur acheteur sera connecté--> 
				</li>
				<li>
					${enchere.article.montantEnchere} <!-- TODO : Mettre la plus haute enchère en cours sinon le prix initial sur une autre ligne -->
				</li>
				<li>
					${enchere.article.finEnchere} <!-- Mettre la date et l'heure de fin d'enchère sur une autre ligne  -->
				</li>
				<li>
					${enchere.utilisateur.nom} <!-- et mettre sur une ligne le nom du propriétaire puis transformer en un lien qui conduira vers le détail de l'utilisateur vendeur lorsque l'utilisateur vendeur sera connecté -->
				</li>
			</ul>
		</c:forEach>
	</c:if>
	
	<c:if test="${empty listeEncheres}"> <!-- Si la liste créée des (articles mis en vente) est vide, ...   -->
	
		<p>Aucune enchère en cours, Revenez ultérieurement!</p> <!-- Afficher ce message   -->
		
	</c:if>


</body>
</html>