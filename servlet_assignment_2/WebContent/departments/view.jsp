<<<<<<< HEAD
<%@ page import="comp3095.assignment2.database.models.Department"%>
<%@ page import="java.util.List"%>
<%@ page import="comp3095.assignment2.TableHelper"%>
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
<title>View Departments - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">View Departments</h1>
		<hr>

		<table class="table table-sm table-bordered">
			<thead class="thead-light">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Name</th>
					<th scope="col">Location</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Department> departments = db.departments.getAll();
				
				for (Department department : departments) {
					TableHelper.writeRowStart(out);
					TableHelper.writeHeader(out, Integer.toString(department.getId()), "row");
					TableHelper.writeDefinition(out, department.getName(), null);
					TableHelper.writeDefinition(out, department.getLocation(), null);
					TableHelper.writeRowEnd(out);
				}
				%>
			</tbody>
		</table>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
</body>

=======
<%@page import="comp3095.assignment2.database.models.Department"%>
<%@page import="java.util.List"%>
<%@page import="comp3095.assignment2.TableHelper"%>
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
<title>View Departments - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">View Departments</h1>
		<hr>

		<table class="table table-sm table-bordered">
			<thead class="thead-light">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Name</th>
					<th scope="col">Location</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Department> departments = db.departments.getAll();
				
				for (Department department : departments) {
					TableHelper.writeRowStart(out);
					TableHelper.writeHeader(out, Integer.toString(department.getId()), "row");
					TableHelper.writeDefinition(out, department.getName(), null);
					TableHelper.writeDefinition(out, department.getLocation(), null);
					TableHelper.writeRowEnd(out);
				}
				%>
			</tbody>
		</table>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
</body>

>>>>>>> refs/heads/dev-arzu
</html>