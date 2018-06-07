package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.GroupReport;

public class GroupReportDbAccess extends DbAccess<GroupReport> {
	public GroupReportDbAccess(DatabaseHandler db) { super(db); }

	public void insert(GroupReport groupReport) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `group_reports` (group_id, report_id) VALUES (?, ?)");
		stmt.setInt(1, groupReport.getGroupId());
		stmt.setInt(2, groupReport.getReportId());
		stmt.executeUpdate();
		groupReport.setId(getInsertId(stmt));
	}

	@Override
	protected GroupReport createModel(ResultSet results) throws SQLException { return GroupReport.fromResults(results); }
	@Override
	protected String getTableName() { return "group_reports"; }
}
