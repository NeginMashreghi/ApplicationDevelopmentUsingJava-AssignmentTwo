package comp3095.assignment2.forms.validation.single;

import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.validation.FormErrors;

public class AlphabeticValidater extends FieldValidater {
	public static final AlphabeticValidater instance = new AlphabeticValidater();
	@Override
	public void validate(FormField field) {
		if (!field.isAlphabetic())
			field.setErrorMessage(FormErrors.ALPHABETICAL);
	}

}
