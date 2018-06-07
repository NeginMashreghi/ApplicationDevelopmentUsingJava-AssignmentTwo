package comp3095.assignment2.forms.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.Redirect;
import comp3095.assignment2.database.models.Group;
import comp3095.assignment2.database.models.GroupMember;
import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.FormField;
import comp3095.assignment2.forms.StringUtil;
import comp3095.assignment2.forms.validation.group.NewGroupEmployeeValidater;

public class NewGroupForm extends Form {
	private FormField _department;
	private FormField _name;
	private FormField[] _employees;
	private ArrayList<FormField> _validEmployees;

	public NewGroupForm setValidEmployees(ArrayList<FormField> validEmployees) {
		_validEmployees = validEmployees;
		return this;
	}
	public ArrayList<FormField> getValidEmployees() { return _validEmployees; }
	public FormField getDepartment() { return _department; }
	public FormField getName() { return _name; }
	public FormField getEmployee(int i) { return _employees[i]; }
	public FormField[] getEmployees() { return _employees; }

	public NewGroupForm() {
		super
			.setAction("new_group")
			.setMethod(POST);

		_department = new FormField("department_id");
		_department.mustBeNonEmpty().mustBeInteger();
		_name = new FormField("name");
		_name.mustBeNonEmpty().mustBeAlphabetic();

		_employees = new FormField[6];
		for (int i = 0; i < _employees.length; i++)
			_employees[i] = new FormField("employee" + (Integer.toString(i + 1))).mustBeInteger();
		this.addGroupValidation(new NewGroupEmployeeValidater(), _employees);

		addFields(_department, _name);
		addFields(_employees);
	}
	
	@Override
	protected void onFail(HttpSession session, HttpServletResponse response, String message) throws IOException {
		Redirect.newGroupPage(response, false);
	}
	public Group toGroup() {
		return new Group().setName(_name.getValue()).setDepartmentId(StringUtil.parseInt(_department.getValue(), -1));
	}
	public ArrayList<GroupMember> getMembers(Group group) {
		ArrayList<GroupMember> members = new ArrayList<>();
		for (FormField employee : _validEmployees) {
			if (employee.isEmpty()) continue;

			int value = Integer.parseInt(employee.getValue());
			GroupMember member = new GroupMember();
			member.setEmployeeId(value)
					.setGroupId(group.getId());

			members.add(member);
		}
		return members;
	}
}
