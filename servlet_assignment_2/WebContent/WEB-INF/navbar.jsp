<%@page import="comp3095.assignment2.Session"%>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
	<a class="navbar-brand abs" href="/servlet_assignment_2/"> Servlet
		Assignment 2 </a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbars" aria-controls="navbars" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbars">
		<ul class="navbar-nav">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Departments
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown1">
					<a class="dropdown-item" href="/servlet_assignment_2/departments/new.jsp">
						New
					</a>
					<a class="dropdown-item" href="/servlet_assignment_2/departments/view.jsp">
						View
					</a>
				</div>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" id="dropdown2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Employees
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown2">
					<a class="dropdown-item" href="/servlet_assignment_2/employees/new.jsp">
						New
					</a>
					<a class="dropdown-item" href="/servlet_assignment_2/employees/view.jsp">
						View
					</a>
				</div>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" id="dropdown3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Groups
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown3">
					<a class="dropdown-item" href="/servlet_assignment_2/groups/new.jsp">
						New
					</a>
					<a class="dropdown-item" href="/servlet_assignment_2/groups/view.jsp">
						View
					</a>
				</div>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" id="dropdown4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Reports
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown4">
					<a class="dropdown-item" href="/servlet_assignment_2/reports/create_template.jsp">
						Create Template
					</a>
					<a class="dropdown-item" href="/servlet_assignment_2/reports/view.jsp">
						View
					</a>
					<a class="dropdown-item" href="/servlet_assignment_2/reports/pick_report_template.jsp">
						New
					</a>
				</div>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" id="dropdown5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Attendance
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown5">
					<a class="dropdown-item" href="/servlet_assignment_2/attendance/enter.jsp">
						Enter
					</a>
					<a class="dropdown-item" href="/servlet_assignment_2/attendance/view.jsp">
						View
					</a>
				</div>
			</li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<li>
				<span class="navbar-text">Welcome</span>
				<span class="navbar-text" style="font-weight: bold">
					<%=Session.getUser(session).getFirstName()%>!
				</span>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/servlet_assignment_2/servlets/logout">
					(Logout)
				</a>
			</li>
		</ul>
	</div>
</nav>