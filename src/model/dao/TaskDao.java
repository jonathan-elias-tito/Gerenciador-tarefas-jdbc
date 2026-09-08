package model.dao;

import java.util.List;
import model.entities.Task;
import model.entities.User;

public interface TaskDao {
	void insert(Task obj);
	void update (Task obj);
	void deleteById(Integer Id);
	List<Task>findAll();
	List<Task>findById(Task Id);
	List<Task>findByUser(User user);
	
}
