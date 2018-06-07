package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.Group;
import comp3095.assignment2.database.models.GroupMember;

public class GroupDbAccess extends DbAccess<Group> {
	public GroupDbAccess(DatabaseHandler db) {
		super(db);
	}

	public void insert(Group group) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `groups` (name, department_id) VALUES (?, ?)");
		stmt.setString(1, group.getName());
		stmt.setInt(2, group.getDepartmentId());
		stmt.executeUpdate();
		group.setId(getInsertId(stmt));
	}
	public void insertMember(GroupMember member) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `group_members` (employee_id, group_id) VALUES (?, ?)");
		stmt.setInt(1, member.getEmployeeId());
		stmt.setInt(2, member.getGroupId());
		stmt.executeUpdate();
	}

	public List<Group> byDepartment(int departmentId) throws SQLException {
		PreparedStatement stmt = db.prepare("SELECT * FROM `groups` WHERE `department_id` = ?");
		stmt.setInt(1, departmentId);
		ResultSet results = stmt.executeQuery();
		ArrayList<Group> groups = new ArrayList<>();
		while (results.next()) {
			Group group = Group.fromResults(results);
			groups.add(group);
		}
		return groups;
	}

	@Override
	protected Group createModel(ResultSet results) throws SQLException { return Group.fromResults(results); }
	@Override
	protected String getTableName() { return "groups"; }

}
