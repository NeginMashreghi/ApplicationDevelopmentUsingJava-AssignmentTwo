package comp3095.assignment2;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import comp3095.assignment2.forms.FormField;

public final class HtmlHelper {
	private HtmlHelper() { }

	public static void writeResetAndSubmit(JspWriter jw) throws IOException {
		writeFormRowStart(jw);
		writeFormHalfColStart(jw);
		writeButton(jw, "reset", "btn-dark", "Reset");
		writeDivEnd(jw);
		writeFormHalfColStart(jw);
		writeButton(jw, "submit", "btn-primary", "Create");
		writeDivEnd(jw);
		writeDivEnd(jw);
	}
	public static void writeButton(JspWriter jw, String type, String clazz, String text) throws IOException {
		jw.append("<button ");
		writeAttribute(jw, "type", type);
		jw.append(' ');
		writeFormClasses(jw, true, "btn " + clazz);
		jw.append('>');
		jw.append(text);
		jw.append("</button>");
	}

	public static void writeHr(JspWriter jw) throws IOException { jw.append("<hr>"); }
	public static void writeFormRowStart(JspWriter jw) throws IOException {
		jw.append("<div ");
		writeAttribute(jw, "class", "form-row");
		jw.append(">");
	}
	public static void writeFormGroup(JspWriter jw, String clazz) throws IOException {
		String extras = clazz == null ? "" : clazz;
		jw.append("<div ");
		writeAttribute(jw, "class", "form-group " + extras);
		jw.append('>');
	}
	public static void writeFormHalfColStart(JspWriter jw) throws IOException {
		writeFormGroup(jw, "col-md-6");
	}
	public static void writeFormOneThirdColStart(JspWriter jw) throws IOException {
		writeFormGroup(jw, "col-md-4");
	}
	public static void writeFormTwoThirdColStart(JspWriter jw) throws IOException {
		writeFormGroup(jw, "col-md-8");
	}
	
	public static void writeDivEnd(JspWriter jw) throws IOException {
		jw.append("</div>");
	}

