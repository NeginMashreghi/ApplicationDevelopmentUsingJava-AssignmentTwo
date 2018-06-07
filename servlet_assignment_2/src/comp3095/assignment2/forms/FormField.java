package comp3095.assignment2.forms;

import javax.servlet.http.HttpServletRequest;

import comp3095.assignment2.forms.validation.Validates;

public class FormField extends Validates<FormField> {
	private String _name;
	private String _value;

	public FormField(String name) {
		super(FormField.class);
		_name = name;
	}

	public final void setValue(String value) {
		_value = value;
	}

	public final String getName() { return _name; }
	public final String getValue() { return _value; }

	public final boolean isEmpty() { return StringUtil.isNullOrEmpty(_value); }
	public final boolean isAlphabetic() { return StringUtil.isAlphabetic(_value); }
	public final boolean isInteger() { return StringUtil.isNumber(_value); }
	public final boolean isEmail() { return StringUtil.isEmail(_value); }
	public final boolean isDate() { return StringUtil.isDate(_value); }

	public final void readRequest(HttpServletRequest request) {
		_value = (String)request.getParameter(_name);
	}
	public final void validate() { validate(this); }
}
