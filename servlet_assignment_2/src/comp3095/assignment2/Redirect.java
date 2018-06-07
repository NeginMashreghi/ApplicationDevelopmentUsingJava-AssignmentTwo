package comp3095.assignment2;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public final class Redirect {
	private Redirect() {
	}

	private static final String SUCCESS_QUERY_STRING = "?success=1";
	private static final String BASE_URL = "/servlet_assignment_2/";
	
	//private static final String PICK_TEMPLATE_URL = BASE_URL + "reports/pick_template.jsp";
	
	

	private static final String HOME_URL = BASE_URL + "home.jsp";
	private static final String LOGIN_URL = BASE_URL;
	private static final String UNAUTHORIZED_URL = LOGIN_URL + "?unauthorized=1";

	private static final String NEW_DEPARTMENT_URL = BASE_URL + "departments/new.jsp";
	private static final String NEW_DEPARTMENT_SUCCESS_URL = NEW_DEPARTMENT_URL + SUCCESS_QUERY_STRING;

	private static final String NEW_EMPLOYEE_URL = BASE_URL + "employees/new.jsp";
	private static final String NEW_EMPLOYEE_SUCCESS_URL = NEW_EMPLOYEE_URL + SUCCESS_QUERY_STRING;

	private static final String NEW_GROUP_URL = BASE_URL + "groups/new.jsp";
	private static final String NEW_GROUP_SUCCESS_URL = NEW_GROUP_URL + SUCCESS_QUERY_STRING;

	private static final String NEW_REPORT_URL = BASE_URL + "reports/new.jsp";
	private static final String NEW_REPORT_SUCCESS_URL = NEW_REPORT_URL + SUCCESS_QUERY_STRING;
	
	private static final String NEW_REPORT_TEMPLATE_URL = BASE_URL + "reports/pick_report_template.jsp";
	private static final String NEW_REPORT_TEMPLATE_SUCCESS_URL = NEW_REPORT_TEMPLATE_URL + SUCCESS_QUERY_STRING;
	
	public static void loginPage(HttpServletResponse response, boolean unauthorized) throws IOException {
		redirectIf(response, unauthorized, UNAUTHORIZED_URL, LOGIN_URL);
	}

	public static void homePage(HttpServletResponse response) throws IOException {
		response.sendRedirect(HOME_URL);
	}

	public static void newDepartmentPage(HttpServletResponse response, boolean success) throws IOException {
		redirectIf(response, success, NEW_DEPARTMENT_SUCCESS_URL, NEW_DEPARTMENT_URL);
	}
	public static void newEmployeePage(HttpServletResponse response, boolean success) throws IOException {
		redirectIf(response, success, NEW_EMPLOYEE_SUCCESS_URL, NEW_EMPLOYEE_URL);
	}
	public static void newGroupPage(HttpServletResponse response, boolean success) throws IOException {
		redirectIf(response, success, NEW_GROUP_SUCCESS_URL, NEW_GROUP_URL);
	}
	
	public static void newReportPage(HttpServletResponse response, boolean success) throws IOException {
		redirectIf(response, success, NEW_REPORT_SUCCESS_URL ,  NEW_REPORT_URL);
	}
	
	public static void newReportTemplatePage(HttpServletResponse response, boolean success) throws IOException {
		redirectIf(response, success, NEW_REPORT_TEMPLATE_SUCCESS_URL, NEW_REPORT_TEMPLATE_URL);
	}

	private static void redirectIf(HttpServletResponse response, boolean success, String successUrl, String failedUrl) throws IOException {
		final String url = success ? successUrl : failedUrl;
		response.sendRedirect(url);
	}
}
