package br.com.sisalfa.DAO;

import java.sql.SQLException;
import java.util.List;

import br.com.sisalfa.model.Challenge;

public interface ChallengeDAO {
	
	public void insert(Challenge challenge) throws SQLException;
	public Challenge getById(int id) throws SQLException;
	public List<Challenge> getAll() throws SQLException;
	public List<Challenge> getByUserId(int id) throws SQLException;
	public List<Challenge> getByContextId(int id) throws SQLException;
	public void delete(int id) throws SQLException;
}