package comp3095.assignment2.forms.validation.single;

import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.validation.FormErrors;

public class DateValidater extends FieldValidater {
	public static final DateValidater instance = new DateValidater();
	@Override
	public void validate(FormField field) {
		if (!field.isDate())
			field.setErrorMessage(FormErrors.INVALID_DATE);
	}
}
