package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.ReportSection;

public class ReportSectionDbAccess extends DbAccess<ReportSection> {
	public ReportSectionDbAccess(DatabaseHandler db) { super(db); }

	public void insert(ReportSection reportSection) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `report_sections` (evaluation, section_id) VALUES (?, ?)");
		stmt.setString(1, reportSection.getEvaluation());
		stmt.setInt(2, reportSection.getSectionId());
		stmt.executeUpdate();
		reportSection.setId(getInsertId(stmt));
	}

	@Override
	protected ReportSection createModel(ResultSet results) throws SQLException { return ReportSection.fromResults(results); }

	@Override
	protected String getTableName() { return "report_sections"; }
}
