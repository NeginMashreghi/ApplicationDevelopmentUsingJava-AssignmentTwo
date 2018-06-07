package comp3095.assignment2;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comp3095.assignment2.database.DatabaseHandler;
import comp3095.assignment2.database.models.Employee;
import comp3095.assignment2.forms.Form;
import comp3095.assignment2.forms.StringUtil;
import comp3095.assignment2.forms.impl.LoginForm;

public final class Session {
	private static final ThreadLocal<SecureRandom> _secureRandom = new ThreadLocal<>();

	private static final String AUTH_COOKIE = "auth";
	private static final String IS_STARTED = "isStarted";
	private static final String EMPLOYEE = "employee";
	private static final String FORM = "form";
	private static final int COOKIE_MAX_AGE = 3600;
	private static final String COOKIE_PATH = "/servlet_assignment_2/";

	private Session() {
	}

	public static boolean isStarted(HttpSession session) {
		Boolean isStarted = (Boolean) session.getAttribute(IS_STARTED);
		return isStarted != null && isStarted.booleanValue() && getUser(session) != null;
	}

	public static boolean isStarted(DatabaseHandler db, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return isStarted(request.getSession()) || tryStart(db, request, response);
	}
	public static boolean assertIsStarted(DatabaseHandler db, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		boolean started = isStarted(db, request, response);
		if (!started) Redirect.loginPage(response, true);
		return started;
	}

	public static Employee getUser(HttpSession session) {
		return (Employee) session.getAttribute(EMPLOYEE);
	}

	public static boolean login(DatabaseHandler db, HttpSession session, HttpServletResponse response, LoginForm form)
			throws SQLException, IOException {
		String username = form.getUsername().getValue();
		String password = form.getPassword().getValue();

		Employee employee = db.employees.byUsername(username);
		if (employee == null || !employee.getPassword().contentEquals(password))
			return false;
		start(session, employee);
		if ("on".equals(form.getRememberMe().getValue()))
			addCookieSecret(db, response, employee);
		return true;
	}

	public static void logout(DatabaseHandler db, HttpSession session, HttpServletResponse response)
			throws IOException, SQLException {
		if (isStarted(session)) {
			Employee user = (Employee) session.getAttribute(EMPLOYEE);
			end(session);
			if (user != null)
				deleteCookieSecret(db, response, user);
		}
	}

	private static boolean tryStart(DatabaseHandler db, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Cookie cookie = getCookie(request, AUTH_COOKIE);
		if (cookie == null)
			return false;

		String cookieSecret = cookie.getValue();
		if (StringUtil.isNullOrEmpty(cookieSecret))
			return false;

		try {
			Employee employee = db.employees.byCookieSecret(cookieSecret);
			if (employee == null)
				return false;
			if (employee.getCookieExpiration().after(new Date())) {
				deleteCookieSecret(db, response, employee);
				return false;
			}

			HttpSession session = request.getSession();
			start(session, employee);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static void setFormError(HttpSession session, Form form, String message) throws IOException {
		form.setErrorMessage(message);
		Session.setForm(session, form);
	}

	public static void setForm(HttpSession session, Form form) {
		session.setAttribute(FORM, form);
	}

	@SuppressWarnings("unchecked") // (╯°□°）╯︵ ┻━┻ it is checked against clazz input.
	public static <T extends Form> T getForm(HttpSession session, Class<T> clazz) {
		Object o = session.getAttribute(FORM);
		if (o != null) {
			session.removeAttribute(FORM);
			if (o.getClass().isAssignableFrom(clazz)) {
				return (T) o;
			}
		}
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	private static void start(HttpSession session, Employee employee) throws IOException, SQLException {
		session.setAttribute(IS_STARTED, new Boolean(true));
		session.setAttribute(EMPLOYEE, employee.sanitizeForSession());
	}

	private static void end(HttpSession session) throws IOException {
		session.removeAttribute(IS_STARTED);
		session.removeAttribute(EMPLOYEE);
		session.invalidate();
	}

	private static void addCookieSecret(DatabaseHandler db, HttpServletResponse response, Employee employee)
			throws SQLException {
		String cookieSecret = createCookieSecret();
		Cookie cookie = new Cookie(AUTH_COOKIE, cookieSecret);
		cookie.setMaxAge(COOKIE_MAX_AGE);
		cookie.setPath(COOKIE_PATH);

		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.SECOND, COOKIE_MAX_AGE);

		response.addCookie(cookie);
		db.employees.changeCookieSecret(employee, cookieSecret, cal.getTime());
	}

	public static void deleteCookieSecret(DatabaseHandler db, HttpServletResponse response, Employee user)
			throws IOException, SQLException {
		Cookie cookie = new Cookie(AUTH_COOKIE, "");
		cookie.setPath(COOKIE_PATH);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		db.employees.changeCookieSecret(user, null, new Date());
	}

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0)
			return null;
		for (Cookie cookie : cookies) {
			if (name.contentEquals(cookie.getName()))
				return cookie;
		}
		return null;
	}

	private static String createCookieSecret() {
		SecureRandom random = _secureRandom.get();
		if (random == null) {
			random = new SecureRandom();
			_secureRandom.set(random);
		}
		byte[] buffer = new byte[32];
		random.nextBytes(buffer);
		return Base64.getEncoder().encodeToString(buffer);
	}
}
