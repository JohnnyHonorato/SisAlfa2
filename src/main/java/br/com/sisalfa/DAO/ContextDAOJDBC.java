package br.com.sisalfa.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sisalfa.model.Context;

public class ContextDAOJDBC extends MySQLConection implements ContextDAO {

	public void insert(Context context) throws SQLException {
		String INSERT_SQL = "insert into context(name, description, id_user) values(?, ?, ?)";
		try {
			super.connect();
			PreparedStatement psmtm = connection.prepareStatement(INSERT_SQL);
			psmtm.setString(1, context.getName());
			psmtm.setString(2, context.getDescription());
			psmtm.setInt(3, context.getIdUser());
			int rows = psmtm.executeUpdate();
			if (rows > 0)
				super.commit();
			else {
				super.rollback();
				throw new SQLException("Erro. Contexto nao inserido!");

			}
			super.close();
		} catch (ClassNotFoundException e) {
			super.close();
			e.printStackTrace();
		}

	}

	public Context getById(int id) throws SQLException {
		List<Context> contexts = this.getAll();
		for (Context context : contexts) {
			if (context.getId() == id)
				return context;
		}
		return null;
	}

	public List<Context> getAll() throws SQLException {
		String SELECT_SQL = "select * from context;";
		try {
			super.connect();
			List<Context> contexts = new ArrayList<Context>();
			Context context = null;
			Statement psmtm = connection.createStatement();
			ResultSet resultset = psmtm.executeQuery(SELECT_SQL);
			while (resultset.next()) {
				context = new Context();
				context.setId(resultset.getInt("id"));
				context.setName(resultset.getString("name"));
				context.setDescription(resultset.getString("description"));
				context.setIdUser(resultset.getInt("id_user"));
				contexts.add(context);
			}
			super.close();
			return contexts;
		} catch (ClassNotFoundException e) {
			super.close();
			e.printStackTrace();
		}
		return null;
	}

	public List<Context> getByUserId(int id) throws SQLException {
		List<Context> allContexts = this.getAll();
		List<Context> contexts = new ArrayList<Context>();
		for (Context context : allContexts) {
			if (context.getIdUser() == id)
				contexts.add(context);
		}
		return contexts;
	}

	public void delete(int id) throws SQLException {
		String DELETE_SQL = "delete from context where id = ?";
		try {
			super.connect();
			PreparedStatement psmtm = connection.prepareStatement(DELETE_SQL);
			psmtm.setInt(1, id);
			int rows = psmtm.executeUpdate();
			if (rows > 0)
				super.commit();
			else {
				super.rollback();
				throw new SQLException("Erro. Contexto nao deletado!");
			}
			super.close();
		} catch (ClassNotFoundException e) {
			super.close();
		}
	}

}
