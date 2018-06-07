package comp3095.assignment2.forms.validation;

import java.util.ArrayList;

import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.validation.single.*;

public abstract class Validates<T extends Validates<T>> extends ErrorContainer {
	private final ArrayList<FieldValidater> _fieldValidaters;
	private final T _this;
	private boolean _passedValidation;

	protected Validates(Class<T> clazz) {
		_fieldValidaters = new ArrayList<>();
		_this = clazz.cast(this);
		_passedValidation = true;
	}

	public boolean passedValidation() { return _passedValidation; }

	@Override
	public void setErrorMessage(String errorMessage) {
		super.setErrorMessage(errorMessage);
		_passedValidation = false;
	}

	public void validate(Form form) {
		form.getFields().forEach(f -> validate(f));
	}
	public void validate(FormField field) {
		if (!field.passedValidation() || !doOnValidate(field))
			return;

		_fieldValidaters.stream().allMatch(v -> {
			v.validate(field);
			return field.passedValidation();
		});
	}
	private boolean doOnValidate(FormField field) {
		onValidate(field);
		return field.passedValidation();
	}
	protected void onValidate(FormField field) { }

	// Helpers for validation
	public final T mustBeNonEmpty() { return mustPass(NonEmptyValidater.instance); }
	public final T mustBeAlphabetic() { return mustPass(AlphabeticValidater.instance); }
	public final T mustBeDate() { return mustPass(DateValidater.instance); }
	public final T mustBeEmail() { return mustPass(EmailValidater.instance); }
	public final T mustBeInteger() { return mustPass(IntegerValidater.instance); }
	public final T mustPass(FieldValidater validater) {
		_fieldValidaters.add(validater);
		return _this;
	}

}
