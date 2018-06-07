<%@ page import="comp3095.assignment2.forms.StringUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="comp3095.assignment2.HtmlHelper"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.database.DatabaseHandler"%>
<%@ page import="comp3095.assignment2.database.models.Department"%>
<%@ page import="comp3095.assignment2.forms.impl.NewEmployeeForm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	DatabaseHandler db = DatabaseHandler.create(response);
	if (db == null || !Session.assertIsStarted(db, request, response)) return;

    NewEmployeeForm form = Session.getForm(session, NewEmployeeForm.class);
    boolean success = request.getParameter("success") != null;
%>
<!doctype html>
<html>

<head>
<title>New Employee - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">New Employee</h1>
		<hr>
		<form action="<%=form.getAction()%>" method="<%=form.getMethod()%>">
			<%
				HtmlHelper.writeFormRowStart(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getFirstName(), "text", "First Name", false);
					HtmlHelper.writeDivEnd(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getLastName(), "text", "Last Name", false);
					HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeDivEnd(out);

				HtmlHelper.writeFormRowStart(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getEmployeeId(), "text", "Employee Id", false);
					HtmlHelper.writeDivEnd(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getEmail(), "email", "Email", false);
					HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeDivEnd(out);

				HtmlHelper.writeFormRowStart(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getHireDate(), "date", "Hire Date", false);
					HtmlHelper.writeDivEnd(out);
					HtmlHelper.writeFormHalfColStart(out);

						List<Department> departments = db.departments.getAll();
						int lastDepartment = StringUtil.parseInt(form.getDepartment().getValue(), -1);

						HtmlHelper.writeSelectStart(out, form.getDepartment(), "Department");
						for (Department department : departments)
							HtmlHelper.writeOption(out, department.getId(), department.getName(), lastDepartment == department.getId());
						HtmlHelper.writeSelectEnd(out, form.getDepartment());

					HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeResetAndSubmit(out);
				HtmlHelper.writeConditionalCloseableMessage(out, !form.passedValidation(), false, form.getErrorMessage());
				HtmlHelper.writeConditionalCloseableMessage(out, success, true, "The employee was added successfully");
			%>
		</form>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
</body>

<%@page import="comp3095.assignment2.forms.StringUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="comp3095.assignment2.HtmlHelper"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.database.DatabaseHandler"%>
<%@ page import="comp3095.assignment2.database.models.Department"%>
<%@ page import="comp3095.assignment2.forms.impl.NewEmployeeForm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	DatabaseHandler db = DatabaseHandler.create(response);
	if (db == null || !Session.assertIsStarted(db, request, response)) return;

    NewEmployeeForm form = Session.getForm(session, NewEmployeeForm.class);
    boolean success = request.getParameter("success") != null;
%>
<!doctype html>
<html>

<head>
<title>New Employee - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">New Employee</h1>
		<hr>
		<form action="<%=form.getAction()%>" method="<%=form.getMethod()%>">
			<%
				HtmlHelper.writeFormRowStart(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getFirstName(), "text", "First Name", false);
					HtmlHelper.writeDivEnd(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getLastName(), "text", "Last Name", false);
					HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeDivEnd(out);

				HtmlHelper.writeFormRowStart(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getEmployeeId(), "text", "Employee Id", false);
					HtmlHelper.writeDivEnd(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getEmail(), "email", "Email", false);
					HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeDivEnd(out);

				HtmlHelper.writeFormRowStart(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getHireDate(), "date", "Hire Date", false);
					HtmlHelper.writeDivEnd(out);
					HtmlHelper.writeFormHalfColStart(out);

						List<Department> departments = db.departments.getAll();
						int lastDepartment = StringUtil.parseInt(form.getDepartment().getValue(), -1);

						HtmlHelper.writeSelectStart(out, form.getDepartment(), "Department");
						for (Department department : departments)
							HtmlHelper.writeOption(out, department.getId(), department.getName(), lastDepartment == department.getId());
						HtmlHelper.writeSelectEnd(out, form.getDepartment());

					HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeResetAndSubmit(out);
				HtmlHelper.writeConditionalCloseableMessage(out, !form.passedValidation(), false, form.getErrorMessage());
				HtmlHelper.writeConditionalCloseableMessage(out, success, true, "The employee was added successfully");
			%>
		</form>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
</body>

</html>