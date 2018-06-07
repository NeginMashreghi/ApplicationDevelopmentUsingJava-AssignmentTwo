<%@ page import="comp3095.assignment2.forms.models.ReportInputSectionModel"%>
<%@ page import="comp3095.assignment2.database.models.SectionCriteriaTemplate"%>
<%@ page import="comp3095.assignment2.database.models.ReportSectionTemplate"%>
<%@ page import="comp3095.assignment2.database.models.ReportTemplate"%>
<%@ page import="comp3095.assignment2.forms.impl.NewReportForm"%>
<%@ page import="comp3095.assignment2.forms.StringUtil"%>
<%@ page import="comp3095.assignment2.forms.FormField"%>
<%@ page import="comp3095.assignment2.HtmlHelper"%>
<%@ page import="comp3095.assignment2.Session"%>
<%@ page import="comp3095.assignment2.Redirect"%>
<%@ page import="comp3095.assignment2.database.DatabaseHandler"%>
<%@ page import="comp3095.assignment2.database.models.Employee"%>
<%@ page import="comp3095.assignment2.database.models.Group"%>
<%@ page import="comp3095.assignment2.forms.models.SectionModel"%>
<%@ page import="comp3095.assignment2.forms.models.CriteriaModel"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    DatabaseHandler db = DatabaseHandler.create(response);
    if (db == null || !Session.assertIsStarted(db, request, response)) return;
    int templateId = StringUtil.parseInt(request.getParameter("template_id"), -1);
    if (templateId == -1) {
        Redirect.newReportTemplatePage(response, false);
        return;
    }
    ReportTemplate template = db.reportTemplates.get(templateId);
    if (template == null) {
    	Redirect.newReportTemplatePage(response, false);
    	return;
    }

    NewReportForm form = Session.getForm(session, NewReportForm.class);
    boolean success = request.getParameter("success") != null;
    form.getReportTemplateId().setValue(Integer.toString(template.getId()));
%>
<!doctype html>
<html>

<head>
<title>Page Title - Servlet Assignment 2</title>
<jsp:include page="../WEB-INF/header.jsp" />
</head>

