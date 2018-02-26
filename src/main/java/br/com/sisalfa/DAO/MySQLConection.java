package br.com.sisalfa.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConection {

	protected Connection conection = null;
	private String DRIVER;
	private String URL;
	private String USER;
	private String PASSWORD;

	public MySQLConection() {
		Property property = new Property();
		Properties prop;
		try {
			prop = property.getProp();
			DRIVER = prop.getProperty("prop.driver");
			URL = prop.getProperty("prop.url");
			USER = prop.getProperty("prop.user");
			PASSWORD = prop.getProperty("prop.password");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void connect() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		conection = DriverManager.getConnection(URL, USER, PASSWORD);
		conection.setAutoCommit(false);
		System.out.println("Conected!: " + conection);
	}

	protected void close() throws SQLException {
		if (conection != null) {
			conection.close();
			System.out.println("Close!: " + conection);
		} else {
			throw new SQLException("Conexao ja fechada");
		}
	}

	protected void commit() throws SQLException {
		if ((conection != null) && (conection.getAutoCommit() == false)) {
			conection.commit();
			System.out.println("Commit!: " + conection);
		} else {
			throw new SQLException("Impossivel commitar dados");
		}
	}

	protected void rollback() throws SQLException {
		if ((conection != null) && (conection.getAutoCommit() == false)) {
			conection.rollback();
			System.out.println("RollingBack!: " + conection);
		} else {
			throw new SQLException("Impossovel desfazer alteracoes");
		}
	}

	protected boolean isConected() {
		if (this.conection != null)
			return true;
		return false;
	}
}
