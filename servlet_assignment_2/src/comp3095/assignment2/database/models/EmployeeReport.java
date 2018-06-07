package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeReport {
	private int _id;
	private int _employeeId;
	private int _reportId;

	public int getId() { return _id; }
	public int getEmployeeId() { return _employeeId; }
	public int getReportId() { return _reportId; }

	public EmployeeReport setId(int id) {
		_id = id;
		return this;
	}
	public EmployeeReport setEmployeeId(int employeeId) {
		_employeeId = employeeId;
		return this;
	}
	public EmployeeReport setReportId(int reportId) {
		_reportId = reportId;
		return this;
	}

	public static EmployeeReport fromResults(ResultSet results) throws SQLException {
		return new EmployeeReport()
			.setId(results.getInt("id"))
			.setEmployeeId(results.getInt("employee_id"))
			.setReportId(results.getInt("report_id"));
	}
}
