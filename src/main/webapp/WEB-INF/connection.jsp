<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<label for="identifiant">Identifiant: </label>
	<input type="text" name="identifiant" id="identifiant" placeholder="Pseudo ou email"/><br />
	
	<label for="mdp">Mot de passe: </label>
	<input type="text" name="mdp" id="mdp" placeholder="Mot de passe"/><br />
	
	<input type="submit" id="connexion" value="Connexion" />
	
	<div id="options">
		
		
		<input type="checkbox" name="ssdm" />
		<label for="ssdm">Se souvenir de moi</label><br />
		
		<a href="#">Mot de passe oublié</a>
	</div>
	
	<a href="#" id="boutonCompte">Creer un compte</a>
</body>
</html>