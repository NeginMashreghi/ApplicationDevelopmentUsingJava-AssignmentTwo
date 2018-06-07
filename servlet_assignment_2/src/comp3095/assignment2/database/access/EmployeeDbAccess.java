package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.Employee;

public class EmployeeDbAccess extends DbAccess<Employee> {
	public EmployeeDbAccess(DatabaseHandler db) {
		super(db);
	}

	public Employee get(int id, boolean sanitized) throws SQLException {
		Employee employee = get(id);
		if (employee != null && sanitized)
			employee.sanitizeForSession();
		return employee;
	}

	public void insert(Employee employee) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `employees` "
				+ "(first_name, last_name, email, role, employee_id, hire_date, department_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
		stmt.setString(1, employee.getFirstName());
		stmt.setString(2, employee.getLastName());
		stmt.setString(3, employee.getEmail());
		stmt.setString(4, "employee");
		stmt.setInt(5, employee.getEmployeeId());
		stmt.setDate(6, employee.getHireDateSql());
		stmt.setInt(7, employee.getDepartment());
		stmt.executeUpdate();
		ResultSet results = stmt.getGeneratedKeys();
		if (!results.next()) return;
		employee.setId(results.getInt(1));
	}

	public Employee byUsername(String username) throws SQLException {
		PreparedStatement stmt = db.prepare("SELECT * FROM `employees` WHERE role = 'admin' AND username = ?");
		stmt.setString(1, username);
		ResultSet results = stmt.executeQuery();
		if (!results.next()) {
			return null;
		}
		return createModel(results);
	}
	public List<Employee> byDepartment(int departmentId) throws SQLException {
		ArrayList<Employee> employees = new ArrayList<>();
		PreparedStatement stmt = db.prepare("SELECT * FROM `employees` WHERE `department_id` = ?");
		stmt.setInt(1, departmentId);
		ResultSet results = stmt.executeQuery();
		while (results.next()) {
			Employee employee = createModel(results);
			employee.sanitizeForSession();
			employees.add(employee);
		}
		return employees;
	}

	public Employee byCookieSecret(String cookieSecret) throws SQLException {
		PreparedStatement stmt = db.prepare("SELECT * FROM `employees` WHERE `cookie_secret` = ? LIMIT 1");
		stmt.setString(1, cookieSecret);
		ResultSet results = stmt.executeQuery();
		if (!results.next()) return null;
		return createModel(results);
	}
	public void changeCookieSecret(Employee employee, String cookieSecret, Date time) throws SQLException {
		PreparedStatement stmt = db.prepare("UPDATE `employees` SET cookie_secret=?, cookie_expiration=? WHERE id = ?");
		stmt.setString(1, cookieSecret);
		stmt.setTimestamp(2, new java.sql.Timestamp(time.getTime()));
		stmt.setInt(3, employee.getId());
		stmt.executeUpdate();
	}

	@Override
	protected Employee createModel(ResultSet results) throws SQLException { return Employee.fromResults(results); }
	@Override
	protected String getTableName() { return "employees"; }
}
