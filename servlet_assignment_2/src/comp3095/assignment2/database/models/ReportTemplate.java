package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ReportTemplate {
	private int _id;
	private String _name;
	private int _departmentId;
	private Date _created;
	
	public int getId() { return _id; }
	public String getName() { return _name; }
	public int getDepartmentId() { return _departmentId; }
	public Date getCreated() { return _created; }
	public java.sql.Date getCreatedSql() {
		long time = _created.getTime();
		return new java.sql.Date(time);
	}
	public ReportTemplate setId(int id) {
		_id = id;
		return this;
	}
	public ReportTemplate setName(String name) {
		_name = name;
		return this;
	}
	public ReportTemplate setDepartmentId(int departmentId) {
		_departmentId = departmentId;
		return this;
	}
	public ReportTemplate setCreatedSql(java.sql.Date created) {
		long time = created.getTime();
		return setCreated(new Date(time));
	}
	public ReportTemplate setCreated(Date created) {
		_created = created;
		return this;
	}

	public static ReportTemplate fromResults(ResultSet results) throws SQLException {
		return new ReportTemplate()
			.setId(results.getInt("id"))
			.setName(results.getString("name"))
			.setDepartmentId(results.getInt("department_id"))
			.setCreated(results.getDate("date_created"));
	}

}
