package br.com.sisalfa.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import br.com.sisalfa.controller.UserService;
import br.com.sisalfa.model.User;

public class UserServiceTest {

	EntitiesForTests entities;
	UserService userService;
	int STATUS_OK = 200;

	@Before
	public void setUp() {
		entities = new EntitiesForTests();
		userService = new UserService();
		userService.addUser(entities.getUser());
	}

	@Test
	public void testGetUsers() {
		assertTrue(userService.getUsers().length() > 0);
	}

	@Test
	public void testGetUser() {
		assertNotNull(userService.getUser(1)); // QUERO FAZER UM CONSTANTE PARA O NUMERO 1 'E INDICADO?
	}

	@Test
	public void testAddUser() {
		assertTrue(userService.addUser(entities.getUser()).getStatus() == STATUS_OK);
	}
	
	@Test
	public void testDeleteUser() {
		assertTrue(userService.deleteUser(1).getStatus() == STATUS_OK);
	}
}
