package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.ReportTemplate;

public class ReportTemplateDbAccess extends DbAccess<ReportTemplate> {
	public ReportTemplateDbAccess(DatabaseHandler db) { super(db); }

	public void insert(ReportTemplate template) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `report_templates` (name, department_id, date_created) VALUES (?, ?, ?)");
		stmt.setString(1, template.getName());
		stmt.setInt(2, template.getDepartmentId());
		stmt.setDate(3, template.getCreatedSql());
		stmt.executeUpdate();
		template.setId(getInsertId(stmt));
	}

	@Override
	protected ReportTemplate createModel(ResultSet results) throws SQLException {
		return ReportTemplate.fromResults(results);
	}

	@Override
	protected String getTableName() { return "report_templates"; }
}
