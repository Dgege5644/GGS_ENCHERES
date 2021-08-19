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
	<form action="${pageContext.request.contextPath}/SupprimerMonCompte" method="post">
	
		<h3>Supprimer votre compte?</h3>
		
		
		<input type="submit" value="Supprimer"/>
		
		
		<button><a href="${pageContext.request.contextPath}/Accueil">Annuler</a></button>
		
	</form>
	

</body>
</html>