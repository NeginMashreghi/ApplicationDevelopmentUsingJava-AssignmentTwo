<%@ page import="comp3095.assignment2.database.models.Group"%>
<%@ page import="comp3095.assignment2.database.models.Department"%>
<%@ page import="java.util.List"%>
<%@ page import="comp3095.assignment2.HtmlHelper"%>
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
<title>View Groups - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">View Groups</h1>
		<hr>

		<div id="groupDetails">
			<%
				HtmlHelper.writeFormRowStart(out);
					HtmlHelper.writeFormHalfColStart(out);
			%>
				<select class="form-control" id="departmentSelect">
					<option value="-1" selected>Select a value</option>
					<%
					List<Department> departments = db.departments.getAll();
					for (int i = 0; i < departments.size(); i++) {
						Department department = departments.get(i);
						HtmlHelper.writeOption(out, department.getId(), department.getName(), false);
					}
					%>
				</select>
			<%
					HtmlHelper.writeDivEnd(out);
					HtmlHelper.writeFormHalfColStart(out);
						
					HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeDivEnd(out);
			%>
			<hr>
			<h3>Group Members:</h3>
			<table class="table table-sm table-bordered">
				<thead class="thead-light">
					<tr>
						<th scope="col">Name</th>
						<th scope="col">Employee No</th>
					</tr>
				</thead>
				<tbody id="membersBody">
				</tbody>
			</table>
		</div>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
</body>

</html>