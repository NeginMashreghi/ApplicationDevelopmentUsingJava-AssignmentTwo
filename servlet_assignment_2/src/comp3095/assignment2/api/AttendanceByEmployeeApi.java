package comp3095.assignment2.api;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comp3095.assignment2.JsonHelper;
import comp3095.assignment2.Session;
import comp3095.assignment2.UrlHelper;
import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.Attendance;
import comp3095.assignment2.forms.StringUtil;

@WebServlet("/api/attendance/by/employee/*")
public class AttendanceByEmployeeApi extends HttpServlet {
	private static final long serialVersionUID = 40L;

	private static final String URL_BASE = "/servlet_assignment_2/api/attendance/by/employee/";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseHandler db = DatabaseHandler.create(response);
		if (db == null || !Session.assertIsStarted(db, request, response))
			return;

		String[] args = UrlHelper.getPathParts(request, URL_BASE);
		if (args.length != 2) {
			JsonHelper.writeEmptyResult(response);
			return;
		}

		try {
			int employeeId = Integer.parseInt(args[0]);
			Date date = StringUtil.toDate(args[1]);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			if (employeeId < 0) {
				JsonHelper.writeEmptyResult(response);
			} else {
				Attendance attendance = db.attendance.byEmployee(employeeId, sqlDate);
				JsonHelper.writeOutput(response, attendance);
			}
		} catch (Exception ex) {
			JsonHelper.writeEmptyResult(response);
		}
	}
}
