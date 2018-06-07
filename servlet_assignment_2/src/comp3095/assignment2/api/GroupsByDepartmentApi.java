package comp3095.assignment2.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comp3095.assignment2.JsonHelper;
import comp3095.assignment2.Session;
import comp3095.assignment2.UrlHelper;
import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.Group;

@WebServlet("/api/groups/by/department/*")
public class GroupsByDepartmentApi extends HttpServlet {
	private static final long serialVersionUID = 7L;

	private static final String URL_BASE = "/servlet_assignment_2/api/groups/by/department/";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseHandler db = DatabaseHandler.create(response);
		if (db == null || !Session.assertIsStarted(db, request, response))
			return;

		String[] args = UrlHelper.getPathParts(request, URL_BASE);
		if (args.length == 0) {
			JsonHelper.writeEmptyResult(response);
			return;
		}

		try {
			int departmentId = Integer.parseInt(args[0]);
			if (departmentId < 0) {
				JsonHelper.writeEmptyResult(response);
			} else {
				List<Group> employees = db.groups.byDepartment(departmentId);
				JsonHelper.writeOutput(response, employees);
			}
		} catch (Exception ex) {
			JsonHelper.writeEmptyResult(response);
		}
	}
}
