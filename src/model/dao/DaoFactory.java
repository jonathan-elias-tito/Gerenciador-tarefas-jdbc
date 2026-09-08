package model.dao;

import db.DbConn;
import model.dao.impl.TaskJDBC;
import model.dao.impl.UserJDBC;

public class DaoFactory {
	public UseJDBC createUserDao() {
		return new UserJDBC(DbConn.initConection());
	}

	public TaskJDBC createTaskDao() {
		return new TaskJDBC(DbConn.initConection());
	}
}
