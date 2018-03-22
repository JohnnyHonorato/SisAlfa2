package br.com.sisalfa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sisalfa.model.Challenge;

public class ChallengeDAOJDBC implements ChallengeDAO {


	public void insert(Challenge challenge) throws SQLException {
		String insertSQL = "insert into challenge(id_user, id_context, name, photo) values(?, ?, ?, ?)";
		try {
			Connection conection = MySQLConection.getInstance().abriConexao();
			PreparedStatement psmtm = conection.prepareStatement(insertSQL);
			psmtm.setInt(1, challenge.getIdUser());
			psmtm.setInt(2, challenge.getIdContext());
			psmtm.setString(3, challenge.getName());
			psmtm.setBytes(4, challenge.getImage());
			int rows = psmtm.executeUpdate();
			if (rows > 0)
				conection.commit();
			else {
				conection.rollback();
				throw new SQLException("Erro. Desafio nao inserido!");
			}
			conection.close();
		} catch (Exception e) {
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
		String selectSQL = "select * from challenge;";
		try {
			Connection conection = MySQLConection.getInstance().abriConexao();
			List<Challenge> challenges = new ArrayList<Challenge>();
			Challenge challenge = null;
			Statement psmtm = conection.createStatement();
			ResultSet resultset = psmtm.executeQuery(selectSQL);
			while (resultset.next()) {
				challenge = new Challenge(resultset.getInt("id_user"), resultset.getInt("id_context"),
						resultset.getString("name"));
				challenge.setId(resultset.getInt("id"));
				challenge.setImage(resultset.getBytes("photo"));
				challenges.add(challenge);
			}
			conection.close();
			return challenges;
		} catch (Exception e) {
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
		String deleteSQL = "delete from challenge where id = ?";
		try {
			Connection conection = MySQLConection.getInstance().abriConexao();
			PreparedStatement psmtm = conection.prepareStatement(deleteSQL);
			psmtm.setInt(1, id);
			int rows = psmtm.executeUpdate();
			if (rows > 0)
				conection.commit();
			else {
				conection.rollback();
				throw new SQLException("Erro. Desafio nao deletado!");
			}
			conection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}