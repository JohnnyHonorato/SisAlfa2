package br.com.sisalfa.DAO;

public interface DAOFactory {
	public UserDAO createUserDAO();
	public ChallengeDAO createChallengeDAO();
	public ContextDAO createContextDAO();
}
