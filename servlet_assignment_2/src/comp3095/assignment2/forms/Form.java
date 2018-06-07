package comp3095.assignment2.forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.Session;
import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.forms.validation.Validates;
import comp3095.assignment2.forms.validation.group.GroupValidater;

public abstract class Form extends Validates<Form> {
	private static final String FIX_FIELDS_ERROR = "Fix the marked fields and try again";
	private static final String ACTION_URL_BASE = "/servlet_assignment_2/servlets/";
	public static final String POST = "POST";
	public static final String PUT = "PUT";
	public static final String DELETE = "DELETE";

	private ArrayList<GroupValidater> _groupValidaters;
	private ArrayList<FormField> _fields;
	private String _action;
	private String _method;
	private boolean _isNew;
	
	public String getAction() { return _action; }
	public String getMethod() { return _method; }
	public boolean isNew() { return _isNew; }

	public final Form setAction(String action) {
		_action = ACTION_URL_BASE + action; return this;
	}
	public final Form setMethod(String method) {
		_method = method; return this;
	}
	public final Form setIsNew(boolean isNew) {
		_isNew = isNew; return this;
	}

	public final List<FormField> getFields() { return _fields; }
	public final void addFields(FormField... fields) {
		for (FormField field : fields)
			_fields.add(field);
	}

	public final void addGroupValidation(GroupValidater validater, FormField[] fields) {
		validater.setFields(this, fields);
		_groupValidaters.add(validater);
	}

	protected Form() {
		super(Form.class);
		_groupValidaters = new ArrayList<>();
		_fields = new ArrayList<>();
		_isNew = true;
	}
	@Override
	public boolean passedValidation() {
		// If the form failed validation just return false;
		if (!super.passedValidation()) return false;
		// Otherwise make sure all fields have passed validation
		return _fields.stream().allMatch(f -> f.passedValidation());
	}
	public final void readRequest(HttpServletRequest request) {
		// Read the values into the form
		for (FormField field : _fields)
			field.readRequest(request);
		runValidations();
	}
	private void runValidations() {
		// Run validations for all fields
		validate(this);
		// Run individual validations for all fields
		_fields.stream().forEach(f -> f.validate());
		// Run group validations
		_groupValidaters.stream().forEach(v -> v.validate());
	}


	public void fail(HttpSession session, HttpServletResponse response, String message) throws IOException {
		Session.setFormError(session, this, message);
		setIsNew(false);
		onFail(session, response, message);
	}
	public void failFixFields(HttpSession session, HttpServletResponse response) throws IOException {
		fail(session, response, FIX_FIELDS_ERROR);
	}
	public void failDbStmtError(HttpSession session, HttpServletResponse response) throws IOException {
		fail(session, response, DatabaseHandler.STMT_ERROR_MSG);
	}
	protected abstract void onFail(HttpSession session, HttpServletResponse response, String message) throws IOException;
}
