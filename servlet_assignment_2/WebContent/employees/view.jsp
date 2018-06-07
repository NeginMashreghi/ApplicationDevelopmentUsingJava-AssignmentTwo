<%@page import="comp3095.assignment2.forms.StringUtil"%>
<%@page import="comp3095.assignment2.database.models.Employee"%>
<%@page import="java.util.List"%>
<%@page import="comp3095.assignment2.database.models.Department"%>
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
	<title>View Employees - Servlet Assignment 2</title>
	<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">View Employees</h1>
		<hr>

		<table class="table table-sm table-bordered">
			<thead class="thead-light">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Name</th>
					<th scope="col">Email</th>
					<th scope="col">Role</th>
					<th scope="col">Employee Id</th>
					<th scope="col">Hire Date</th>
					<th scope="col">Department Name</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Employee> employees = db.employees.getAll();
				for (Employee employee : employees) {
					TableHelper.writeRowStart(out);
					TableHelper.writeHeader(out, Integer.toString(employee.getId()), "row");
					TableHelper.writeDefinition(out, employee.getFirstName() + ' ' + employee.getLastName(), null);
					TableHelper.writeDefinition(out, employee.getEmail(), null);
					TableHelper.writeDefinition(out, employee.getRole(), null);
					TableHelper.writeDefinition(out, Integer.toString(employee.getEmployeeId()), null);
					TableHelper.writeDefinition(out, StringUtil.formatDate(employee.getHireDate()), null);
					TableHelper.writeDefinition(out, db.departments.get(employee.getDepartment()).getName(), null);
					TableHelper.writeRowEnd(out);
				}
				%>
			</tbody>
		</table>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
</body>

</html>