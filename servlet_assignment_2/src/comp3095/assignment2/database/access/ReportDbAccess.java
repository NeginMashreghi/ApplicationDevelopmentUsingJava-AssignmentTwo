package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.Report;

public class ReportDbAccess extends DbAccess<Report> {

	public ReportDbAccess(DatabaseHandler db) { super(db); }

	public void insert(Report report) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `reports` (title, template_id, report_type, creation_date) VALUES (?, ?, ?, ?)");
		stmt.setString(1, report.getTitle());
		stmt.setInt(2, report.getTemplateId());
		stmt.setInt(3, report.getReportType());
		stmt.setDate(4, report.getCreationDateSql());
		stmt.executeUpdate();
		report.setId(getInsertId(stmt));
	}

	@Override
	protected Report createModel(ResultSet results) throws SQLException {
		return Report.fromResults(results);
	}

	@Override
	protected String getTableName() { return "reports"; }

}
