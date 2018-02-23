package br.com.sisalfa.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sisalfa.model.User;

public class UserDAOJDBC extends MySQLConection implements UserDAO {

	public void insert(User user) throws SQLException {
		String INSERT_SQL = "insert into user(name) values(?)";
		try {
			super.connect();
			PreparedStatement psmtm = connection.prepareStatement(INSERT_SQL);
			psmtm.setString(1, user.getName());
			int rows = psmtm.executeUpdate();
			if (rows > 0)
				super.commit();
			else {
				super.rollback();
				throw new SQLException("Erro. Usuario nao Cadastrado!");
			}
			super.close();
		} catch (ClassNotFoundException e) {
			super.close();
			e.printStackTrace();
		}

	}

	public User getById(int id) throws SQLException {
		List<User> users = this.getAll();
		for (User user : users) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}

	public List<User> getAll() throws SQLException {
		String SELECT_SQL = "select * from user;";
		try {
			super.connect();
			List<User> users = new ArrayList<User>();
			User user = null;
			Statement psmtm = connection.createStatement();
			ResultSet resultset = psmtm.executeQuery(SELECT_SQL);
			while (resultset.next()) {
				user = new User();
				user.setId(resultset.getInt("id"));
				user.setName(resultset.getString("name"));
				users.add(user);
			}
			super.close();
			return users;
		} catch (ClassNotFoundException e) {
			super.close();
			e.printStackTrace();
		}
		return null;
	}

	public void delete(int id) throws SQLException {
		String DELETE_SQL = "delete from user where id = ?";
		try {
			super.connect();
			PreparedStatement psmtm = connection.prepareStatement(DELETE_SQL);
			psmtm.setInt(1, id);
			int rows = psmtm.executeUpdate();
			if (rows > 0)
				super.commit();
			else {
				super.rollback();
				throw new SQLException("Erro. Usuario nao deletado!");
			}
			super.close();
		} catch (ClassNotFoundException e) {
			super.close();
		}

	}

}