package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionCriteria {
	private int _id;
	private int _evaluation;
	private int _sectionId;

	public int getId() { return _id; }
	public int getEvaluation() { return _evaluation; }
	public int getSectionId() { return _sectionId; }

	public SectionCriteria setId(int id) {
		_id = id;
		return this;
	}
	public SectionCriteria setEvaluation(int evaluation) {
		_evaluation = evaluation;
		return this;
	}
	public SectionCriteria setSectionId(int sectionId) {
		_sectionId = sectionId;
		return this;
	}

	public static SectionCriteria fromResults(ResultSet results) throws SQLException {
		return new SectionCriteria()
			.setId(results.getInt("id"))
			.setEvaluation(results.getInt("evaluation"))
			.setSectionId(results.getInt("criteria_id"));
	}
}