<body>
	<jsp:include page="../WEB-INF/navbar.jsp" />

	<main role="main" class="container">
		<h1 class="display-5 text-center page-title">New Report</h1>
		<hr>
		<form action="<%=form.getAction()%>" method="<%=form.getMethod()%>">
		
		<%
			HtmlHelper.writeFormControl(out, form.getReportTemplateId(), null, null, false);
			HtmlHelper.writeFormRowStart(out);
				HtmlHelper.writeFormOneThirdColStart(out);
					HtmlHelper.writeStaticFormControl(out, "text", template.getName(), "Report Template");
				HtmlHelper.writeDivEnd(out);
				HtmlHelper.writeFormOneThirdColStart(out);
					HtmlHelper.writeFormControl(out, form.get_reportTitle(), "text", "Report Title", false);
	 			HtmlHelper.writeDivEnd(out);
	 			HtmlHelper.writeFormOneThirdColStart(out);
					HtmlHelper.writeLabel(out, "datePreview", "Date Created");
					%> <input id="datePreview" class="form-control" type="date" disabled="disabled" /> <%
				HtmlHelper.writeDivEnd(out);
			HtmlHelper.writeDivEnd(out);
	
		
			HtmlHelper.writeFormRowStart(out);
					String departmentName = db.departments.get(template.getDepartmentId()).getName();
					HtmlHelper.writeLabel(out, "department", "Departments");
					%> <input class="form-control" type="text" disabled="disabled" value="<%=departmentName%>"> <%
			HtmlHelper.writeDivEnd(out);
			HtmlHelper.writeHr(out);
		
		
			HtmlHelper.writeFormRowStart(out);
				HtmlHelper.writeFormHalfColStart(out);
					HtmlHelper.writeLabel(out, "reportType", "Report Type");
					HtmlHelper.writeRadioOption(out, form.get_reportType(), "1", "Group", true);
					HtmlHelper.writeRadioOption(out, form.get_reportType(), "2", "Employee", false);
				HtmlHelper.writeDivEnd(out);
			HtmlHelper.writeDivEnd(out);

		
		
			HtmlHelper.writeFormRowStart(out);
				
					HtmlHelper.writeFormHalfColStart(out);
		
						List<Employee> Employees = db.employees.getAll();
						int lastEmployee= StringUtil.parseInt(form.get_employeeType().getValue(), -1);
		
						HtmlHelper.writeSelectStart(out, form.get_employeeType(), "Employee", true);
						for (Employee employee : Employees)
							HtmlHelper.writeOption(out, employee.getId(), employee.getFirstName(), lastEmployee == employee.getId());
						HtmlHelper.writeSelectEnd(out, form.get_employeeType()); 
		
					HtmlHelper.writeDivEnd(out);
					
					
					HtmlHelper.writeFormHalfColStart(out);
						
						List<Group> groups = db.groups.getAll();
						int lastGroup= StringUtil.parseInt(form.get_groupType().getValue(), -1);
		
						HtmlHelper.writeSelectStart(out, form.get_groupType(), "Group");
						for (Group group : groups)
							HtmlHelper.writeOption(out, group.getId(), group.getName(), lastGroup == group.getId());
						HtmlHelper.writeSelectEnd(out, form.get_groupType()); 
	
					HtmlHelper.writeDivEnd(out);
					
				
			HtmlHelper.writeDivEnd(out);
		
			/////////////////////////////////////////////////////////////////////////////////////
			
			List<ReportSectionTemplate> sections = db.reportSectionTemplates.getSections(template);
			
			int sectionCount = 0;
			for (ReportSectionTemplate section : sections) {
				ReportInputSectionModel sectionModel = form.getSectionModel(sectionCount);
				HtmlHelper.writeHr(out);
				
				HtmlHelper.writeFormRowStart(out);
				
					HtmlHelper.writeFormOneThirdColStart(out);
						HtmlHelper.writeStaticFormControl(out, "text", section.getName(), "Section " + Integer.toString(++sectionCount));
					HtmlHelper.writeDivEnd(out);
				
					HtmlHelper.writeFormOneThirdColStart(out);
						int criteriaCount = 0;
						List<SectionCriteriaTemplate> criterias = db.sectionCriteriaTemplates.getCriterias(section);
						for (SectionCriteriaTemplate criteria : criterias) {
							FormField eval = sectionModel.getEvaluation(criteriaCount);
							HtmlHelper.writeFormRowStart(out);
								HtmlHelper.writeStaticFormControl(out, "text",criteria.getName(), "Criteria " + Integer.toString(++criteriaCount));
	
								HtmlHelper.writeSelectStart(out, eval, "Maximum");
									for (int i = 0; i < criteria.getMaxEvaluation()+1; i++) {
										String val = Integer.toString(i);
										HtmlHelper.writeOption(out, i , val ,false);
									}
								HtmlHelper.writeSelectEnd(out, eval);
							HtmlHelper.writeDivEnd(out);
						}
					HtmlHelper.writeDivEnd(out);
					
					HtmlHelper.writeFormOneThirdColStart(out);
						FormField comment = sectionModel.getComment();
						HtmlHelper.writeTextArea(out, comment, 5,30 , "Comment Section");
					HtmlHelper.writeDivEnd(out);
			 	HtmlHelper.writeDivEnd(out);
			}
			
			
			/////////////////////////////////////////////////////////////////////////////////////
			
			HtmlHelper.writeHr(out);
			HtmlHelper.writeResetAndSubmit(out);
			HtmlHelper.writeConditionalCloseableMessage(out, !form.passedValidation(), false, form.getErrorMessage());
			HtmlHelper.writeConditionalCloseableMessage(out, success, true, "The report template was created successfully");
		
		%>
		
</form>
	</main>

	<jsp:include page="../WEB-INF/footer.jsp" />
	<script type="text/javascript" src="/servlet_assignment_2/assets/js/new_report.js"></script>
</body>

</html>