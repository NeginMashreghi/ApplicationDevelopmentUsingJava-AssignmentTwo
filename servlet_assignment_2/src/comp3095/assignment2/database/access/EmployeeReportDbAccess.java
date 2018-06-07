package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.EmployeeReport;

public class EmployeeReportDbAccess extends DbAccess<EmployeeReport> {
	public EmployeeReportDbAccess(DatabaseHandler db) { super(db); }

	public void insert(EmployeeReport employeeReport) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `employee_reports` (employee_id, report_id) VALUES (?, ?)");
		stmt.setInt(1, employeeReport.getEmployeeId());
		stmt.setInt(2, employeeReport.getReportId());
		stmt.executeUpdate();
		employeeReport.setId(getInsertId(stmt));
	}

	@Override
	protected EmployeeReport createModel(ResultSet results) throws SQLException { return EmployeeReport.fromResults(results); }
	@Override
	protected String getTableName() { return "employee_reports"; }
}
