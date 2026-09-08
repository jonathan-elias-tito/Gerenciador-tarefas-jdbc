package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DbConn;
import db.DbException;
import model.dao.UserDao;
import model.entities.User;

public class UserJDBC implements UserDao {
	private static Connection conn = null;

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
				rs.close();

			}

		} catch (SQLException e) {
			throw new DbException("Erro causa:" + e.getMessage());
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(User obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
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
			throw new DbException("Erroe:" + e.getMessage());
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException("Error closing statement:"+e.getMessage());
			}
		}
	}

	@Override
	public void deleteById(Integer Id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findById(Integer Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
