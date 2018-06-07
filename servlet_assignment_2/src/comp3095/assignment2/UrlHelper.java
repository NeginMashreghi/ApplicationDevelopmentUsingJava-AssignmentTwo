package comp3095.assignment2;

import javax.servlet.http.HttpServletRequest;

public class UrlHelper {
	private static final String[] EMPTY_URL = new String[0];
	public static String[] getPathParts(HttpServletRequest request, String urlBase) {
		String requestUrl = request.getRequestURI();
		final boolean hasArgs = requestUrl.length() > urlBase.length();
		if (hasArgs) {
			requestUrl = requestUrl.substring(urlBase.length());
			return requestUrl.split("/");
		} else return EMPTY_URL;
	}
}
