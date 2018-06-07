package comp3095.assignment2.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.Redirect;
import comp3095.assignment2.Session;
import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.Group;
import comp3095.assignment2.database.models.GroupMember;
import comp3095.assignment2.forms.impl.NewGroupForm;

/**
 * Servlet implementation class GroupServlet
 */
@WebServlet("/servlets/new_group")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 5L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseHandler db = DatabaseHandler.create(response);
		if (db == null || !Session.assertIsStarted(db, request, response)) return;

		HttpSession session = request.getSession();
		NewGroupForm form = new NewGroupForm();

		form.readRequest(request);
		if (!form.passedValidation()) {
			form.failFixFields(session, response);
			return;
		}

		Group group = form.toGroup();
		try {
			db.groups.insert(group);
			ArrayList<GroupMember> members = form.getMembers(group);
			for (GroupMember member : members)
				db.groups.insertMember(member);
			Redirect.newGroupPage(response, true);
		} catch (SQLException ex) {
			form.failDbStmtError(session, response);
		}
	}

}
