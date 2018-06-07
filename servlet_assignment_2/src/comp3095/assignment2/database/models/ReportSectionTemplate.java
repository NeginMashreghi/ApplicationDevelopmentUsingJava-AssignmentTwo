package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportSectionTemplate {
	private int _id;
	private String _name;
	private int _reportTemplateId;
	private boolean _short;

	public int getId() { return _id; }
	public String getName() { return _name; }
	public int getReportTemplateId() { return _reportTemplateId; }
	public boolean isShort() { return _short; }

	public ReportSectionTemplate setId(int id) {
		_id = id;
		return this;
	}
	public ReportSectionTemplate setName(String name) {
		_name = name;
		return this;
	}
	public ReportSectionTemplate setReportTemplateId(int reportTemplateId) {
		_reportTemplateId = reportTemplateId;
		return this;
	}
	public ReportSectionTemplate setShort(boolean isShort) {
		_short = isShort;
		return this;
	}
	
	public static ReportSectionTemplate fromResults(ResultSet results) throws SQLException {
		return new ReportSectionTemplate()
			.setId(results.getInt("id"))
			.setName(results.getString("name"))
			.setReportTemplateId(results.getInt("report_template_id"))
			.setShort(results.getBoolean("is_short"));
	}
}
