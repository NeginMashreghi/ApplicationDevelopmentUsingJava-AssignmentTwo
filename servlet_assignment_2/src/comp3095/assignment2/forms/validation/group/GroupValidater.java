package comp3095.assignment2.forms.validation.group;

import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;

public abstract class GroupValidater {
	private Form _form;
	private FormField[] _fields;
	public final void setFields(Form form, FormField[] fields) {
		_form = form;
		_fields = fields;
	}
	public final void validate() {
		if (_fields != null)
			onValidate(_form, _fields);
	}
	protected abstract void onValidate(Form form, FormField[] fields);
}
