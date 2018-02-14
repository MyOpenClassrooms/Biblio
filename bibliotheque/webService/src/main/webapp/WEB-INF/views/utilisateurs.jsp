<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	

<table border="1" width="80%">
	<tr>
		<th>ID</th>
		<th>Nom</th>
		<th>prenom</th>
	</tr>
	<c:forEach items="${utilisateurs}" var="u">
		<tr>
			<td>${u.idUser }</td>
			<td>${u.nom}</td>
			<td>${u.prenom }</td>
			
		</tr>
	</c:forEach>
</table>
</body>
</html>