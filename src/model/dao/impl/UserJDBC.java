package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbConn;
import db.DbException;
import model.dao.UserDao;
import model.entities.User;

public class UserJDBC implements UserDao {
	private Connection conn = null;

	public UserJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(User obj) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("INSERT INTO user (name, email) " + "VALUES (?,?) ",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
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
			throw new DbException("Error:" + e.getMessage());
		} finally {
			DbConn.closeResultSet(rs);
			DbConn.closerStatement(st);
		}
	}

	@Override
	public void update(User obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE User " + "SET name = ? , email = ? " + "WHERE Id = ? ");
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setInt(3, obj.getId());
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
			st = conn.prepareStatement("DELETE FROM User  " + "WHERE Id = ? ");
			st.setInt(1, Id);
			int rows = st.executeUpdate();
			if (rows == 0) {
				throw new DbException("Delete failed: ID not found.");
			}
		} catch (SQLException e) {
			throw new DbException("Error on delete" + e.getMessage());
		} finally {
			DbConn.closerStatement(st);
		}
	}

	@Override
	public User findById(Integer Id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM User WHERE Id = ? ");
			st.setInt(1, Id);
			rs = st.executeQuery();
			if (rs.next()) {
				User obj = newUser(rs);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException("Error in connection:" + e.getMessage());
		} finally {
			DbConn.closeResultSet(rs);
			DbConn.closerStatement(st);
		}
	}

	@Override
	public List<User> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM User");
			rs = st.executeQuery();
			List<User> lista = new ArrayList<>();
			while (rs.next()) {

				User obj = newUser(rs);
				lista.add(obj);
			}
			return lista;
		} catch (SQLException e) {
			throw new DbException("Error in connection:" + e.getMessage());
		} finally {
			DbConn.closeResultSet(rs);
			DbConn.closerStatement(st);
		}
	}

	private User newUser(ResultSet rs) throws SQLException {
		User obj = new User();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setEmail(rs.getString("email"));
		return obj;

	};

}
