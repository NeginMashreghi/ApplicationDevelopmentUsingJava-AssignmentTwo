package comp3095.assignment2.forms.impl;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import comp3095.assignment2.Redirect;
import comp3095.assignment2.database.models.EmployeeReport;
import comp3095.assignment2.database.models.GroupReport;
import comp3095.assignment2.database.models.Report;
import comp3095.assignment2.database.models.ReportTemplate;
import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.StringUtil;
import comp3095.assignment2.forms.models.ReportInputSectionModel;

public class NewReportForm extends Form {
	private FormField _reportTemplateId;
	private FormField _reportTitle;
	private FormField _department;
	private FormField _reportType;
	private FormField _groupType;
	private FormField _employeeType;
	private ReportInputSectionModel[] _sectionModels; 
	
	public FormField getReportTemplateId() { return _reportTemplateId; }
	public FormField get_reportTitle() {return _reportTitle;}
	public FormField get_department() {return _department;}
	public FormField get_reportType() {return _reportType;}
	public FormField get_groupType() {return _groupType;}
	public FormField get_employeeType() {return _employeeType;}
	public ReportInputSectionModel getSectionModel(int section) {
		return _sectionModels[section];
	}
	public ReportInputSectionModel[] getSections() { return _sectionModels; }
	
	public NewReportForm(){
		super.setAction("report").setMethod(POST);
	
		
		_reportTemplateId = new FormField("template_id");
		_reportTitle = new FormField("report_title");
		_department = new FormField("department");
		_reportType = new FormField("report_type");
		_groupType = new FormField("group_type");
		_employeeType = new FormField("employee_type");
		_sectionModels = new ReportInputSectionModel[3];
		for (int i = 0; i < _sectionModels.length; i++) {
			_sectionModels[i] = new ReportInputSectionModel(this, i != 0, i);
		}
		
		_reportTemplateId.mustBeNonEmpty().mustBeInteger();
		_reportTitle.mustBeAlphabetic().mustBeNonEmpty();
		_reportType.mustBeNonEmpty();
	
		addFields(_reportTemplateId, _reportTitle, _department, _reportType, _groupType, _employeeType);
		
	}
	
	public Report toReport() {
		return new Report()
				.setTitle(_reportTitle.getValue())
				.setReportType(StringUtil.parseInt(_reportType.getValue(), -1))
				.setCreationDate(new Date())
				.setTemplateId(StringUtil.parseInt(_reportTemplateId.getValue(), -1));
	}
	public GroupReport toGroupReport(int reportId) {
		return new GroupReport()
			.setReportId(reportId)
			.setGroupId(StringUtil.parseInt(_groupType.getValue(), -1));
	}
	public EmployeeReport toEmployeeReport(int reportId) {
		return new EmployeeReport()
				.setReportId(reportId)
				.setEmployeeId(StringUtil.parseInt(_employeeType.getValue(), -1));
	}
	
	
	@Override
	protected void onFail(HttpSession session, HttpServletResponse response, String message) throws IOException {
		Redirect.newReportPage(response, false);
	}

}
