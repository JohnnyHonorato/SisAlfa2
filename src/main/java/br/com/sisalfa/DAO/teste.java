package br.com.sisalfa.DAO;

import java.sql.SQLException;

import br.com.sisalfa.model.User;

public class teste {
	public static void main(String[]args) throws ClassNotFoundException, SQLException {
		UserDAOJDBC a = new UserDAOJDBC();
		User user = new User();
		user.setName("dsadsad");
		a.insert(user);
	}
}
