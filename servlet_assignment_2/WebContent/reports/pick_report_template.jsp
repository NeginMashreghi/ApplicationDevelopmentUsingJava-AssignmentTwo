<%@ page import="comp3095.assignment2.database.models.ReportTemplate"%>
<%@ page import="java.util.List"%>
<%@ page import="comp3095.assignment2.HtmlHelper"%>
<%@ page import="comp3095.assignment2.forms.StringUtil"%>
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
<title>New Report - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">Pick a Report Template</h1>
		<hr>
		<%
			HtmlHelper.writeFormRowStart(out);
		%>
		<select class="form-control" id="departmentSelect">
			<option value="-1">Select a department</option>
			<%
				List<ReportTemplate> templates = db.reportTemplates.getAll();
				for (ReportTemplate template : templates) {
					HtmlHelper.writeOption(out, template.getId(), template.getName(), false);
				}
			%>
		</select>
		<button class="btn btn-primary" onclick="doTheDo()">Create Report</button>
		<%
			HtmlHelper.writeDivEnd(out);
		%>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
	<script type="text/javascript" src="/servlet_assignment_2/assets/js/pick_report.js">
	</script>
</body>

</html>
