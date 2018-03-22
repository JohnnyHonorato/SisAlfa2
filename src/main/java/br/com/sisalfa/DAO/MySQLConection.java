package br.com.sisalfa.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public final class MySQLConection{

	private String DRIVER;
	private String URL;
	private String USER;
	private String PASSWORD;
	
	private static MySQLConection instance = null;
	
	private MySQLConection() {
		
	}
	
	public static MySQLConection getInstance() {
		if (instance == null) 
			instance = new MySQLConection();
		return instance;
	}

	protected void loadData() throws IOException {
		Property property = new Property();
		Properties prop;
		prop = property.getProp();
		DRIVER = prop.getProperty("prop.driver");
		URL = prop.getProperty("prop.url");
		USER = prop.getProperty("prop.user");
		PASSWORD = prop.getProperty("prop.password");
	}

	public Connection abriConexao() throws Exception{
		loadData();
		System.out.println(URL);
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}
