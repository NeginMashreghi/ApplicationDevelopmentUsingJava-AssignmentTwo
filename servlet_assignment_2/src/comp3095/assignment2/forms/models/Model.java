package comp3095.assignment2.forms.models;

import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;

public abstract class Model {
	private final Form _form;
	protected Model(Form form) {
		_form = form;
	}

	protected void addFields(FormField... fields) {
		_form.addFields(fields);
	}
}
