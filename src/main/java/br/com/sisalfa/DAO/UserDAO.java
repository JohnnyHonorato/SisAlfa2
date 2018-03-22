package br.com.sisalfa.DAO;

import java.sql.SQLException;
import java.util.List;
import br.com.sisalfa.model.User;

public interface UserDAO {
	
	public void insert(User user);

	public User getById(int id) throws SQLException;

	public List<User> getAll() throws SQLException;

	public void delete(int id) throws SQLException;
}
