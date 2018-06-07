package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Attendance {
	private int _id;
	private int _employeeId;
	private Date _date;
	private boolean _present;

	public Attendance setId(int id) {
		_id = id;
		return this;
	}
	public Attendance setEmployeeId(int employeeId) {
		_employeeId = employeeId;
		return this;
	}
	public Attendance setDate(Date date) {
		_date = date;
		return this;
	}
	@JSONField(deserialize=false)
	public Attendance setDateSql(java.sql.Date date) {
		long time = date.getTime();
		_date = new Date(time);
		return this;
	}
	public Attendance setPresent(boolean present) {
		_present = present;
		return this;
	}

	public int getId() { return _id; }
	public int getEmployeeId() { return _employeeId; }
	public Date getDate() { return _date; }
	@JSONField(serialize=false)
	public java.sql.Date getDateSql() {
		long time = _date.getTime();
		return new java.sql.Date(time);
	}
	public boolean isPresent() { return _present; }

	public static Attendance fromResults(ResultSet results) throws SQLException {
		return new Attendance()
			.setId(results.getInt("id"))
			.setEmployeeId(results.getInt("employee_id"))
			.setDate(results.getDate("date"))
			.setPresent(results.getBoolean("present"));
	}
}
