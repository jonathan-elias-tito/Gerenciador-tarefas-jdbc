package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DbConn;
import db.DbException;
import model.dao.TaskDao;
import model.entities.Task;
import model.entities.User;

public class TaskJDBC implements TaskDao {

	private Connection conn = null;

	public TaskJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Task obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Task (title,description,data_entrega,status,user_id) " 
			+ "VALUES  (?,?,?,?,?) "
			,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getTitulo());
			st.setString(2, obj.getDescricao());
			st.setDate(3, new java.sql.Date(obj.getDataEntrega().getTime()));
			st.setString(4, obj.getStatus());
			st.setInt(5, obj.getUser().getId());
			int rows = st.executeUpdate();
			if (rows > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			} else {
				throw new DbException("Insert failed: No rows affected.");
			}
		} catch (SQLException e) {
			throw new DbException("Error " + e.getMessage());
		} finally {
			DbConn.closeResultSet(rs);
			DbConn.closerStatement(st);
		}

	}

	@Override
	public void update(Task obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Task "
					+ "SET title = ?,description = ?,data_entrega = ?,status = ?,user_id = ? " + "WHERE id = ? ");
			st.setString(1, obj.getTitulo());
			st.setString(2, obj.getDescricao());
			st.setDate(3, new java.sql.Date(obj.getDataEntrega().getTime()));
			st.setString(4, obj.getStatus());
			st.setInt(5, obj.getUser().getId());
			st.setInt(6, obj.getId());
			int rows = st.executeUpdate();
			if (rows == 0) {
				throw new DbException("Update failed: ID not found");
			}
		} catch (SQLException e) {
			throw new DbException("Error:" + e.getMessage());
		} finally {
			DbConn.closerStatement(st);
		}
	}

	@Override
	public void deleteById(Integer Id) {

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM Task " + "WHERE id = ? ");
			st.setInt(1, Id);
			int rows = st.executeUpdate();
			if (rows == 0) {
				throw new DbException("Delete failed: ID not found.");
			}

		} catch (SQLException e) {
			throw new DbException("Error:" + e.getMessage());
		} finally {
			DbConn.closerStatement(st);
		}
	}

	@Override
	public List<Task> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT task.*, user.name AS user_name, user.email AS user_email " + "FROM task "
					+ "INNER JOIN user ON task.user_id = user.id");
			rs = st.executeQuery();
			List<Task> lista = new ArrayList<>();
			Map<Integer, User> map = new HashMap<>();
			while (rs.next()) {
				User objU = map.get(rs.getInt("user_id"));
				if (objU == null) {
					objU = newUser(rs);
					map.put(rs.getInt("user_id"), objU);
				}
				Task obj = newTask(rs, objU);
				lista.add(obj);
			}
			return lista;
		} catch (SQLException e) {
			throw new DbException("Error:" + e.getMessage());
		} finally {
			DbConn.closeResultSet(rs);
			DbConn.closerStatement(st);
		}
	}

	@Override
	public Task findById(Integer Id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT task.*, user.name AS user_name, user.email AS user_email "
					+ "FROM task INNER JOIN user ON task.user_id = user.id " + "WHERE task.id = ?");
			st.setInt(1, Id);
			rs = st.executeQuery();

			if (rs.next()) {
				User objU = newUser(rs);
				Task obj = newTask(rs, objU);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DbConn.closeResultSet(rs);
			DbConn.closerStatement(st);
		}

	}

	@Override
	public List<Task> findByUser(User user) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT task.*, user.name AS user_name, user.email AS user_email "
					+ "FROM task INNER JOIN user ON task.user_id = user.id " + "WHERE user_id = ? ");
			st.setInt(1, user.getId());
			rs = st.executeQuery();
			List<Task> lista = new ArrayList<>();
			Map<Integer, User> map = new HashMap<>();
			while (rs.next()) {
				User objU = map.get(rs.getInt("user_id"));
				if (objU == null) {
					objU = newUser(rs);
					map.put(rs.getInt("user_id"), objU);
				}
				Task objT = newTask(rs, objU);
				lista.add(objT);
			}
			return lista;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DbConn.closeResultSet(rs);
			DbConn.closerStatement(st);
		}

	}

	public List<Task> findByStatus(String status) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT task.*, user.name AS user_name, user.email AS user_email "
					+ "FROM task INNER JOIN user ON task.user_id = user.id " + "WHERE status = ? ");
			st.setString(1, status);
			rs = st.executeQuery();
			List<Task> lista = new ArrayList<>();
			Map<Integer, User> map = new HashMap<>();
			while (rs.next()) {
				User objU = map.get(rs.getInt("user_id"));
				if (objU == null) {
					objU = newUser(rs);
					map.put(rs.getInt("user_id"), objU);
				}
				Task objT = newTask(rs, objU);
				lista.add(objT);
			}
			return lista;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DbConn.closeResultSet(rs);
			DbConn.closerStatement(st);
		}
	}

	private Task newTask(ResultSet rs, User user) throws SQLException {
		Task obj = new Task();
		obj.setId(rs.getInt("id"));
		obj.setTitulo(rs.getString("title"));
		obj.setDescricao(rs.getString("description"));
		obj.setStatus(rs.getString("status"));
		obj.setDataEntrega(rs.getDate("data_entrega"));
		obj.setUser(user);
		return obj;
	}

	private User newUser(ResultSet rs) throws SQLException {
		User obj = new User();
		obj.setId(rs.getInt("user_id"));
		obj.setName(rs.getString("user_name"));
		obj.setEmail(rs.getString("user_email"));
		return obj;
	}
}
