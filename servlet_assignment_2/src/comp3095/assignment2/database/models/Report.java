package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Report {
	private int _id;
	private String _title;
	private int _templateId;
	private int _reportType;
	private Date _creationDate;
	
	public int getId() { return _id; }
	public String getTitle() { return _title; }
	public int getTemplateId() { return _templateId; }
	public int getReportType() { return _reportType; }
	public Date getCreationDate() { return _creationDate; }
	public java.sql.Date getCreationDateSql() { 
		long time = _creationDate.getTime();
		return new java.sql.Date(time);
	}
	
	public Report setId(int id) {
		_id = id;
		return this;
	}
	public Report setTitle(String title) {
		_title = title;
		return this;
	}
	public Report setTemplateId(int templateId) {
		_templateId = templateId;
		return this;
	}
	public Report setReportType(int reportType) {
		_reportType = reportType;
		return this;
	}
	public Report setCreationDate(Date creationDate) {
		_creationDate = creationDate;
		return this;
	}
	public Report setCreationDate(java.sql.Date creationDate) {
		long time = creationDate.getTime();
		return setCreationDate(new Date(time));
	}

	public static Report fromResults(ResultSet results) throws SQLException {
		return new Report()
			.setId(results.getInt("id"))
			.setTitle(results.getString("title"))
			.setTemplateId(results.getInt("template_id"))
			.setReportType(results.getInt("report_type"))
			.setCreationDate(results.getDate("creation_date"));
	}
}
