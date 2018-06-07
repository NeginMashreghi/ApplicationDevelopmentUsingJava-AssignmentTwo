package comp3095.assignment2.forms.validation.group;

import java.util.ArrayList;

import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.impl.NewGroupForm;

public class NewGroupEmployeeValidater extends GroupValidater {
	private static final String NO_EMPLOYEES_ERROR = "Please select an employee to assign to the group";
	private static final String NO_MATCHING_EMPLOYEES_ERROR = "You can not assign an employee to a group multiple times";

	@Override
	protected void onValidate(Form form, FormField[] fields) {
		ArrayList<FormField> validFields = getValidFields(fields);
		((NewGroupForm)form).setValidEmployees(validFields);
		if (validFields.size() == 0) {
			form.setErrorMessage(NO_EMPLOYEES_ERROR);
			return;
		}
		checkMatchingContent(validFields);
	}
	private ArrayList<FormField> getValidFields(FormField[] fields) {
		ArrayList<FormField> validFields = new ArrayList<>();
		for (FormField field : fields) {
			if (field.passedValidation() && 
					field.isInteger() &&
					!field.getValue().contentEquals("-1"))
				validFields.add(field);
		}
		return validFields;
	}
	private void checkMatchingContent(ArrayList<FormField> validFields) {
		if (validFields.size() == 1) return;
		for (int i1 = 0; i1 < validFields.size(); i1++) {
			for (int i2 = 0; i2 < validFields.size(); i2++) {
				if (i1 == i2) continue;
				compare(validFields, i1, i2);
			}
		}
	}
	private void compare(ArrayList<FormField> validFields, int index1, int index2) {
		FormField field1 = validFields.get(index1);
		FormField field2 = validFields.get(index2);
		if (field1.getValue().contentEquals(field2.getValue())) {
			setMatchingError(field1, field2);
		}
	}
	private void setMatchingError(FormField field1, FormField field2) {
		field1.setErrorMessage(NO_MATCHING_EMPLOYEES_ERROR);
		field2.setErrorMessage(NO_MATCHING_EMPLOYEES_ERROR);
	}
}
