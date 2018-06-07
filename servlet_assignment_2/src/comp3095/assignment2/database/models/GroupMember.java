package comp3095.assignment2.database.models;

public class GroupMember {
	private int _groupId;
	private int _employeeId;

	public int getGroupId() { return _groupId; }
	public int getEmployeeId() { return _employeeId; }
	public GroupMember setGroupId(int groupId) {
		_groupId = groupId;
		return this;
	}
	public GroupMember setEmployeeId(int employeeId) {
		_employeeId = employeeId;
		return this;
	}
}
