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
import comp3095.assignment2.forms.impl.LoginForm;

@WebServlet("/servlets/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseHandler db = DatabaseHandler.create(response);
		if (db == null) return;
		if (Session.isStarted(db, request, response)) {
			// Session is already started, or a non-expired cookie was found and validated
			Redirect.homePage(response);
			return;
		}

		HttpSession session = request.getSession();
		LoginForm form = new LoginForm();

		form.readRequest(request);
		if (!form.passedValidation()) {
			form.failFixFields(session, response);
			return;
		}

		try {
			if (Session.login(db, session, response, form)) {
				Redirect.homePage(response);
			} else {
				form.fail(session, response, "Incorrect username/password combination");
			}
		} catch (SQLException e) {
			form.fail(session, response, "There was an unexpected database error");
		}
	}
}
