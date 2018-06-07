<<<<<<< HEAD
<%@ page import="java.util.ArrayList"%>
<%@ page import="comp3095.assignment2.forms.StringUtil"%>
<%@ page import="comp3095.assignment2.forms.FormField"%>
<%@ page import="comp3095.assignment2.HtmlHelper"%>
<%@ page import="comp3095.assignment2.database.models.Department"%>
<%@ page import="java.util.List"%>
<%@ page import="comp3095.assignment2.forms.impl.NewGroupForm"%>
<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.database.DatabaseHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	DatabaseHandler db = DatabaseHandler.create(response);
	if (db == null || !Session.assertIsStarted(db, request, response)) return;

	NewGroupForm form = Session.getForm(session, NewGroupForm.class);
	boolean success = request.getParameter("success") != null;
%>
<!doctype html>
<html>

<head>
<title>New Group - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
<link rel="stylesheet" href="/servlet_assignment_2/assets/css/group.css">
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
	<h1 class="display-5 text-center page-title">New Group</h1>
	<hr>
	<h3>Department</h3>
	<select class="form-control" id="departmentSelect">
		<option value="-1" selected>Select a value</option>
		<%
			List<Department> departments = db.departments.getAll();
			int id = StringUtil.parseInt(form.getDepartment().getValue(), -1);
			for (int i = 0; i < departments.size(); i++) {
				Department department = departments.get(i);
				HtmlHelper.writeOption(out, department.getId(), department.getName(), department.getId() == id);
			}
		%>
	</select>
	<div id="progress" class="progress" style="display: none">
		<div class="progress-bar progress-bar-striped active"
			role="progressbar" aria-valuenow="100" aria-valuemin="0"
			aria-valuemax="100" style="width: 100%">
			<span>Please wait<span class="dotdotdot"></span></span>
		</div>
	</div>
	<hr>
	<div id="error" class="alert alert-dark text-center" role="alert" style="display: none">
		The selected department has no employees
	</div>
	<%
		HtmlHelper.writeConditionalCloseableMessage(out, success, true, "The group was added successfully");
		HtmlHelper.writeConditionalCloseableMessage(out, !form.passedValidation(), false, form.getErrorMessage());
		
	%>
	<div id="createGroup" style="display:none">
		<form action="<%=form.getAction()%>" method="<%=form.getMethod()%>">

			<%
			HtmlHelper.writeFormControl(out, form.getDepartment(), null, null, false);
			HtmlHelper.writeFormControl(out, form.getName(), "text", "Group Name", false); 
			HtmlHelper.writeHr(out);
			%>
			<h3>Select employees to add to the group</h3>
			
			<%
				final int rows = 3;
				final int columns = 2;
				for (int row = 0; row < rows; row++) {
					HtmlHelper.writeFormRowStart(out);
					for (int column = 0; column < columns; column++) {
						HtmlHelper.writeFormHalfColStart(out);

						int index = row * columns + column;
						FormField current = form.getEmployee(index);

						HtmlHelper.writeSelectStart(out, current, "Employee " + (index + 1));
						HtmlHelper.writeOption(out, -1, "Please select an employee", false);
						HtmlHelper.writeSelectEnd(out, current);

						HtmlHelper.writeDivEnd(out);
					}
					HtmlHelper.writeDivEnd(out);
				}
				HtmlHelper.writeResetAndSubmit(out);
			%>
			
		</form>
	</div>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
	<script>
		var prevDepartment = <%
			out.print(id);
		%>;
		var prevEmployees = {
			<%
			if (!form.isNew()) {
				ArrayList<FormField> validEmployees = form.getValidEmployees();
				for (FormField field : validEmployees) {
					out.print("\"");
					out.print(field.getName());
					out.print("\":\"");
					out.print(field.getValue());
					out.print("\",\n");
				}
			}
			%>
		};
	</script>
	<script src="/servlet_assignment_2/assets/js/common.js"
		type="text/javascript"></script>
	<script src="/servlet_assignment_2/assets/js/group.js"
		type="text/javascript"></script>
</body>

