<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%-- <form:errors path="*"></form:errors> --%>

	<h1>USERS</h1>

	<form:form method="POST" action="/main" modelAttribute="user">
		<form:errors path="*"></form:errors>
		<table>
			<tr>
				<td><form:label path="firstName">First Name</form:label></td>
				<td><form:input path="firstName" />
					<form:errors path="firstName"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Last Name</form:label></td>
				<td><form:input path="lastName" />
					<form:errors path="lastName"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="dateStarted">Date Started</form:label></td>
				<td><form:input path="dateStarted" /></td>
			</tr>

			<tr>
				<td><form:label path="url">URL</form:label></td>
				<td><form:input path="url" /></td>
			</tr>


			<tr>
				<td><form:radiobutton path="type" value="EMPLOYEE" />Employee
					<form:radiobutton path="type" value="CUSTOMER" />Customer</td>
			</tr>
			<tr>
				<td><form:label path="uid">UID</form:label></td>


				<td><form:input readonly="true" path="uid" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>