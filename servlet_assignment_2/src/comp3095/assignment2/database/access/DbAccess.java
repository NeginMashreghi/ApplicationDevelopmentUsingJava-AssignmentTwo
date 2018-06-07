package comp3095.assignment2.database.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3095.assignment2.database.DatabaseHandler;

// Implements get all and by id for subclasses
public abstract class DbAccess<T> {
	protected final DatabaseHandler db;

	protected DbAccess(DatabaseHandler db) {
		this.db = db;
	}
	public int getInsertId(PreparedStatement stmt) throws SQLException {
		ResultSet results = stmt.getGeneratedKeys();
		if (!results.next()) return -1;
		return results.getInt(1);
	}

	public T get(int id) throws SQLException {
		String sql = String.join("", new String[] {
			"SELECT * FROM `",
			getTableName(),
			"` WHERE `id` = ? LIMIT 1"
		});

		PreparedStatement stmt = db.prepare(sql);
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		if (!results.next())
			return null;
		T model = createModel(results);
		return model;
	}
	public List<T> getAll() throws SQLException {
		String sql = String.join("", new String[] {
			"SELECT * FROM `",
			getTableName(),
			"`"
		});

		ResultSet results = db.execute(sql);
		ArrayList<T> list = new ArrayList<>();
		while (results.next()) {
			T model = createModel(results);
			list.add(model);
		}
		return list;
	}

	protected abstract T createModel(ResultSet results) throws SQLException;
	protected abstract String getTableName();
}
