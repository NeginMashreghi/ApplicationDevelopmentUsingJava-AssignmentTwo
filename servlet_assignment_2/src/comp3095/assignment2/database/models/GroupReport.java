package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupReport {
	private int _id;
	private int _groupId;
	private int _reportId;

	public int getId() { return _id; }
	public int getGroupId() { return _groupId; }
	public int getReportId() { return _reportId; }

	public GroupReport setId(int id) {
		_id = id;
		return this;
	}
	public GroupReport setGroupId(int groupId) {
		_groupId = groupId;
		return this;
	}
	public GroupReport setReportId(int reportId) {
		_reportId = reportId;
		return this;
	}

	public static GroupReport fromResults(ResultSet results) throws SQLException {
		return new GroupReport()
			.setId(results.getInt("id"))
			.setGroupId(results.getInt("group_id"))
			.setReportId(results.getInt("report_id"));
	}
}
