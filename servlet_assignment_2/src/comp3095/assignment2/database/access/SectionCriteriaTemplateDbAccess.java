<<<<<<< HEAD
package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.ReportSectionTemplate;
import comp3095.assignment2.database.models.SectionCriteriaTemplate;

public class SectionCriteriaTemplateDbAccess extends DbAccess<SectionCriteriaTemplate> {

	public SectionCriteriaTemplateDbAccess(DatabaseHandler db) { super(db); }

	public void insert(SectionCriteriaTemplate criteriaTemplate) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `section_criteria_templates` (name, max_evaluation, report_section_id) VALUES (?, ?, ?)");
		stmt.setString(1, criteriaTemplate.getName());
		stmt.setInt(2, criteriaTemplate.getMaxEvaluation());
		stmt.setInt(3, criteriaTemplate.getReportSectionId());
		stmt.executeUpdate();
		criteriaTemplate.setId(getInsertId(stmt));
	}
	public List<SectionCriteriaTemplate> getCriterias(ReportSectionTemplate sectionTemplate) throws SQLException {
		PreparedStatement stmt = db.prepare("SELECT * FROM `section_criteria_templates` WHERE `report_section_id` = ?");
		stmt.setInt(1, sectionTemplate.getId());
		ResultSet results = stmt.executeQuery();
		
		ArrayList<SectionCriteriaTemplate> criterias = new ArrayList<>();
		while (results.next()) {
			SectionCriteriaTemplate criteriaTemplate = SectionCriteriaTemplate.fromResults(results);
			criterias.add(criteriaTemplate);
		}
		return criterias;
	}

	@Override
	protected SectionCriteriaTemplate createModel(ResultSet results) throws SQLException {
		return SectionCriteriaTemplate.fromResults(results);
	}

	@Override
	protected String getTableName() { return "section_criteria_templates"; }

}
=======
package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.ReportSectionTemplate;
import comp3095.assignment2.database.models.SectionCriteriaTemplate;

public class SectionCriteriaTemplateDbAccess extends DbAccess<SectionCriteriaTemplate> {

	public SectionCriteriaTemplateDbAccess(DatabaseHandler db) { super(db); }

	public void insert(SectionCriteriaTemplate criteriaTemplate) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `section_criteria_templates` (name, max_evaluation, report_section_id) VALUES (?, ?, ?)");
		stmt.setString(1, criteriaTemplate.getName());
		stmt.setInt(2, criteriaTemplate.getMaxEvaluation());
		stmt.setInt(3, criteriaTemplate.getReportSectionId());
		stmt.executeUpdate();
		criteriaTemplate.setId(getInsertId(stmt));
	}
	public List<SectionCriteriaTemplate> getCriterias(ReportSectionTemplate sectionTemplate) throws SQLException {
		PreparedStatement stmt = db.prepare("SELECT * FROM `section_criteria_templates` WHERE `report_section_id` = ?");
		stmt.setInt(1, sectionTemplate.getId());
		ResultSet results = stmt.executeQuery();
		
		ArrayList<SectionCriteriaTemplate> criterias = new ArrayList<>();
		while (results.next()) {
			SectionCriteriaTemplate criteriaTemplate = SectionCriteriaTemplate.fromResults(results);
			criterias.add(criteriaTemplate);
		}
		return criterias;
	}

	@Override
	protected SectionCriteriaTemplate createModel(ResultSet results) throws SQLException {
		return SectionCriteriaTemplate.fromResults(results);
	}

	@Override
	protected String getTableName() { return "section_criteria_templates"; }

}
>>>>>>> refs/heads/dev-arzu
