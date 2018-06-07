package comp3095.assignment2;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class TableHelper {
	public static void writeRowStart(JspWriter jw) throws IOException {
		jw.append("<tr>");
	}
	public static void writeRowEnd(JspWriter jw) throws IOException {
		jw.append("</tr>");
	}
	public static void writeHeader(JspWriter jw, String value, String scope) throws IOException {
		jw.append("<th");
		if (scope != null) {
			jw.append(' ');
			HtmlHelper.writeAttribute(jw, "scope", scope);
		}
		jw.append(">");
		jw.append(value);
		jw.append("</th>");
	}
	public static void writeDefinition(JspWriter jw, String value, String scope) throws IOException {
		jw.append("<td");
		if (scope != null) {
			jw.append(' ');
			HtmlHelper.writeAttribute(jw, "scope", scope);
		}
		jw.append(">");
		jw.append(value);
		jw.append("</td>");
	}
}
