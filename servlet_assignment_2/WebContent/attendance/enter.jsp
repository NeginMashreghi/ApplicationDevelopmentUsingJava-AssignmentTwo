<<<<<<< HEAD
<%@ page import="comp3095.assignment2.forms.StringUtil"%>
<%@ page import="comp3095.assignment2.database.models.Department"%>
<%@ page import="java.util.List"%>
<%@ page import="comp3095.assignment2.HtmlHelper"%>
<%@ page import="comp3095.assignment2.forms.impl.EnterAttendanceForm"%>
<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.database.DatabaseHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	DatabaseHandler db = DatabaseHandler.create(response);
	if (db == null || !Session.assertIsStarted(db, request, response)) return;

    EnterAttendanceForm form = Session.getForm(session, EnterAttendanceForm.class);
    boolean success = request.getParameter("success") != null;
%>
<!doctype html>
<html>

<head>
<title>Enter Attendance - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">Enter Attendance</h1>
		<hr>
		<h3>Department</h3>
		<div>
			<%
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
			<h3>Select a date</h3>
			<input type="date" id="dateInput" class="form-control" />
			<hr>
			<button class="btn btn-primary btn-block" click="enterAttendanceClicked()">Enter Attendance</button>
		</div>
		<div id="progress" class="progress" style="display: none">
			<div class="progress-bar progress-bar-striped active"
				role="progressbar" aria-valuenow="100" aria-valuemin="0"
				aria-valuemax="100" style="width: 100%">
				<span>Please wait<span class="dotdotdot"></span></span>
			</div>
		</div>
		<div id="error" class="alert alert-dark text-center" role="alert" style="display: none">
			<hr>
			The selected department has no employees
		</div>
		<div id="step2" style="display:none">
			<hr>
			<table class="table table-sm table-bordered">
				<thead>
					<tr>
						<th scope="col">First Name</th>
						<th scope="col">Employee No</th>
						<th scope="col">Present</th>
					</tr>
				</thead>
				<tbody id="attendanceBody">
				</tbody>
			</table>
		</div>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
	<script src="/servlet_assignment_2/assets/js/common.js"
		type="text/javascript"></script>
	<script src="/servlet_assignment_2/assets/js/attendance.js"
		type="text/javascript"></script>
</body>

=======
<%@ page import="comp3095.assignment2.forms.StringUtil"%>
<%@ page import="comp3095.assignment2.database.models.Department"%>
<%@ page import="java.util.List"%>
<%@ page import="comp3095.assignment2.HtmlHelper"%>
<%@ page import="comp3095.assignment2.forms.impl.EnterAttendanceForm"%>
<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.database.DatabaseHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	DatabaseHandler db = DatabaseHandler.create(response);
	if (db == null || !Session.assertIsStarted(db, request, response)) return;

    EnterAttendanceForm form = Session.getForm(session, EnterAttendanceForm.class);
    boolean success = request.getParameter("success") != null;
%>
<!doctype html>
<html>

<head>
<title>Enter Attendance - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">Enter Attendance</h1>
		<hr>
		<h3>Department</h3>
		<div id="step1">
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
			<h3>Select a date</h3>
			<input type="date" id="dateInput" class="form-control" />
			<button class="button button-primary">Test</button>
		</div>
		<div id="progress" class="progress" style="display: none">
			<div class="progress-bar progress-bar-striped active"
				role="progressbar" aria-valuenow="100" aria-valuemin="0"
				aria-valuemax="100" style="width: 100%">
				<span>Please wait<span class="dotdotdot"></span></span>
			</div>
		</div>
		<div id="error" class="alert alert-dark text-center" role="alert" style="display: none">
			<hr>
			The selected department has no employees
		</div>
		<div id="step3" style="display:none">
			<hr>
			<table class="table table-sm table-bordered">
				<thead>
					<tr>
						<th scope="col">First Name</th>
						<th scope="col">Employee No</th>
						<th scope="col">Present</th>
					</tr>
				</thead>
				<tbody id="attendanceBody">
				</tbody>
			</table>
		</div>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
	<script src="/servlet_assignment_2/assets/js/common.js"
		type="text/javascript"></script>
	<script src="/servlet_assignment_2/assets/js/attendance.js"
		type="text/javascript"></script>
</body>

>>>>>>> refs/heads/dev-arzu
</html>