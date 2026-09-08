package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import model.dao.TaskDao;
import model.entities.Task;
import model.entities.User;

public class TaskJDBC implements TaskDao {

	private static Connection conn = null;
	public TaskJDBC(Connection conn) {
		this.conn=conn;;
	}
	@Override
	public void insert(Task obj) {

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
