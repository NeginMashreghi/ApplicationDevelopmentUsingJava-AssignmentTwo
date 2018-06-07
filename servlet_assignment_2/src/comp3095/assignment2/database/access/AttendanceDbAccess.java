package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.Attendance;

public class AttendanceDbAccess extends DbAccess<Attendance> {
	public AttendanceDbAccess(DatabaseHandler db) { super(db); }

	public void insert(Attendance attendance) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `attendance` (employee_id, date, present) VALUES (?, ?, ?)");
		stmt.setInt(1, attendance.getEmployeeId());
		stmt.setDate(2, attendance.getDateSql());
		stmt.setBoolean(3, attendance.isPresent());
		stmt.executeUpdate();
		attendance.setId(getInsertId(stmt));
	}

	@Override
	protected Attendance createModel(ResultSet results) throws SQLException {
		return Attendance.fromResults(results);
	}
	@Override
	protected String getTableName() { return "attendance"; }

	public Attendance byEmployee(int employeeId, java.sql.Date date) throws SQLException {
		PreparedStatement stmt = db.prepare("SELECT * FROM `attendance` WHERE `employee_id` = ? AND `date` = ?");
		stmt.setInt(1, employeeId);
		stmt.setDate(2, date);
		ResultSet results = stmt.executeQuery();
		if (!results.next()) {
			return null;
		}
		return Attendance.fromResults(results);
	}
}
package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.Attendance;

public class AttendanceDbAccess extends DbAccess<Attendance> {
	public AttendanceDbAccess(DatabaseHandler db) { super(db); }

	public void insert(Attendance attendance) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `attendance` (employee_id, date, present) VALUES (?, ?, ?)");
		stmt.setInt(1, attendance.getEmployeeId());
		stmt.setDate(2, attendance.getDateSql());
		stmt.setBoolean(3, attendance.isPresent());
		stmt.executeUpdate();
		attendance.setId(getInsertId(stmt));
	}

	@Override
	protected Attendance createModel(ResultSet results) throws SQLException {
		return Attendance.fromResults(results);
	}
	@Override
	protected String getTableName() { return "attendance"; }

	public Attendance byEmployee(int employeeId, java.sql.Date date) throws SQLException {
		PreparedStatement stmt = db.prepare("SELECT * FROM `attendance` WHERE `employee_id` = ? AND `date` = ?");
		stmt.setInt(1, employeeId);
		stmt.setDate(2, date);
		ResultSet results = stmt.executeQuery();
		if (!results.next()) {
			return null;
		}
		return Attendance.fromResults(results);
	}
}
