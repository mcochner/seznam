<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>USERS</h1>
	<%-- ADD USER --%>
	<form:form action="edit_user" method="post">
		<input type="hidden" name="uid" value="0" />
		<input type="submit" value="Add User">
	</form:form>
	<form:form action="test_populate" method="post">
		<input type="submit" value="test_Populate">
	</form:form>
	<hr>
	<%-- TABLE --%>
	<table border="1">
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Started</th>
			<th>Type</th>
			<th>URL</th>
			<th>UID</th>
		</tr>

		<!-- 	$ se pta kontroleru -->
		<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.firstName}" /></td>
				<td><c:out value="${user.lastName}" /></td>
				<td><fmt:formatDate value="${user.dateStarted}"
						pattern="yyyy-MM-dd" /></td>
				<td><c:out value="${user.type}" /></td>
				<td><c:out value="${user.url}" /></td>
				<td><c:out value="${user.uid}" /></td>
				<td><a href="/edit_user?uid=${user.uid}"> <img
						src="/resources/edit.png" width="30" alt="Edit">
				</a></td>
				<td><a href="/delete_user?uid=${user.uid}"> <img
						src="/resources/delete.png" width="30" alt="Delete">
				</a></td>

			</tr>
		</c:forEach>
	</table>

</body>
</html>