<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Accueil GGS Enchères</title>
<!-- ${pageContext.request.contextPath} permet d'avoir un lien absolu vers ce qui est noté derrière.
 Dans le cas suivant, cela cherche le /css/style.css dans tout le projet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<!--  définition des messages à afficher selon les actions réalisées
	1er test : si la mise en vente d'un article s'est bien passée -->
	<c:if test="${!empty succesVendreUnArticle}"> L'article a bien été ajouté à vos articles à vendre!</c:if>
	<!-- 2ème test : si la modification du prfil s'est bien passée -->
	<c:if test="${!empty succesModifProfil}"> Les modifications ont bien été enregistrées dans votre profil</c:if>
	
	<!-- 3ème test : si la connexion avec identifiant et mot de passe s'est bien passée, affiche 
	un lien S'inscrire- Se connecter dant de connexion -->
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
		<c:if test="${!empty succes}">
	<div>
		<input type="radio" name="historique"/>Achats<br /> <!-- TODO créer des JRadioButton -->
		<input type="checkbox" name="encheresouvertes"/>enchères ouvertes<br /><!-- TODO créer des JCheckbox -->
		<input type="checkbox" name="mesencheres"/>mes enchères<br />
		<input type="checkbox" name="mesencheresremportees"/>mes enchères remportées<br />
	</div>
	<div>
		<input type="radio" name="historique"/>Mes ventes<br />
		<input type="checkbox" name="mesventesencours"/>mes ventes en cours<br />
		<input type="checkbox" name="ventesnondebutees"/>ventes non débutées<br />
		<input type="checkbox" name="ventesterminees"/>ventes terminées<br />
	</div>
</c:if>
		<input type="submit" value="Rechercher"/>

	</form>
	
	
	
	<c:if test="${!empty listeEncheres}"> <!-- Si la liste créée des (articles mis en vente) n'est pas vide,...   -->
	
	<p>Il y a ${listeEncheres.size()} enchères en cours, Bonne chance!</p> <!-- Affiche la taille de la variable "listeEncheres" -->
	
 		<c:forEach var="enchereEC" items ="${listeEncheres}"> <!-- pour chaque enchère présente dans la liste des enchères   -->
			<ul>
			
			<c:if test="${!empty succes}">
				<li>
					<a href="${pageContext.request.contextPath}/Encherir?noArticle=${enchereEC.articleEC.noArticle}">${enchereEC.articleEC.nomArticle}</a>  <!-- Mettre le  lien qui conduira vers le détail de l'enchère lorsque l'utilisateur acheteur sera connecté-->
				</li>
			</c:if>
			
			<c:if test="${empty succes}">
				<li>
					${enchereEC.articleEC.nomArticle} <!-- Mettre le nom de l'article sur une ligne, puis transformer en un lien qui conduira vers le détail de l'enchère lorsque l'utilisateur acheteur sera connecté--> 
				</li>
			</c:if>
			
				<li>
					Prix: ${enchereEC.montantEnchere} <!-- TODO : Mettre la plus haute enchère en cours sinon le prix initial sur une autre ligne -->
				</li>
				<li>
					Fin de l'enchere: ${enchereEC.dateEnchere} <!-- Mettre la date et l'heure de fin d'enchère sur une autre ligne  -->
				</li>
			<c:if test="${!empty succes}">
				<li>
					Vendeur: <a href="${pageContext.request.contextPath}/AfficherProfilVendeur?no_utilisateur=${enchereEC.userEncherisseur.no_utilisateur}">${enchereEC.userEncherisseur.nom}</a> <!-- et mettre sur une ligne le nom du propriétaire puis transformer en un lien qui conduira vers le détail de l'utilisateur vendeur lorsque l'utilisateur vendeur sera connecté -->
				</li>
			</c:if>
			<c:if test="${empty succes}">
				<li>
					Vendeur: ${enchereEC.userEncherisseur.nom} <!-- et mettre sur une ligne le nom du propriétaire puis transformer en un lien qui conduira vers le détail de l'utilisateur vendeur lorsque l'utilisateur vendeur sera connecté -->
				</li>
			</c:if>
			</ul>
		</c:forEach> 
	</c:if>
	
	<c:if test="${!empty listeEncheresNulle}"> <!-- Si la liste créée des (articles mis en vente) est vide, ...   -->
	
		${listeEncheresNulle} <!-- Affiche le message défini dans la servlet "Aucune enchère en cours, Revenez ultérieurement!"   -->
		
	</c:if>
	
	<c:if test="${!empty erreurListeEncheres}">
			${erreurListeEncheres} <!-- Affiche le message défini dans la servlet "Erreur lors de l'affichage de la liste"   -->
	</c:if>


</body>
</html>