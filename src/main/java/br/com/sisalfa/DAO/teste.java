package br.com.sisalfa.DAO;

import java.io.IOException;
import java.sql.SQLException;

public class teste {
	public static void main(String[]args) throws ClassNotFoundException, SQLException, IOException {
		UserDAOJDBC a = new UserDAOJDBC();
		a.connect();
		System.out.println(a.isConected());

	}
}
