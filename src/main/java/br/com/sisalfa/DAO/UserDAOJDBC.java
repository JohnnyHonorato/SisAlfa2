package br.com.sisalfa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sisalfa.model.User;

public class UserDAOJDBC implements UserDAO {

	
	public void insert(User user) {
		String insertSQL = "insert into user(name) values(?)";
		try {
			Connection conection = MySQLConection.getInstance().abriConexao();
			PreparedStatement psmtm = conection.prepareStatement(insertSQL);
			psmtm.setString(1, user.getName());
			int rows = psmtm.executeUpdate();
			if (rows > 0)
				conection.commit();
			else {
				conection.rollback();
				throw new SQLException("Erro. Usuario nao Cadastrado!");
			}
			conection.close();
		} catch (Exception e) {
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
		String selectSQL = "select * from user;";
		List<User> users = new ArrayList<User>();
		try {
			Connection conection = MySQLConection.getInstance().abriConexao();
			User user = null;
			Statement psmtm = conection.createStatement();
			ResultSet resultset = psmtm.executeQuery(selectSQL);
			while (resultset.next()) {
				user = new User();
				user.setId(resultset.getInt("id"));
				user.setName(resultset.getString("name"));
				users.add(user);
			}
			conection.close();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void delete(int id) throws SQLException {
		String deleteSQL = "delete from user where id = ?";
		try {
			Connection conection = MySQLConection.getInstance().abriConexao();
			PreparedStatement psmtm = conection.prepareStatement(deleteSQL);
			psmtm.setInt(1, id);
			int rows = psmtm.executeUpdate();
			if (rows > 0)
				conection.commit();
			else {
				conection.rollback();
				throw new SQLException("Erro. Usuario nao deletado!");
			}
			conection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}