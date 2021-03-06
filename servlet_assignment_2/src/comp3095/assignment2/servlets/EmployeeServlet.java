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
import comp3095.assignment2.database.models.Employee;
import comp3095.assignment2.forms.impl.NewEmployeeForm;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/servlets/employees")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 4L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseHandler db = DatabaseHandler.create(response);
		if (db == null || !Session.assertIsStarted(db, request, response)) return;

		HttpSession session = request.getSession();
		NewEmployeeForm form = new NewEmployeeForm();

		form.readRequest(request);
		if (!form.passedValidation()) {
			form.failFixFields(session, response);
			return;
		}

		Employee employee = form.toEmployee();
		try {
			db.employees.insert(employee);
			Redirect.newEmployeePage(response, true);
		} catch (SQLException ex) {
			form.failDbStmtError(session, response);
		}
	}
}
