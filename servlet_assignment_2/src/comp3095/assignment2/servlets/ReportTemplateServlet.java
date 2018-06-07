package comp3095.assignment2.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.Redirect;
import comp3095.assignment2.Session;
import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.ReportSectionTemplate;
import comp3095.assignment2.database.models.ReportTemplate;
import comp3095.assignment2.database.models.SectionCriteriaTemplate;
import comp3095.assignment2.forms.impl.NewReportTemplateForm;
import comp3095.assignment2.forms.models.CriteriaModel;
import comp3095.assignment2.forms.models.SectionModel;



@WebServlet("/servlets/report_template")
public class ReportTemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 20L;
     
    public ReportTemplateServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DatabaseHandler db = DatabaseHandler.create(response);
		if (db == null || !Session.assertIsStarted(db, request, response)) return;

		HttpSession session = request.getSession();
		NewReportTemplateForm form = new NewReportTemplateForm();

		form.readRequest(request);
		if (!form.passedValidation()) {
			form.failFixFields(session, response);
			return;
		}
		
		try {
			
			ReportTemplate reportTemplate = form.toReportTemplate();
			db.reportTemplates.insert(reportTemplate);
			
			for (SectionModel section : form.getSections()) {
				ReportSectionTemplate sectionTemplate = section.toReportSectionTemplate(reportTemplate.getId());
				db.reportSectionTemplates.insert(sectionTemplate);
				for (CriteriaModel criteria : section.getCriterias()) {
					SectionCriteriaTemplate criteriaTemplate = criteria.toSectionCriteriaTemplate(sectionTemplate.getId());
					db.sectionCriteriaTemplates.insert(criteriaTemplate);
				}
			}	
			Redirect.newReportTemplatePage(response, true);
		} catch (SQLException ex) {
			form.failDbStmtError(session, response);
		}
		
		
	}

}