package comp3095.assignment2.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.Redirect;
import comp3095.assignment2.Session;
import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.EmployeeReport;
import comp3095.assignment2.database.models.GroupReport;
import comp3095.assignment2.database.models.Report;
import comp3095.assignment2.database.models.ReportSection;
import comp3095.assignment2.database.models.ReportSectionTemplate;
import comp3095.assignment2.database.models.ReportTemplate;
import comp3095.assignment2.database.models.SectionCriteria;
import comp3095.assignment2.database.models.SectionCriteriaTemplate;
import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.StringUtil;
import comp3095.assignment2.forms.impl.NewReportForm;
import comp3095.assignment2.forms.impl.NewReportTemplateForm;
import comp3095.assignment2.forms.models.CriteriaModel;
import comp3095.assignment2.forms.models.ReportInputSectionModel;
import comp3095.assignment2.forms.models.SectionModel;

@WebServlet("/servlets/report")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 30L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseHandler db = DatabaseHandler.create(response);
		if (db == null || !Session.assertIsStarted(db, request, response)) return;

		HttpSession session = request.getSession();
		NewReportForm form = new NewReportForm();

		form.readRequest(request);
		if (!form.passedValidation()) {
			form.failFixFields(session, response);
			return;
		}
		
		try {
			Report report = form.toReport();
			db.reports.insert(report);

			if (report.getReportType() == 1) {
				// Group
				GroupReport gReport = form.toGroupReport(report.getId());
				db.groupReports.insert(gReport);
			} else {
				// Employee
				EmployeeReport eReport = form.toEmployeeReport(report.getId());
				db.employeeReports.insert(eReport);
			}

			ReportTemplate template = db.reportTemplates.get(report.getTemplateId());
			List<ReportSectionTemplate> sectionTemplates = db.reportSectionTemplates.getSections(template);

			for (int i = 0; i < sectionTemplates.size(); i++) {
				ReportSectionTemplate sectionTemplate = sectionTemplates.get(i);
				List<SectionCriteriaTemplate> criteriaTemplates = db.sectionCriteriaTemplates.getCriterias(sectionTemplate);
				ReportInputSectionModel sectionModel = form.getSectionModel(i);
				ReportSection reportSection = sectionModel.toReportSection(sectionTemplate.getId());
				db.reportSections.insert(reportSection);

				for (int i2 = 0; i2 < criteriaTemplates.size(); i2++) {
					SectionCriteriaTemplate criteriaTemplate = criteriaTemplates.get(i2);
					FormField evaluationField = sectionModel.getEvaluation(i2);
					int evaluation = StringUtil.parseInt(evaluationField.getValue(), -1);

					SectionCriteria criteria = new SectionCriteria();
					criteria.setEvaluation(evaluation)
							.setSectionId(criteria.getId());
					db.sectionCriteria.insert(criteria);
				}
			}
			Redirect.newReportPage(response, true);
		} catch (SQLException ex) {
			form.failDbStmtError(session, response);
		}
	}

}
