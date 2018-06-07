package comp3095.assignment2.forms.validation;

public abstract class ErrorContainer {
	private String _errorMessage;

	public String getErrorMessage() { return _errorMessage; }
	public void setErrorMessage(String errorMessage) { _errorMessage = errorMessage; }

	protected ErrorContainer() { }
}
