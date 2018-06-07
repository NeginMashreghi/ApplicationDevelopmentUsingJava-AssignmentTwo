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

@WebServlet("/servlets/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DatabaseHandler db = DatabaseHandler.create(response);
		if (db == null) return;
		try { Session.logout(db, session, response); }
		catch (SQLException e) { }
		Redirect.loginPage(response, false);
	}
}	