=======
<%@page import="java.util.ArrayList"%>
<%@page import="comp3095.assignment2.forms.StringUtil"%>
<%@ page import="comp3095.assignment2.forms.FormField"%>
<%@ page import="comp3095.assignment2.HtmlHelper"%>
<%@ page import="comp3095.assignment2.database.models.Department"%>
<%@ page import="java.util.List"%>
<%@ page import="comp3095.assignment2.forms.impl.NewGroupForm"%>
<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.database.DatabaseHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	DatabaseHandler db = DatabaseHandler.create(response);
	if (db == null || !Session.assertIsStarted(db, request, response)) return;

	NewGroupForm form = Session.getForm(session, NewGroupForm.class);
	boolean success = request.getParameter("success") != null;
%>
<!doctype html>
<html>

<head>
<title>New Group - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
<link rel="stylesheet" href="/servlet_assignment_2/assets/css/group.css">
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
	<h1 class="display-5 text-center page-title">New Group</h1>
	<hr>
	<h3>Department</h3>
	<select class="form-control" id="departmentSelect">
		<option value="-1" selected>Select a value</option>
		<%
			List<Department> departments = db.departments.getAll();
			int id = StringUtil.parseInt(form.getDepartment().getValue(), -1);
			for (int i = 0; i < departments.size(); i++) {
				Department department = departments.get(i);
				HtmlHelper.writeOption(out, department.getId(), department.getName(), department.getId() == id);
			}
		%>
	</select>
	<div id="progress" class="progress" style="display: none">
		<div class="progress-bar progress-bar-striped active"
			role="progressbar" aria-valuenow="100" aria-valuemin="0"
			aria-valuemax="100" style="width: 100%">
			<span>Please wait<span class="dotdotdot"></span></span>
		</div>
	</div>
	<hr>
	<div id="error" class="alert alert-dark text-center" role="alert" style="display: none">
		The selected department has no employees
	</div>
	<%
		HtmlHelper.writeConditionalCloseableMessage(out, success, true, "The group was added successfully");
		HtmlHelper.writeConditionalCloseableMessage(out, !form.passedValidation(), false, form.getErrorMessage());
		
	%>
	<div id="createGroup" style="display:none">
		<form action="<%=form.getAction()%>" method="<%=form.getMethod()%>">

			<%
			HtmlHelper.writeFormControl(out, form.getDepartment(), null, null, false);
			HtmlHelper.writeFormControl(out, form.getName(), "text", "Group Name", false); 
			HtmlHelper.writeHr(out);
			%>
			<h3>Select employees to add to the group</h3>
			
			<%
				final int rows = 3;
				final int columns = 2;
				for (int row = 0; row < rows; row++) {
					HtmlHelper.writeFormRowStart(out);
					for (int column = 0; column < columns; column++) {
						HtmlHelper.writeFormHalfColStart(out);

						int index = row * columns + column;
						FormField current = form.getEmployee(index);

						HtmlHelper.writeSelectStart(out, current, "Employee " + (index + 1));
						HtmlHelper.writeOption(out, -1, "Please select an employee", false);
						HtmlHelper.writeSelectEnd(out, current);

						HtmlHelper.writeDivEnd(out);
					}
					HtmlHelper.writeDivEnd(out);
				}
				HtmlHelper.writeResetAndSubmit(out);
			%>
			
		</form>
	</div>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
	<script>
		var prevDepartment = <%
			out.print(id);
		%>;
		var prevEmployees = {
			<%
			if (!form.isNew()) {
				ArrayList<FormField> validEmployees = form.getValidEmployees();
				for (FormField field : validEmployees) {
					out.print("\"");
					out.print(field.getName());
					out.print("\":\"");
					out.print(field.getValue());
					out.print("\",\n");
				}
			}
			%>
		};
	</script>
	<script src="/servlet_assignment_2/assets/js/common.js"
		type="text/javascript"></script>
	<script src="/servlet_assignment_2/assets/js/group.js"
		type="text/javascript"></script>
</body>

>>>>>>> refs/heads/dev-arzu
</html>