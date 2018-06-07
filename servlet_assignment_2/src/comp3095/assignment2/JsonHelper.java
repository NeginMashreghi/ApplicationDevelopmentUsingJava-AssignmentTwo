package comp3095.assignment2;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public final class JsonHelper {
	private JsonHelper() { }
	private static final Object[] EMPTY_ARR = new Object[0];
	public static Object getNotNull(Object val) {
		return val == null
				? new Object()
				: val;
	}
	public static void writeOutput(HttpServletResponse response, Object o) throws IOException {
		byte[] json = JSON.toJSONBytes(o);
		response.getOutputStream().write(json);
	}
	public static void writeEmptyResult(HttpServletResponse response) throws IOException { writeOutput(response, EMPTY_ARR); }
}
