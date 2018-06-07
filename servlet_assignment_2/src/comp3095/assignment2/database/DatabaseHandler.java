package comp3095.assignment2.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

import comp3095.assignment2.Redirect;
import comp3095.assignment2.database.access.*;

import java.io.IOException;
import java.sql.Connection;

public class DatabaseHandler implements AutoCloseable {
	public static final String CONNECT_ERROR_MSG = "There was an unexpected error connecting to the database";
	public static final String STMT_ERROR_MSG = "There was an unexpected error while executing your request";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	public final Connection connection;
	public final AttendanceDbAccess attendance;
	public final DepartmentDbAccess departments;
	public final EmployeeDbAccess employees;
	public final GroupDbAccess groups;

	public final ReportDbAccess reports;
	public final EmployeeReportDbAccess employeeReports;
	public final GroupReportDbAccess groupReports;
	public final ReportSectionDbAccess reportSections;
	public final SectionCriteriaDbAccess sectionCriteria;

	public final ReportTemplateDbAccess reportTemplates;
	public final ReportSectionTemplateDbAccess reportSectionTemplates;
	public final SectionCriteriaTemplateDbAccess sectionCriteriaTemplates;

	private DatabaseHandler() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COMP3095", USERNAME, PASSWORD);

		attendance = new AttendanceDbAccess(this);
		departments = new DepartmentDbAccess(this);
		employees = new EmployeeDbAccess(this);
		groups = new GroupDbAccess(this);

		reports = new ReportDbAccess(this);
		employeeReports = new EmployeeReportDbAccess(this);
		groupReports = new GroupReportDbAccess(this);
		reportSections = new ReportSectionDbAccess(this);
		sectionCriteria = new SectionCriteriaDbAccess(this);

		reportTemplates = new ReportTemplateDbAccess(this);
		reportSectionTemplates = new ReportSectionTemplateDbAccess(this);
		sectionCriteriaTemplates = new SectionCriteriaTemplateDbAccess(this);
	}

	public static DatabaseHandler create(HttpServletResponse response) throws IOException {
		return create(response, true);
	}

	public static DatabaseHandler create(HttpServletResponse response, boolean redirect) throws IOException {
		try {
			return new DatabaseHandler();
		} catch (Exception e) {
			if (redirect)
				Redirect.loginPage(response, false);
			return null;
		}
	}

	public PreparedStatement prepare(String sql) throws SQLException {
		return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	}
	public ResultSet execute(String sql) throws SQLException {
		return prepare(sql).executeQuery();
	}

	@Override
	public void close() throws Exception {
		if (!connection.isClosed())
			connection.close();
	}
}