	public static void writeSelectStart(JspWriter jw, FormField field, String name) throws IOException {
		writeSelectStart(jw, field, name, false);
	}
	public static void writeSelectStart(JspWriter jw, FormField field, String name, boolean disabled) throws IOException {
		writeLabel(jw, field, name);

		jw.append("<select ");
		writeFormClasses(jw, field);
		writeAttribute(jw, " name", field.getName());
		if (disabled)
			writeAttribute(jw, " disabled", "disabled");
		jw.append('>');
	}
	public static void writeSelectEnd(JspWriter jw, FormField field) throws IOException {
		jw.append("</select>");
		if (!field.passedValidation())
			writeInvalidFieldFeedback(jw, field.getErrorMessage());
	}
	public static void writeOption(JspWriter jw, int value, String displayName, boolean selected) throws IOException {
		jw.append("<option ");
		writeAttribute(jw, "value", Integer.toString(value));

		if (selected)
			writeAttribute(jw, " selected", "selected");

		jw.append('>');
		jw.append(displayName);
		jw.append("</option>");
	}
	public static void writeTextArea(JspWriter jw, FormField field, int rows, int columns, String friendlyName) throws IOException {
		writeFormGroup(jw, null);
		writeLabel(jw, field, friendlyName);
		jw.append("<textarea ");
		writeAttribute(jw, "name", field.getName());
		writeFormClasses(jw, field);
		writeAttribute(jw, " id", field.getName());
		writeAttribute(jw, " rows", Integer.toString(rows));
		writeAttribute(jw, " cols", Integer.toString(columns));
		jw.append("></textarea>");
		writeDivEnd(jw);
	}
	public static void writeRadioOption(JspWriter jw, FormField field, String value, String friendlyName, boolean checked) throws IOException {
		jw.append("<div ");
		writeAttribute(jw, "class", "radio");
		jw.append('>');

		jw.append("<label ");
		writeAttribute(jw, "for", field.getName());
		jw.append("><input ");
		writeAttribute(jw, "type", "radio");
		writeAttribute(jw, " name", field.getName());
		writeAttribute(jw, " value", value);
		if (checked)
			writeAttribute(jw, " checked", "checked");
		jw.append(' ');
		jw.append('>');
		jw.append(friendlyName);
		jw.append("</label></div>");
	}
	public static void writeStaticFormControl(JspWriter jw, String type, String value, String friendlyName) throws IOException {
		String str = null;
		writeLabel(jw, str, friendlyName);
		
		jw.append("<input ");
		writeAttribute(jw, "type", type);
		writeFormClasses(jw, true, null);
		writeAttribute(jw, " value", value);
		writeAttribute(jw, " disabled", "disabled");
		jw.append(">");
	}
	public static void writeFormControl(JspWriter jw, FormField field, String type, String friendlyName, boolean disabled) throws IOException {
		boolean hidden = type == null;
		if (hidden) {
			type = "hidden";
		} else {
			writeLabel(jw, field, friendlyName);
		}
		jw.append("<input ");
		// Write the input type
		writeAttribute(jw, "type", type);
		jw.append(' ');
		// write the bootstrap form classes (ie, form-invalid, form-control)
		writeFormClasses(jw, field);
		jw.append(' ');
		// write the form field name
		writeAttribute(jw, "name", field.getName());
		// If the field has a previous value, write that as an attribute as well
		if (field.getValue() != null) {
			jw.append(' ');
			writeAttribute(jw, "value", field.getValue());
		}
		if (disabled) {
			jw.append(" disabled");
		}
		jw.append(">");

		// If the field failed validation, show closable error alert
		if (!hidden && !field.passedValidation())
			writeInvalidFieldFeedback(jw, field.getErrorMessage());
	}
	
	public static void writeConditionalCloseableMessage(JspWriter jw, boolean condition, boolean isSuccess, String message) throws IOException {
		if (!condition) return;

		jw.append("<div class=\"alert alert-");
		// Write the alert type
		jw.append(isSuccess ? "success" : "danger");
		jw.append(" alert-dismissible fade show text-center\" role=\"alert\">\n");
		// Write the alert message
		jw.append(message);
		// Add the close button
		jw.append("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">");
		jw.append("<span aria-hidden=\"true\">&times;</span>");
		jw.append("</button>");
		jw.append("</div>");
	}
	private static void writeInvalidFieldFeedback(JspWriter jw, String feedback) throws IOException {
		jw.append("<div class=\"invalid-feedback\">");
		jw.append(feedback);
		jw.append("</div>");
	}

	private static void writeFormClasses(JspWriter jw, FormField field) throws IOException {
		writeFormClasses(jw, field.passedValidation(), null);
	}
	private static void writeFormClasses(JspWriter jw, boolean isValid, String extra) throws IOException {
		final String fieldClass = "form-control";
		String classes;
		// All fields have the class 'form-control'
		// Invalid fields additionally have 'is-invalid'
		if (isValid)
			classes = fieldClass;
		else 
			classes = fieldClass + " is-invalid";
		if (extra != null)
			classes = classes + " " + extra;
		writeAttribute(jw, "class", classes);
	}

	public static void writeAttribute(JspWriter jw, String attribute, String value) throws IOException {
		jw.append(attribute);
		jw.append("=\"");
		jw.append(value);
		jw.append("\"");
	}
	public static void writeLabel(JspWriter jw, String name, String friendlyName) throws IOException {
		jw.append("<label");
		if (name != null)
			writeAttribute(jw, " for", name);
		jw.write(">");
		jw.write(friendlyName);
		jw.write("</label>");
	}
	private static void writeLabel(JspWriter jw, FormField field, String friendlyName) throws IOException {
		writeLabel(jw, field.getName(), friendlyName);
	}
}
package comp3095.assignment2;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import comp3095.assignment2.forms.FormField;

public final class HtmlHelper {
	private HtmlHelper() { }

