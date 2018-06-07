package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.Department;

public class DepartmentDbAccess extends DbAccess<Department> {
	public DepartmentDbAccess(DatabaseHandler db) { super(db); }

	public void insert(Department department) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `departments` (`name`, `location`) VALUES (?, ?)");
		stmt.setString(1, department.getName());
		stmt.setString(2, department.getLocation());
		stmt.executeUpdate();
		ResultSet results = stmt.getGeneratedKeys();
		if (!results.next()) return;
		department.setId(results.getInt(1));
	}

	@Override
	protected Department createModel(ResultSet results) throws SQLException {
		return Department.fromResults(results);
	}
	@Override
	protected String getTableName() { return "departments"; }

}