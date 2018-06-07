package comp3095.assignment2.forms.models;

import comp3095.assignment2.database.models.SectionCriteriaTemplate;
import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.StringUtil;

public class CriteriaModel extends Model {
	private FormField _type;
	private FormField _maxEvaluation;
	
	public FormField getType() { return _type; }
	public FormField getMaxEvaluation() { return _maxEvaluation; }
	
	public CriteriaModel(Form form, String typeName, String maxEvalName) {
		super(form);
		
		_type = new FormField(typeName);
		_maxEvaluation = new FormField(maxEvalName);
		
		addFields(_type, _maxEvaluation);
	}
	
	public SectionCriteriaTemplate toSectionCriteriaTemplate( int reportSectionId )
	{
		return new SectionCriteriaTemplate()
				.setName(_type.getValue())
				.setMaxEvaluation(StringUtil.parseInt(_maxEvaluation.getValue(), -1))
				.setReportSectionId(reportSectionId)
				;
		
	}
}
