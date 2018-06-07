package comp3095.assignment2.forms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public final class StringUtil {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private StringUtil() {
	}

	public static int parseInt(String value, int defaultValue) {
		if (value == null) return defaultValue;
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException ex) {
			return defaultValue;
		}
	}
	public static String formatDate(Date date) {
		return DATE_FORMAT.format(date);
	}

	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	public static boolean isAlphabetic(String str) {
		return matches(str, "^[a-z 0-9]{1,}$", true);
	}

	public static boolean isEmail(String str) {
		return matches(str,
				"^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$",
				true);
	}

	public static boolean isNumber(String str) {
		return matches(str, "^-?[0-9]{1,}$", false);
	}

	public static boolean isDate(String str) {
		return toDate(str) != null;
	}

	public static int toUInt(String str) {
		try {
			return Integer.parseUnsignedInt(str);
		} catch (NumberFormatException ex) {
			return -1;
		}
	}
	public static Date toDate(String str) {
		try {
			return DATE_FORMAT.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	public static boolean matches(String value, String regex, boolean caseInsensitive) {
		if (isNullOrEmpty(value))
			return false;
		Pattern p;
		if (caseInsensitive) {
			p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		} else {
			p = Pattern.compile(regex);
		}
		return p.matcher(value).matches();
	}
	
	public static java.sql.Date toSqlDate(String string) {
		Date date = toDate(string);
		if (date == null) return null;
		return new java.sql.Date(date.getTime());
		}
}