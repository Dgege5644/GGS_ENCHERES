<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Accueil GGS Ench�res</title>
<!-- ${pageContext.request.contextPath} permet d'avoir un lien absolu vers ce qui est not� derri�re.
 Dans le cas suivant, cela cherche le /css/style.css dans tout le projet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<header>
	<%@include file="entete.html" %>
	<!--  d�finition des messages � afficher selon les actions r�alis�es--!>
	
	<!-- test : l'utilsateur arrive � l'accueil pour la premi�re fois   -->
	<c:if test="${empty succes}"> 
		<a href="${pageContext.request.contextPath}/Connexion" id="sinscrire">S'inscrire - Se connecter</a><!-- Adresse = servlet de connexion -->
	</c:if>
	
	<!-- test : si la connexion avec identifiant et mot de passe s'est bien pass�e -->
	<c:if test="${!empty succes}"> 
	
		<!-- affichage du message : pr�nom nom de l'utilisateur (r�cup�r�s en BDD) est connect�(e)   -->
		<p>${userConnected.prenom} ${userConnected.nom} est connect�(e)</p>
		
		<!-- affichage des autres fonctionnalit�s du site organis�es dans un volet de navigation   -->
		<nav id="navigation">
			<a href="${pageContext.request.contextPath}/Vendre">Vendre un article</a>
			<a href="${pageContext.request.contextPath}/AfficherMonProfil">Mon Profil</a>
			<a href="${pageContext.request.contextPath}/Deconnexion">Se D�connecter</a>
		</nav>
	</c:if> 
</header>
<main>	 
	<!--test : si la mise en vente d'un article s'est bien pass�e -->
	<c:if test="${!empty succesVendreUnArticle}"><h2> L'article a bien �t� ajout� � vos articles � vendre!</h2></c:if>
	<!--test : si la modification du prfil s'est bien pass�e -->
	<c:if test="${!empty succesModifProfil}"><h2>Les modifications ont bien �t� enregistr�es dans votre profil</h2></c:if>
	
	<!--test : si la suppression du prfil s'est bien pass�e -->
	<c:if test="${!empty suppressionCompte}"><h2>Votre compte a �t� correctement supprim�!</h2></c:if> 
	
	<!--test : si la d�connexion du profil s'est bien pass�e -->
	<c:if test="${!empty succesDeconnexion}"><h2>Vous avez correctement �t� d�connect�(e)!</h2></c:if>
	
	<!--test : si l'ench�re s'est bien pass�e-->
	<c:if test="${!empty succesEnchere}">
		<h2>Votre ench�re a bien �t� prise en compte!</h2>
		<p id = "texteEnchere">Vous serez peut- �tre bient�t l'heureux propri�taire de cet article</p>
	</c:if>
	
	<c:if test="${!empty erreur}"><!-- Si utilisateur n'existe pas en base de donn�es(apr�s une tentative de connexion, utilisateur == null) alors on affiche un message d'erreur sur la page d'accueil  -->  
		<p>${erreur}</p>
	</c:if>


	<h1>Liste des ench�res</h1>
	
	<form action="./Accueil" method="POST">
	
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
		
		<c:if test="${!empty succes}">
			<div id="choixrecherche">	
				<div>
					<input type="radio" name="historique" id="achats"/>Achats<br /> <!-- TODO cr�er des JRadioButton -->
					<input type="checkbox" name="encheresouvertes" id="encheresouvertes"/>ench�res ouvertes<br /><!-- TODO cr�er des JCheckbox -->
					<input type="checkbox" name="mesencheres" id="mesencheres"/>mes ench�res<br />
					<input type="checkbox" name="mesencheresremportees" id="mesencheresremportees"/>mes ench�res remport�es<br />
				</div>
			
				<div>
					<input type="radio" name="historique" id="mesventes"/>Mes ventes<br />
					<input type="checkbox" name="mesventesencours" id="mesvenetesencours"/>mes ventes en cours<br />
					<input type="checkbox" name="ventesnondebutees" id="ventesnondebutees"/>ventes non d�but�es<br />
					<input type="checkbox" name="ventesterminees" id="ventesterminees"/>ventes termin�es<br />
				</div>
			</div>
		</c:if>
			<input type="submit" value="Rechercher"/>
		
	</form>
	
		<c:if test="${!empty listeEncheres}"> <!-- Si la liste cr��e des (articles mis en vente) n'est pas vide,...   -->
		
			<p>Il y a ${listeEncheres.size()} ench�res en cours, Bonne chance!</p> <!-- Affiche la taille de la variable "listeEncheres" -->
		
			<div id="affichagelisteencheres">
			
		 		<c:forEach var="enchereEC" items ="${listeEncheres}"> <!-- pour chaque ench�re pr�sente dans la liste des ench�res   -->
					<ul>
	
					<c:if test="${!empty succes}">
						<li>
							<a href="${pageContext.request.contextPath}/Encherir?noArticle=${enchereEC.articleEC.noArticle}">${enchereEC.articleEC.nomArticle}</a>  <!-- Mettre le  lien qui conduira vers le d�tail de l'ench�re lorsque l'utilisateur acheteur sera connect�-->
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
							Fin de l'enchere: ${enchereEC.articleEC.finEnchere} <!-- Mettre la date et l'heure de fin d'ench�re sur une autre ligne  -->
						</li>
					<c:if test="${!empty succes}">
						<li>
							Vendeur: <a href="${pageContext.request.contextPath}/AfficherProfilVendeur?no_utilisateur=${enchereEC.userEncherisseur.no_utilisateur}">${enchereEC.userEncherisseur.pseudo}</a> <!-- et mettre sur une ligne le nom du propri�taire puis transformer en un lien qui conduira vers le d�tail de l'utilisateur vendeur lorsque l'utilisateur vendeur sera connect� -->
						</li>
					</c:if>
					<c:if test="${empty succes}">
						<li>
							Vendeur: ${enchereEC.userEncherisseur.pseudo} <!-- et mettre sur une ligne le nom du propri�taire puis transformer en un lien qui conduira vers le d�tail de l'utilisateur vendeur lorsque l'utilisateur vendeur sera connect� -->
						</li>
					</c:if>
				</ul>
				</c:forEach> 
			
			</c:if>
		</div>
		
		
	<c:if test="${!empty listeEncheresNulle}"> <!-- Si la liste cr��e des (articles mis en vente) est vide, ...   -->
	
		${listeEncheresNulle} <!-- Affiche le message d�fini dans la servlet "Aucune ench�re en cours, Revenez ult�rieurement!"   -->
		
	</c:if>
	
	<c:if test="${!empty erreurListeEncheres}">
			${erreurListeEncheres} <!-- Affiche le message d�fini dans la servlet "Erreur lors de l'affichage de la liste"   -->
	</c:if>
</main>
<footer>

</footer>

</body>
</html>