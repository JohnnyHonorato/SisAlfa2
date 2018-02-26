package br.com.sisalfa.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
	
	public Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("/home/johnny/eclipse-workspace/sisalfa/src/properties/dados.properties"); 
		props.load(file);
		return props;
	}
}
