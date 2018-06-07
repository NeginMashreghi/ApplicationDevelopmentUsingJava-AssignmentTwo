package comp3095.assignment2.forms.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;

public class EnterAttendanceForm extends Form {
	private FormField _employeeId;
	private FormField _date;
	private FormField _present;

	public FormField getEmployeeId() { return _employeeId; }
	public FormField getDate() { return _date; }
	public FormField getPresent() { return _present; }

	public EnterAttendanceForm() {
		_employeeId = new FormField("employee_id");
		_date = new FormField("date");
		_present = new FormField("present");

		this.mustBeNonEmpty();
		_employeeId.mustBeInteger();
		_date.mustBeDate();

		addFields(_employeeId, _date, _present);
	}

	@Override
	protected void onFail(HttpSession session, HttpServletResponse response, String message) throws IOException {
		//Redirect.attendanceForm(response, false);
	}

}
