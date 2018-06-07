<%@ page import="comp3095.assignment2.HtmlHelper"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.database.DatabaseHandler"%>
<%@ page import="comp3095.assignment2.forms.FormField"%>
<%@ page import="comp3095.assignment2.forms.impl.LoginForm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    DatabaseHandler db = DatabaseHandler.create(response, false);
	// Allow the login page to show if the database connection fails to open
	// This lets us display a message to the user indicating the service is currently down
    if (db != null && Session.isStarted(db, request, response)) {
        Redirect.homePage(response);
        return;
    }

    LoginForm form = Session.getForm(session, LoginForm.class);
    boolean unauthorizedAttempt = request.getParameter("unauthorized") != null;
%>
<!doctype html>
<html>

<head>
<title>Login - Servlet Assignment 2</title>
<jsp:include page="WEB-INF/header.jsp" />
</head>

<body>
	<main role="main" class="container">
		<h1 class="display-3 text-center">LOGIN</h1>
		<form action="<%=form.getAction()%>" method="<%=form.getMethod()%>" class="col-6 offset-md-3">
		<%
			HtmlHelper.writeFormRowStart(out);
				HtmlHelper.writeFormControl(out, form.getUsername(), "text", "Username", false);
			HtmlHelper.writeDivEnd(out);
			HtmlHelper.writeFormRowStart(out);
				HtmlHelper.writeFormControl(out, form.getPassword(), "password", "Password", false);
			HtmlHelper.writeDivEnd(out);
		%>
			<div class="form-group row">
				<label class="col-3"> <input type="checkbox"
					class="form-check-input" checked name="<%=form.getRememberMe().getName()%>">
					Remember Me
				</label>
			</div>
		<%
			HtmlHelper.writeFormRowStart(out);
				HtmlHelper.writeButton(out, "submit", "btn-primary btn-block", "Login");
			HtmlHelper.writeDivEnd(out);
			HtmlHelper.writeConditionalCloseableMessage(out, db == null, false, "There was an error connecting to the database.<br>Please try again later.");
			HtmlHelper.writeConditionalCloseableMessage(out, !form.passedValidation(), false, form.getErrorMessage());
			HtmlHelper.writeConditionalCloseableMessage(out, unauthorizedAttempt, false, "The page you attempted to access requires authentication");
		%>

	</form>
	</main>
	<jsp:include page="WEB-INF/footer.jsp" />
</body>

</html>