package comp3095.assignment2.forms.models;

import comp3095.assignment2.database.models.ReportSectionTemplate;
import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;

public class SectionModel extends Model {
	private FormField _title;
	private CriteriaModel[] _criterias;
	
	public FormField getTitle() { return _title; }
	public CriteriaModel[] getCriterias() { return _criterias; }

	public SectionModel(Form form, String titleName, int criteriaCount, String typeNameBase, String maxEvalNameBase) {
		super(form);

		_title = new FormField(titleName);
		_criterias = new CriteriaModel[criteriaCount];
		for (int i = 0; i < _criterias.length; i++) {
			String iStr = Integer.toString(i);
			String typeName = typeNameBase + iStr;
			String maxEvalName = maxEvalNameBase + iStr;
			_criterias[i] = new CriteriaModel(form, typeName, maxEvalName);
		}

		addFields(_title);
	}
	
	public static SectionModel create(Form form, boolean isSmall, int index) {
		String iStr = Integer.toString(index);
		String titleName = "title_" + iStr + "_";
		String typeName = "type_" + iStr + "_";
		String maxEvalName = "eval_" + iStr + "_";
		int sectionCount = isSmall ? 3 : 5;
		return new SectionModel(form, titleName, sectionCount, typeName, maxEvalName);
	}
	
	private boolean isShort() { return _criterias.length == 3; }
	
	public ReportSectionTemplate toReportSectionTemplate( int reportTemplateId ) {
		return new ReportSectionTemplate()
				.setName(_title.getValue())
				.setReportTemplateId(reportTemplateId)
				.setShort(isShort())
				;
	
		
	}
}
