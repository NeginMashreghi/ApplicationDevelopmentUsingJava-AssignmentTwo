package comp3095.assignment2.forms.impl;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.Redirect;
import comp3095.assignment2.database.models.ReportTemplate;
import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.StringUtil;
import comp3095.assignment2.forms.models.SectionModel;


public class NewReportTemplateForm extends Form {

	private FormField _name;
	
	private FormField _department;
	private SectionModel[] _sections;
	
	public FormField getName() { return _name; }
	
	public FormField getDepartment() { return _department; }
	public SectionModel[] getSections() { return _sections; }
	
	
	public NewReportTemplateForm() {
		
		super.setAction("reportTemplate").setMethod(POST);
		
		this.mustBeNonEmpty();

		// Create Fields and Add the validates
		_name = new FormField("name");
		
		_department = new FormField("department_id");

		_name.mustBeAlphabetic();
		_department.mustBeInteger();
		
		_sections = new SectionModel[3];
		
		for (int i = 0; i < _sections.length; i++) {
			String iStr = Integer.toString(i);
			String titleName = "title_" + iStr + "_";
			String typeName = "type_"+ iStr + "_";
			String maxEvalName = "eval_" + iStr + "_";
			int sectionCount = i == 0 ? 5 : 3;
			_sections[i] = new SectionModel(this, titleName, sectionCount, typeName, maxEvalName);
		
		}
	
		addFields(_name, _department);
	}
	
	
	public ReportTemplate toReportTemplate() {
		return new ReportTemplate()
				.setName(_name.getValue())
				.setDepartmentId(StringUtil.parseInt(_department.getValue(), -1))
				.setCreated(new Date());
	}
	
	@Override
	protected void onFail(HttpSession session, HttpServletResponse response, String message) throws IOException {
		Redirect.newReportTemplatePage(response, false);
		
	}

}
