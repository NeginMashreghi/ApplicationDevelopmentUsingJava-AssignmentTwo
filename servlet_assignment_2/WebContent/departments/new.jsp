<%@ page import="comp3095.assignment2.forms.FormField"%>
<%@ page import="comp3095.assignment2.HtmlHelper"%>
<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.database.DatabaseHandler"%>
<%@ page import="comp3095.assignment2.forms.impl.NewDepartmentForm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	DatabaseHandler db = DatabaseHandler.create(response);
	if (db == null || !Session.assertIsStarted(db, request, response)) return;


    NewDepartmentForm form = Session.getForm(session, NewDepartmentForm.class);
    boolean success = request.getParameter("success") != null;
%>
<!doctype html>
<html>

<head>
<title>New Department - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />
		<main role="main" class="container">
			<h1 class="display-5 text-center page-title">New Department</h1>
			<hr>
			<form action="<%=form.getAction()%>" method="<%=form.getMethod()%>">
			<%
				HtmlHelper.writeFormRowStart(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getName(), "text", "Name", false);
					HtmlHelper.writeDivEnd(out);
					HtmlHelper.writeFormHalfColStart(out);
						HtmlHelper.writeFormControl(out, form.getLocation(), "text", "Location", false);
					HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeResetAndSubmit(out);
				HtmlHelper.writeConditionalCloseableMessage(out, !form.passedValidation(), false, form.getErrorMessage());
				HtmlHelper.writeConditionalCloseableMessage(out, success, true, "The department was added successfully");
%>
		</form>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
</body>

</html>