package comp3095.assignment2.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Department {
	private int _id;
	private String _name;
	private String _location;

	public Department() { }

	public int getId() { return _id; }
	public String getName() { return _name; }
	public String getLocation() { return _location; }

	public Department setId(int id) {
		_id = id;
		return this;
	}
	public Department setName(String name) {
		_name = name;
		return this;
	}
	public Department setLocation(String location) {
		_location = location;
		return this;
	}

	public static Department fromResults(ResultSet results) throws SQLException {
		return new Department()
				.setId(results.getInt("id"))
				.setName(results.getString("name"))
				.setLocation(results.getString("location"));
	}
}