	public static void writeResetAndSubmit(JspWriter jw) throws IOException {
		writeFormRowStart(jw);
		writeFormHalfColStart(jw);
		writeButton(jw, "reset", "btn-dark", "Reset");
		writeDivEnd(jw);
		writeFormHalfColStart(jw);
		writeButton(jw, "submit", "btn-primary", "Create");
		writeDivEnd(jw);
		writeDivEnd(jw);
	}
	public static void writeButton(JspWriter jw, String type, String clazz, String text) throws IOException {
		jw.append("<button ");
		writeAttribute(jw, "type", type);
		jw.append(' ');
		writeFormClasses(jw, true, "btn " + clazz);
		jw.append('>');
		jw.append(text);
		jw.append("</button>");
	}

	public static void writeHr(JspWriter jw) throws IOException { jw.append("<hr>"); }
	public static void writeFormRowStart(JspWriter jw) throws IOException {
		jw.append("<div ");
		writeAttribute(jw, "class", "form-row");
		jw.append(">");
	}
	public static void writeFormGroup(JspWriter jw, String clazz) throws IOException {
		String extras = clazz == null ? "" : clazz;
		jw.append("<div ");
		writeAttribute(jw, "class", "form-group " + extras);
		jw.append('>');
	}
	public static void writeFormHalfColStart(JspWriter jw) throws IOException {
		writeFormGroup(jw, "col-md-6");
	}
	public static void writeFormOneThirdColStart(JspWriter jw) throws IOException {
		writeFormGroup(jw, "col-md-4");
	}
	public static void writeFormTwoThirdColStart(JspWriter jw) throws IOException {
		writeFormGroup(jw, "col-md-8");
	}
	
	public static void writeDivEnd(JspWriter jw) throws IOException {
		jw.append("</div>");
	}

	public static void writeSelectStart(JspWriter jw, FormField field, String name) throws IOException {
		writeSelectStart(jw, field, name, false);
	}
	public static void writeSelectStart(JspWriter jw, FormField field, String name, boolean disabled) throws IOException {
		writeLabel(jw, field, name);

		jw.append("<select ");
		writeFormClasses(jw, field);
		writeAttribute(jw, " name", field.getName());
		if (disabled)
			writeAttribute(jw, " disabled", "disabled");
		jw.append('>');
	}
	public static void writeSelectEnd(JspWriter jw, FormField field) throws IOException {
		jw.append("</select>");
		if (!field.passedValidation())
			writeInvalidFieldFeedback(jw, field.getErrorMessage());
	}
	public static void writeOption(JspWriter jw, int value, String displayName, boolean selected) throws IOException {
		jw.append("<option ");
		writeAttribute(jw, "value", Integer.toString(value));

		if (selected)
			writeAttribute(jw, " selected", "selected");

		jw.append('>');
		jw.append(displayName);
		jw.append("</option>");
	}
	public static void writeTextArea(JspWriter jw, FormField field, int rows, int columns, String friendlyName) throws IOException {
		writeFormGroup(jw, null);
		writeLabel(jw, field, friendlyName);
		jw.append("<textarea ");
		writeAttribute(jw, "name", field.getName());
		writeFormClasses(jw, field);
		writeAttribute(jw, " id", field.getName());
		writeAttribute(jw, " rows", Integer.toString(rows));
		writeAttribute(jw, " cols", Integer.toString(columns));
		jw.append("></textarea>");
		writeDivEnd(jw);
	}
	public static void writeRadioOption(JspWriter jw, FormField field, String value, String friendlyName, boolean checked) throws IOException {
		jw.append("<div ");
		writeAttribute(jw, "class", "radio");
		jw.append('>');

		jw.append("<label ");
		writeAttribute(jw, "for", field.getName());
		jw.append("><input ");
		writeAttribute(jw, "type", "radio");
		writeAttribute(jw, " name", field.getName());
		writeAttribute(jw, " value", value);
		if (checked)
			writeAttribute(jw, " checked", "checked");
		jw.append(' ');
		jw.append('>');
		jw.append(friendlyName);
		jw.append("</label></div>");
	}
	public static void writeStaticFormControl(JspWriter jw, String type, String value, String friendlyName) throws IOException {
		String str = null;
		writeLabel(jw, str, friendlyName);
		
		jw.append("<input ");
		writeAttribute(jw, "type", type);
		writeFormClasses(jw, true, null);
		writeAttribute(jw, " value", value);
		writeAttribute(jw, " disabled", "disabled");
		jw.append(">");
	}
	public static void writeFormControl(JspWriter jw, FormField field, String type, String friendlyName, boolean disabled) throws IOException {
		boolean hidden = type == null;
		if (hidden) {
			type = "hidden";
		} else {
			writeLabel(jw, field, friendlyName);
		}
		jw.append("<input ");
		// Write the input type
		writeAttribute(jw, "type", type);
		jw.append(' ');
		// write the bootstrap form classes (ie, form-invalid, form-control)
		writeFormClasses(jw, field);
		jw.append(' ');
		// write the form field name
		writeAttribute(jw, "name", field.getName());
		// If the field has a previous value, write that as an attribute as well
		if (field.getValue() != null) {
			jw.append(' ');
			writeAttribute(jw, "value", field.getValue());
		}
		if (disabled) {
			jw.append(" disabled");
		}
		jw.append(">");

		// If the field failed validation, show closable error alert
		if (!hidden && !field.passedValidation())
			writeInvalidFieldFeedback(jw, field.getErrorMessage());
	}
	
