package comp3095.assignment2.forms.models;

import comp3095.assignment2.database.models.ReportSection;
import comp3095.assignment2.database.models.ReportSectionTemplate;
import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;

public class ReportInputSectionModel extends Model {
	private FormField[] _evaluations;
	private FormField _comment;
	
	public FormField[] getEvaluations() { return _evaluations; }
	public FormField getEvaluation(int eval) { return _evaluations[eval]; }
	public FormField getComment() { return _comment; }

	public ReportInputSectionModel(Form form, boolean isSmall, int index) {
		super(form);

		String indexStr = Integer.toString(index);

		_comment = new FormField("comment_" + Integer.toString(index));
		_comment.mustBeNonEmpty();
		_evaluations = new FormField[isSmall ? 3 : 5];
		for (int i = 0; i < _evaluations.length; i++) {
			_evaluations[i] = new FormField("eval_" + indexStr + "_" + Integer.toString(i));
		}
	}
	public ReportSection toReportSection(int sectionId) {
		return new ReportSection()
			.setSectionId(sectionId)
			.setEvaluation(_comment.getValue());
	}
}
