<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.database.DatabaseHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    DatabaseHandler db = DatabaseHandler.create(response);
    if (db == null || !Session.assertIsStarted(db, request, response)) return;

%>
<!doctype html>
<html>

<head>
<title>Page Title - Servlet Assignment 2</title>
<jsp:include page="WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">Page Title</h1>
		<hr>

	</main>

	<jsp:include page="WEB-INF/footer.jsp" />
</body>

</html>