	public static void writeConditionalCloseableMessage(JspWriter jw, boolean condition, boolean isSuccess, String message) throws IOException {
		if (!condition) return;

		jw.append("<div class=\"alert alert-");
		// Write the alert type
		jw.append(isSuccess ? "success" : "danger");
		jw.append(" alert-dismissible fade show text-center\" role=\"alert\">\n");
		// Write the alert message
		jw.append(message);
		// Add the close button
		jw.append("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">");
		jw.append("<span aria-hidden=\"true\">&times;</span>");
		jw.append("</button>");
		jw.append("</div>");
	}
	private static void writeInvalidFieldFeedback(JspWriter jw, String feedback) throws IOException {
		jw.append("<div class=\"invalid-feedback\">");
		jw.append(feedback);
		jw.append("</div>");
	}

	private static void writeFormClasses(JspWriter jw, FormField field) throws IOException {
		writeFormClasses(jw, field.passedValidation(), null);
	}
	private static void writeFormClasses(JspWriter jw, boolean isValid, String extra) throws IOException {
		final String fieldClass = "form-control";
		String classes;
		// All fields have the class 'form-control'
		// Invalid fields additionally have 'is-invalid'
		if (isValid)
			classes = fieldClass;
		else 
			classes = fieldClass + " is-invalid";
		if (extra != null)
			classes = classes + " " + extra;
		writeAttribute(jw, "class", classes);
	}

	public static void writeAttribute(JspWriter jw, String attribute, String value) throws IOException {
		jw.append(attribute);
		jw.append("=\"");
		jw.append(value);
		jw.append("\"");
	}
	public static void writeLabel(JspWriter jw, String name, String friendlyName) throws IOException {
		jw.append("<label");
		if (name != null)
			writeAttribute(jw, " for", name);
		jw.write(">");
		jw.write(friendlyName);
		jw.write("</label>");
	}
	private static void writeLabel(JspWriter jw, FormField field, String friendlyName) throws IOException {
		writeLabel(jw, field.getName(), friendlyName);
	}
}