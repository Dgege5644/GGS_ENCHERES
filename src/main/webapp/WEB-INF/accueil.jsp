<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Accueil GGS Ench�res</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<c:if test="${!empty succesVendreUnArticle}"> L'article � bien �t� ajout� a vos articles � vendre!</c:if>
	<c:if test="${!empty succesModifProfil}"> Les modifications ont bien �t� enregistr�es dans votre profil</c:if>
	<!-- Adresse = servlet de connexion -->
	<c:if test="${empty succes}"> <!-- l'utilsateur arrive � l'accueil pour la premi�re fois   -->
		<a href="${pageContext.request.contextPath}/Connexion">S'inscrire - Se connecter</a>
	</c:if>
	
	<c:if test="${!empty erreur}">  <!-- Si utilisateur n'existe pas en base de donn�es(apr�s une tentative de connexion, utilisateur == null) alors on affiche un message d'erreur sur la page d'accueil  -->
		<p>${erreur}</p>
	</c:if>
	
	<c:if test="${!empty succes}"> <!-- l'utilsateur existe en base de donn�es et est connect�; il a alors acc�s aux autres fonctionnalit�s du site   -->
		<nav>
			<a href="${pageContext.request.contextPath}/Vendre">Vendre un article</a>
			<a href="${pageContext.request.contextPath}/AfficherMonProfil">Mon Profil</a>
			<a href="${pageContext.request.contextPath}/Deconnexion">Se D�connecter</a>
		</nav>
		<p>${userConnected.prenom} ${userConnected.nom} est connect�(e)</p> <!-- les pr�nom et nom de l'utilisateur sont r�cup�r�s en base de donn�es   -->
	</c:if> 
	 
	<h1>Liste des ench�res</h1>
	
	<form action="/Accueil" method="POST">
	
		<label for="filtre">Filtres : </label><br />
		<input type="search" placeholder="Le nom de l'article contient" /><br />
		<label for="categorie">Cat�gorie : </label><br/>
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
	
	
	
	<c:if test="${!empty listeEncheres}"> <!-- Si la liste cr��e des (articles mis en vente) n'est pas vide,...   -->
	
	<p>Il y a ${listeEncheres.size()} ench�res en cours, Bonne chance!</p> <!-- Affiche la taille de la variable "listeEncheres" -->
	
 		<c:forEach var="enchereEC" items ="${listeEncheres}"> <!-- pour chaque ench�re pr�sente dans la liste des ench�res   -->
			<ul>
			
			<c:if test="${!empty succes}">
				<li>
					<a href="${pageContext.request.contextPath}/Encherir">${enchereEC.articleEC.nomArticle}</a>  <!-- Mettre le  lien qui conduira vers le d�tail de l'ench�re lorsque l'utilisateur acheteur sera connect�-->
				</li>
			</c:if>
			
			<c:if test="${empty succes}">
				<li>
					${enchereEC.articleEC.nomArticle} <!-- Mettre le nom de l'article sur une ligne, puis transformer en un lien qui conduira vers le d�tail de l'ench�re lorsque l'utilisateur acheteur sera connect�--> 
				</li>
			</c:if>
			
				<li>
					Prix: ${enchereEC.montantEnchere} <!-- TODO : Mettre la plus haute ench�re en cours sinon le prix initial sur une autre ligne -->
				</li>
				<li>
					Fin de l'enchere: ${enchereEC.dateEnchere} <!-- Mettre la date et l'heure de fin d'ench�re sur une autre ligne  -->
				</li>
			<c:if test="${!empty succes}">
				<li>
					Vendeur: <a href="#">${enchereEC.userEncherisseur.nom}</a> <!-- et mettre sur une ligne le nom du propri�taire puis transformer en un lien qui conduira vers le d�tail de l'utilisateur vendeur lorsque l'utilisateur vendeur sera connect� -->
				</li>
			</c:if>
			<c:if test="${empty succes}">
				<li>
					Vendeur: ${enchereEC.userEncherisseur.nom} <!-- et mettre sur une ligne le nom du propri�taire puis transformer en un lien qui conduira vers le d�tail de l'utilisateur vendeur lorsque l'utilisateur vendeur sera connect� -->
				</li>
			</c:if>
			</ul>
		</c:forEach> 
	</c:if>
	
	<c:if test="${!empty listeEncheresNulle}"> <!-- Si la liste cr��e des (articles mis en vente) est vide, ...   -->
	
		${listeEncheresNulle} <!-- Affiche le message d�fini dans la servlet "Aucune ench�re en cours, Revenez ult�rieurement!"   -->
		
	</c:if>
	
	<c:if test="${!empty erreurListeEncheres}">
			${erreurListeEncheres} <!-- Affiche le message d�fini dans la servlet "Erreur lors de l'affichage de la liste"   -->
	</c:if>


</body>
</html>