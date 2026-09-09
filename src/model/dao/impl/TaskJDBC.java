package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DbException;
import model.dao.TaskDao;
import model.entities.Task;
import model.entities.User;

public class TaskJDBC implements TaskDao {

	private static Connection conn = null;

	public TaskJDBC(Connection conn) {
		this.conn = conn;
		;
	}

	@Override
	public void insert(Task obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Task (title,description,data_entrega,status,user_id) "
			+ "VALUES  (?,?,?,?,?) ",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getTitulo());
			st.setString(2, obj.getDescricao());
			st.setDate(3, new java.sql.Date(obj.getDataEntrega().getTime()));
			st.setString(4, obj.getStatus());
			st.setInt(5, obj.getUser().getId());
			int rows = st.executeUpdate();
			if(rows > 0) {
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				obj.setId(id);
			}} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException("Error inserting task:" + e.getMessage());
		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new DbException("Error close" + e.getMessage());
			}
		}

	}

	@Override
	public void update(Task obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer Id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Task> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findById(Task Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
