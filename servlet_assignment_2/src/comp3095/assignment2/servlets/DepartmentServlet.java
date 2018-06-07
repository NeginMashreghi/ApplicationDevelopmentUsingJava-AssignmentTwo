package comp3095.assignment2.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.Redirect;
import comp3095.assignment2.Session;
import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.Department;
import comp3095.assignment2.forms.impl.NewDepartmentForm;

@WebServlet("/servlets/departments/*")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseHandler db = DatabaseHandler.create(response);
		if (db == null || !Session.assertIsStarted(db, request, response)) return;

		HttpSession session = request.getSession();
		NewDepartmentForm form = new NewDepartmentForm();

		form.readRequest(request);
		if (!form.passedValidation()) {
			form.failFixFields(session, response);
			return;
		}

		try {
			Department department = form.toDepartment();
			db.departments.insert(department);
			Redirect.newDepartmentPage(response, true);
		} catch (SQLException ex) {
			form.failDbStmtError(session, response);
		}
	}
}
