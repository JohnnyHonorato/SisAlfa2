package br.com.sisalfa.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConection {

	protected Connection connection = null;
	Properties props = new Properties();
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/sisalfa";
	private final String USER = "root";
	private final String PASSWORD = "1234";


	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
		connection.setAutoCommit(false);
		System.out.println("Conected!: " + connection);
	}

	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
			System.out.println("Close!: " + connection);
		} else {
			throw new SQLException("Conexao ja fechada");
		}
	}

	public void commit() throws SQLException {
		if ((connection != null) && (connection.getAutoCommit() == false)) {
			connection.commit();
			System.out.println("Commit!: " + connection);
		} else {
			throw new SQLException("Impossivel commitar dados");
		}
	}

	public void rollback() throws SQLException {
		if ((connection != null) && (connection.getAutoCommit() == false)) {
			connection.rollback();
			System.out.println("RollingBack!: " + connection);
		} else {
			throw new SQLException("Impossovel desfazer alteracoes");
		}
	}

	public boolean isConected() {
		if (this.connection != null)
			return true;
		return false;
	}
}
