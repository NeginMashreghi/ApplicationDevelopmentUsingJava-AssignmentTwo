package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.ReportSectionTemplate;
import comp3095.assignment2.database.models.ReportTemplate;

public class ReportSectionTemplateDbAccess extends DbAccess<ReportSectionTemplate> {

	public ReportSectionTemplateDbAccess(DatabaseHandler db) { super(db); }

	public void insert(ReportSectionTemplate sectionTemplate) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `report_section_templates` (name, report_template_id, is_short) VALUES (?, ?, ?)");
		stmt.setString(1, sectionTemplate.getName());
		stmt.setInt(2, sectionTemplate.getReportTemplateId());
		stmt.setBoolean(3, sectionTemplate.isShort());
		stmt.executeUpdate();
		sectionTemplate.setId(getInsertId(stmt));
	}

	public List<ReportSectionTemplate> getSections(ReportTemplate template) throws SQLException {
		PreparedStatement stmt = db.prepare("SELECT * FROM `report_section_templates` WHERE `report_template_id` = ?");
		stmt.setInt(1, template.getId());
		ResultSet results = stmt.executeQuery();
		
		ArrayList<ReportSectionTemplate> sections = new ArrayList<>();
		while (results.next()) {
			ReportSectionTemplate sectionTemplate = ReportSectionTemplate.fromResults(results);
			sections.add(sectionTemplate);
		}
		return sections;
	}

	@Override
	protected ReportSectionTemplate createModel(ResultSet results) throws SQLException {
		return ReportSectionTemplate.fromResults(results);
	}
	@Override
	protected String getTableName() { return "report_section_templates"; }
}