package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Employee {
	private int _id;
	private String _firstName;
	private String _lastName;
	private String _email;
	private String _role;
	private int _employeeId;
	private Date _hireDate;
	private int _department;
	private String _username;
	private String _password;
	private String _cookieSecret;
	private Date _cookieExpiration;

	public Employee() { }
	public static Employee fromResults(ResultSet results) throws SQLException {
		return new Employee()
			.setId(results.getInt("id"))
			.setFirstName(results.getString("first_name"))
			.setLastName(results.getString("last_name"))
			.setEmail(results.getString("email"))
			.setRole(results.getString("role"))
			.setEmployeeId(results.getInt("employee_id"))
			.setHireDate(results.getDate("hire_date"))
			.setDepartment(results.getInt("department_id"))
			.setUsername(results.getString("username"))
			.setPassword(results.getString("password"))
			.setCookieSecret(results.getString("cookie_secret"))
			.setCookieExpiration(results.getDate("cookie_expiration"));
	}

	public Employee setId(int id) {
		_id = id;
		return this;
	}
	public Employee setFirstName(String firstName) {
		_firstName = firstName;
		return this;
	}
	public Employee setLastName(String lastName) {
		_lastName = lastName;
		return this;
	}
	public Employee setEmail(String email) {
		_email = email;
		return this;
	}
	public Employee setRole(String role) {
		_role = role;
		return this;
	}
	
	public Employee setEmployeeId(int employeeId) {
		_employeeId = employeeId;
		return this;
	}
	public Employee setHireDate(Date hireDate) {
		_hireDate = hireDate;
		return this;
	}
	@JSONField(deserialize=false)
	public Employee setHireDate(java.sql.Date hireDate) {
		long time = hireDate.getTime();
		return setHireDate(new Date(time));
	}
	public Employee setDepartment(int department) {
		_department = department;
		return this;
	}
	public Employee setUsername(String username) {
		_username = username;
		return this;
	}
	public Employee setPassword(String password) {
		_password = password;
		return this;
	}
	public Employee setCookieSecret(String cookieSecret) {
		_cookieSecret = cookieSecret;
		return this;
	}
	public Employee setCookieExpiration(Date cookieExpiration) {
		_cookieExpiration = cookieExpiration;
		return this;
	}
	public Employee sanitizeForSession() {
		_username = null;
		_password = null;
		_cookieSecret = null;
		_cookieExpiration = null;
		return this;
	}

	public int getId() { return _id; }
	public String getFirstName() { return _firstName; }
	public String getLastName() { return _lastName; }
	@JSONField(serialize=false)
	public String getFullName() { return _firstName + ' ' + _lastName; }
	public String getEmail() { return _email; }
	public String getRole() { return _role; }
	public int getEmployeeId() { return _employeeId; }
	public Date getHireDate() { return _hireDate; }
	@JSONField(serialize=false)
	public java.sql.Date getHireDateSql() { 
		long time = _hireDate.getTime();
		return new java.sql.Date(time);
	}
	public int getDepartment() { return _department; }
	public String getUsername() { return _username; }
	public String getPassword() { return _password; }
	public String getCookieSecret() { return _cookieSecret; }
	public Date getCookieExpiration() { return _cookieExpiration; }
}
