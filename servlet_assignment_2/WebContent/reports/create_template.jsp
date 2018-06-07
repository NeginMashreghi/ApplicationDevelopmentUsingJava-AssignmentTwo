<<<<<<< HEAD

<%@page import="comp3095.assignment2.forms.models.CriteriaModel"%>
<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.database.DatabaseHandler"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.forms.impl.NewReportTemplateForm"%>
<%@ page import="comp3095.assignment2.forms.models.SectionModel"%>
<%@ page import="comp3095.assignment2.HtmlHelper"%>
<%@ page import="comp3095.assignment2.database.models.Department"%>
<%@ page import="comp3095.assignment2.forms.StringUtil"%>

<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	DatabaseHandler db = DatabaseHandler.create(response);
	if (db == null || !Session.assertIsStarted(db, request, response)) return;
    
    NewReportTemplateForm form = Session.getForm(session, NewReportTemplateForm.class);
    boolean success = request.getParameter("success") != null;
    
%>
<!doctype html>
<html> 

<head>
<title>Create Template Report - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">Create Template Report</h1>
		<hr>
		
		<form action="<%=form.getAction()%>" method="<%=form.getMethod()%>">
		<%
		
			HtmlHelper.writeFormRowStart(out);
			
				HtmlHelper.writeFormHalfColStart(out);
					HtmlHelper.writeFormControl(out, form.getName(), "text", "Report Template", false);
				HtmlHelper.writeDivEnd(out);
			
				HtmlHelper.writeFormHalfColStart(out);
					HtmlHelper.writeLabel(out, "datePreview", "Date Created");
					%> <input id="datePreview" class="form-control" type="date" disabled="disabled" /> <%
				HtmlHelper.writeDivEnd(out);
			
			HtmlHelper.writeDivEnd(out);

			HtmlHelper.writeFormRowStart(out);
			
				HtmlHelper.writeFormHalfColStart(out);
	
					List<Department> departments = db.departments.getAll();
					int lastDepartment = StringUtil.parseInt(form.getDepartment().getValue(), -1);
	
					HtmlHelper.writeSelectStart(out, form.getDepartment(), "Department");
					for (Department department : departments)
						HtmlHelper.writeOption(out, department.getId(), department.getName(), lastDepartment == department.getId());
					HtmlHelper.writeSelectEnd(out, form.getDepartment());
	
				HtmlHelper.writeDivEnd(out);
				
			
			HtmlHelper.writeDivEnd(out);

			// 3 section part
			SectionModel[] sections = form.getSections();
			int sectionCount = 0;
			for (SectionModel section : sections) {
				
				HtmlHelper.writeHr(out);
				
				HtmlHelper.writeFormRowStart(out);
				
					HtmlHelper.writeFormOneThirdColStart(out);
						HtmlHelper.writeFormControl(out, section.getTitle(), "text", "Section " + Integer.toString(++sectionCount), false);
					HtmlHelper.writeDivEnd(out);
					
					HtmlHelper.writeFormOneThirdColStart(out);
					
						int criteriaCount = 0;
						for (CriteriaModel criteria : section.getCriterias()) {
							
							HtmlHelper.writeFormRowStart(out);
							
								HtmlHelper.writeFormTwoThirdColStart(out);
									HtmlHelper.writeFormControl(out, criteria.getType(), "text", "Criteria " + Integer.toString(++criteriaCount), false);
								HtmlHelper.writeDivEnd(out);
								
								HtmlHelper.writeFormTwoThirdColStart(out);
									HtmlHelper.writeSelectStart(out, criteria.getMaxEvaluation(), "Maximum");
									for (int i = 0 ; i < 6 ; i++){
										
										String val = Integer.toString(i);
										HtmlHelper.writeOption(out, i , val ,false);
									}
								
									HtmlHelper.writeSelectEnd(out, criteria.getMaxEvaluation());
								HtmlHelper.writeDivEnd(out);		
							HtmlHelper.writeDivEnd(out);
						}
					HtmlHelper.writeDivEnd(out);
			 	HtmlHelper.writeDivEnd(out);
			}
			
		
			HtmlHelper.writeHr(out);
			HtmlHelper.writeResetAndSubmit(out);
			HtmlHelper.writeConditionalCloseableMessage(out, !form.passedValidation(), false, form.getErrorMessage());
			HtmlHelper.writeConditionalCloseableMessage(out, success, true, "The report template was created successfully");
		
		
		%>
		</form>

	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
	<script>
		document.getElementById("datePreview").valueAsDate = new Date();
	</script>
</body>

</html>