package comp3095.assignment2.forms.validation.single;

import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.validation.FormErrors;

public class NonEmptyValidater extends FieldValidater {
	public static final NonEmptyValidater instance = new NonEmptyValidater();
	@Override
	public void validate(FormField field) {
		if (field.isEmpty())
			field.setErrorMessage(FormErrors.MISSING);
	}

}
