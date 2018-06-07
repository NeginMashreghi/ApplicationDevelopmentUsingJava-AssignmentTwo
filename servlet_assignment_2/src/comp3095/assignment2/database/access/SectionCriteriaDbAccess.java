package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.SectionCriteria;

public class SectionCriteriaDbAccess extends DbAccess<SectionCriteria> {
	public SectionCriteriaDbAccess(DatabaseHandler db) { super(db); }

	public void insert(SectionCriteria sectionCriteria) throws SQLException {
		PreparedStatement stmt = db.prepare("INSERT INTO `section_criteria` (evaluation, criteria_id) VALUES (?, ?)");
		stmt.setInt(1, sectionCriteria.getEvaluation());
		stmt.setInt(2, sectionCriteria.getSectionId());
		stmt.executeUpdate();
		sectionCriteria.setId(getInsertId(stmt));
	}

	@Override
	protected SectionCriteria createModel(ResultSet results) throws SQLException {
		return SectionCriteria.fromResults(results);
	}

	@Override
	protected String getTableName() { return "section_criteria"; }
}
