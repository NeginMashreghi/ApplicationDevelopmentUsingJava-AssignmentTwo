package comp3095.assignment2.forms.validation.single;

import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.validation.FormErrors;

public class EmailValidater extends FieldValidater {
	public static final EmailValidater instance = new EmailValidater();
	@Override
	public void validate(FormField field) {
		if (!field.isEmail())
			field.setErrorMessage(FormErrors.INVALID_EMAIL);
	}

}
