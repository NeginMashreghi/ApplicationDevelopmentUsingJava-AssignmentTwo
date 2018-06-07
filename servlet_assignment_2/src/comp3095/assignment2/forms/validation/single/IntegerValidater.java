package comp3095.assignment2.forms.validation.single;

import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.validation.FormErrors;

public class IntegerValidater extends FieldValidater {
	public static final IntegerValidater instance = new IntegerValidater();
	@Override
	public void validate(FormField field) {
		if (!field.isInteger())
			field.setErrorMessage(FormErrors.INVALID_INTEGER);
	}

}
