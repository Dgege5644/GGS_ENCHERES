<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>vendre un artcile</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
</head>
<body>
<h1>Nouvelle vente</h1>
<form action="${pageContext.request.contextPath}/Vendre" method="post">

	<label for="article">Article: </label>
	<input type="text" name="article" placeholder="nom de l'article" required="required"/><br />
	
	<label for="description">Description: </label>
	<textarea name="description" id="descriptionArticle" cols="30" rows="5" required="required">description de l'article</textarea><br />
	
	<label for="categorie">Catégorie: </label>
	<select name="categorie" id="categorie" required="required">
		<optgroup label ="catégories">
			<option value ="Toutes">Toutes</option>
			<option value ="Informatique">Informatique</option>
			<option value ="Ameublement">Ameublement</option>
			<option value ="Vetement">Vêtement</option>
			<option value ="Sport&Loisirs">Sport et Loisirs</option>
		</optgroup>
	</select><br />
	
	<label for="fichierPhotoArticle">Photo de l'article: </label>
	<input type="file" id="fichierPhotoArticle" name="fichierPhotoArticle" accept="image/png, image/jpeg"/> <br />
	
	<label for="miseAPrix">Mise à prix: </label>
	<input type="number" name="miseAPrix" id="miseAPrix" min="0" step="1" /><br />
	
	<label for="dateDebut">Début de l'enchère: </label>
	<input type="datetime-local" name="dateDebut" id="dateDebut"/><br />
	
	<label for="dateFin">Fin de l'enchère: </label>
	<input type="datetime-local" name="dateFin" id="dateFin" />
	
	<fieldset>
		<legend>Retrait</legend>
		
		<label for="rue">Rue: </label>
		<input type="text" name="rue" id="rue" value="${userConnected.rue}"/><br />
		
		<label for="cp">Code postal: </label>
		<input type="text" name="cp" id="cp" value="${userConnected.codePostal}"/><br />
		
		<label for="ville">Ville: </label>
		<input type="text" name="ville" id="ville" value="${userConnected.ville}"/>
	</fieldset><br />
	
	<input type="submit" id="enregistrerArticle" value="Enregistrer"/>
	<a href="${pageContext.request.contextPath}/Accueil" id="boutonAnnuler">Annuler</a>
	



</form>
</body>
</html>