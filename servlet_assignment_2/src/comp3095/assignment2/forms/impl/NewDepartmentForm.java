package comp3095.assignment2.forms.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.Redirect;
import comp3095.assignment2.database.models.Department;
import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;

public class NewDepartmentForm extends Form {
	private FormField _name;
	private FormField _location;

	public FormField getName() { return _name; }
	public FormField getLocation() { return _location; }

	public NewDepartmentForm() {
		super.setAction("departments")
			.setMethod(Form.POST);

		this.mustBeNonEmpty().mustBeAlphabetic();

		_name = new FormField("name");
		_location = new FormField("location");

		addFields(_name, _location);
	}
	public Department toDepartment() {
		return new Department()
				.setName(_name.getValue())
				.setLocation(_location.getValue());
	}
	@Override
	protected void onFail(HttpSession session, HttpServletResponse response, String message) throws IOException {
		Redirect.newDepartmentPage(response, false);
	}
	
}
