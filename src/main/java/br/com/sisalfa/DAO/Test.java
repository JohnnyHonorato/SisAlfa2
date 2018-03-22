package br.com.sisalfa.DAO;

import java.sql.Connection;

public class Test {

	protected static boolean isConected(Connection conection) {
		if (conection != null)
			return true;
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		UserDAOJDBC a = new UserDAOJDBC();
		try {
		
			System.out.println(a.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}




}
