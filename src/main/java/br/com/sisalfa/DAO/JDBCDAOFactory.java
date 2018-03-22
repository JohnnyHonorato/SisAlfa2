package br.com.sisalfa.DAO;

public class JDBCDAOFactory implements DAOFactory {

	@Override
	public UserDAOJDBC createUserDAO() {
		return new UserDAOJDBC();
	}

	@Override
	public ChallengeDAOJDBC createChallengeDAO() {
		return new ChallengeDAOJDBC();
	}

	@Override
	public ContextDAOJDBC createContextDAO() {
		return new ContextDAOJDBC();
	}

}
