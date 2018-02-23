package br.com.sisalfa.DAO;

import java.sql.SQLException;
import java.util.List;

import br.com.sisalfa.model.Context;

public interface ContextDAO {
	
	public void insert(Context context) throws SQLException;
	public Context getById(int id) throws SQLException;
	public List<Context> getAll() throws SQLException;
	public List<Context> getByUserId(int id) throws SQLException;
	public void delete(int id) throws SQLException;
	
}