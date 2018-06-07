package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportSection {
	private int _id;
	private int _sectionId;
	private String _evaluation;

	public int getId() { return _id; }
	public int getSectionId() { return _sectionId; }
	public String getEvaluation() { return _evaluation; }

	public ReportSection setId(int id) {
		_id = id;
		return this;
	}
	public ReportSection setSectionId(int sectionId) {
		_sectionId = sectionId;
		return this;
	}
	public ReportSection setEvaluation(String evaluation) {
		_evaluation = evaluation;
		return this;
	}

	public static ReportSection fromResults(ResultSet results) throws SQLException {
		return new ReportSection()
			.setId(results.getInt("id"))
			.setSectionId(results.getInt("section_id"))
			.setEvaluation(results.getString("evaluation"));
	}
}
