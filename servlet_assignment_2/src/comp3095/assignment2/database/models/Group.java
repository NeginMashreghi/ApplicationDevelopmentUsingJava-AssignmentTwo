package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Group {
	private int _id;
	private int _departmentId;
	private String _name;

	public int getId() { return _id; }
	public int getDepartmentId() { return _departmentId; }
	public String getName() { return _name; }
	public Group setId(int id) {
		_id = id;
		return this;
	}
	public Group setDepartmentId(int departmentId) {
		_departmentId = departmentId;
		return this;
	}
	public Group setName(String name) {
		_name = name;
		return this;
	}
	public static Group fromResults(ResultSet results) throws SQLException {
		return new Group()
			.setId(results.getInt("id"))
			.setDepartmentId(results.getInt("department_id"))
			.setName(results.getString("name"));
	}
}
