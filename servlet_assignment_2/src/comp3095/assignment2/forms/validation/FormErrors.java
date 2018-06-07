package comp3095.assignment2.forms.validation;

public final class FormErrors {
    public static final String MISSING = "Field may not be empty";
    public static final String ALPHABETICAL = "Field must only contain alphabetical characters";
    public static final String INVALID_EMAIL = "Field did not match the format of an email address";
    public static final String INVALID_INTEGER = "Field must be a whole number";
    public static final String INVALID_DATE = "The provided date was invalid";
	
	private FormErrors() { }
}
