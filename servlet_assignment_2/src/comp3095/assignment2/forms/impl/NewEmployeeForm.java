package comp3095.assignment2.forms.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.Redirect;
import comp3095.assignment2.database.models.Employee;
import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.StringUtil;

public class NewEmployeeForm extends Form {
	private FormField _firstName;
	private FormField _lastName;
	private FormField _employeeId;
	private FormField _email;
	private FormField _hireDate;
	private FormField _department;

	public FormField getFirstName() { return _firstName; }
	public FormField getLastName() { return _lastName; }
	public FormField getEmployeeId() { return _employeeId; }
	public FormField getEmail() { return _email; }
	public FormField getHireDate() { return _hireDate; }
	public FormField getDepartment() { return _department; }

	public NewEmployeeForm() {
		super.setAction("employees").setMethod(POST);

		this.mustBeNonEmpty();

		// Create Fields
		_firstName = new FormField("first_name");
		_lastName = new FormField("last_name");
		_employeeId = new FormField("employee_id");
		_email = new FormField("email");
		_hireDate = new FormField("hire_date");
		_department = new FormField("department");

		// Add the validaters
		_firstName.mustBeAlphabetic();
		_lastName.mustBeAlphabetic();
		_employeeId.mustBeInteger();
		_email.mustBeEmail();
		_hireDate.mustBeDate();
		_department.mustBeInteger();

		// Set the form fields
		addFields(_firstName, _lastName, _employeeId, _email, _hireDate, _department);
	}

	@Override
	protected void onFail(HttpSession session, HttpServletResponse response, String message) throws IOException {
		Redirect.newEmployeePage(response, false);
	}

	public Employee toEmployee() {
		return new Employee()
			.setFirstName(_firstName.getValue())
			.setLastName(_lastName.getValue())
			.setEmployeeId(StringUtil.toUInt(_employeeId.getValue()))
			.setEmail(_email.getValue())
			.setHireDate(StringUtil.toDate(_hireDate.getValue()))
			.setDepartment(StringUtil.toUInt(_department.getValue()));
	}
}
