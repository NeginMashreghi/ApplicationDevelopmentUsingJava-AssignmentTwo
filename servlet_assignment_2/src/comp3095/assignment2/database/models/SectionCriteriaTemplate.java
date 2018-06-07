package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionCriteriaTemplate {
	private int _id;
	private String _name;
	private int _maxEvaluation;
	private int _reportSectionId;

	public int getId() { return _id; }
	public String getName() { return _name; }
	public int getMaxEvaluation() { return _maxEvaluation; }
	public int getReportSectionId() { return _reportSectionId; }

	public SectionCriteriaTemplate setId(int id) {
		_id = id;
		return this;
	}
	public SectionCriteriaTemplate setName(String name) {
		_name = name;
		return this;
	}
	public SectionCriteriaTemplate setMaxEvaluation(int maxEvaluation) {
		_maxEvaluation = maxEvaluation;
		return this;
	}
	public SectionCriteriaTemplate setReportSectionId(int reportSectionId) {
		_reportSectionId = reportSectionId;
		return this;
	}

	public static SectionCriteriaTemplate fromResults(ResultSet results) throws SQLException {
		return new SectionCriteriaTemplate()
			.setId(results.getInt("id"))
			.setName(results.getString("name"))
			.setMaxEvaluation(results.getInt("max_evaluation"))
			.setReportSectionId(results.getInt("report_section_id"));
	}
}
