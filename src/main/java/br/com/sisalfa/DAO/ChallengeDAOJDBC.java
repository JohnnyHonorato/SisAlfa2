package br.com.sisalfa.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sisalfa.model.Challenge;

public class ChallengeDAOJDBC extends MySQLConection implements ChallengeDAO {

	public void insert(Challenge challenge) throws SQLException {
		String INSERT_SQL = "insert into challenge(id_user, id_context, name, photo) values(?, ?, ?, ?)";
		try {
			super.connect();
			PreparedStatement psmtm = connection.prepareStatement(INSERT_SQL);
			psmtm.setInt(1, challenge.getIdUser());
			psmtm.setInt(2, challenge.getIdContext());
			psmtm.setString(3, challenge.getName());
			psmtm.setBytes(4, challenge.getImage());
			int rows = psmtm.executeUpdate();
			if (rows > 0)
				super.commit();
			else {
				super.rollback();
				throw new SQLException("Erro Desafio nao inserido!");
			}
			super.close();
		} catch (ClassNotFoundException e) {
			super.close();
			e.printStackTrace();
		}
	}

	public Challenge getById(int id) throws SQLException {
		List<Challenge> challenges = this.getAll();
		for (Challenge challenge : challenges) {
			if (challenge.getId() == id)
				return challenge;
		}
		return null;
	}

	public List<Challenge> getAll() throws SQLException {
		String SELECT_SQL = "select * from challenge;";
		try {
			super.connect();
			List<Challenge> challenges = new ArrayList<Challenge>();
			Challenge challenge = null;
			Statement psmtm = connection.createStatement();
			ResultSet resultset = psmtm.executeQuery(SELECT_SQL);
			while (resultset.next()) {
				challenge = new Challenge(resultset.getInt("id_user"), resultset.getInt("id_context"),
						resultset.getString("name"));
				challenge.setId(resultset.getInt("id"));
				challenge.setImage(resultset.getBytes("photo"));
				challenges.add(challenge);
			}
			super.close();
			return challenges;
		} catch (ClassNotFoundException e) {
			super.close();
			e.printStackTrace();
		}
		return null;
	}

	public List<Challenge> getByUserId(int id) throws SQLException {
		List<Challenge> allChallenges = this.getAll();
		List<Challenge> challenges = new ArrayList<Challenge>();
		for (Challenge challenge : allChallenges) {
			if (challenge.getIdUser() == id)
				challenges.add(challenge);
		}
		return challenges;
	}

	public List<Challenge> getByContextId(int id) throws SQLException {
		List<Challenge> allChallenges = this.getAll();
		List<Challenge> challenges = new ArrayList<Challenge>();
		for (Challenge challenge : allChallenges) {
			if (challenge.getIdContext() == id)
				challenges.add(challenge);
		}
		return challenges;
	}

	public void delete(int id) throws SQLException {
		String DELETE_SQL = "delete from challenge where id = ?";
		try {
			super.connect();
			PreparedStatement psmtm = connection.prepareStatement(DELETE_SQL);
			psmtm.setInt(1, id);
			int rows = psmtm.executeUpdate();
			if (rows > 0)
				super.commit();
			else {
				super.rollback();
				throw new SQLException("Erro. Desafio nao deletado!");
			}
			super.close();
		} catch (ClassNotFoundException e) {
			super.close();
		}

	}

}