package br.com.sisalfa.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.sisalfa.controller.ContextService;

public class ContextServiceTest {

	ContextService contextService;
	Entities entities; 
	int STATUS_OK = 200;

	@Before
	public void setUp() {
		entities = new Entities();
		contextService = new ContextService();
		contextService.addContext(entities.getContext());
	}

	@Test
	public void testGetContexts() {
		assertTrue(contextService.getContexts().length() > 0);
	}

	@Test
	public void testGetContext() {
		assertNotNull(contextService.getContext(1));
	}
	
	@Test
	public void testGetContextFromUser() {
		assertNotNull(contextService.getContextFromUser(0));
	}
	
	@Test
	public void testAddContext() {
		assertTrue(contextService.addContext(entities.getContext()).getStatus() == STATUS_OK);
	}
	
	@Test
	public void testDeleteContext() {
		assertTrue(contextService.deleteContext(1).getStatus() == STATUS_OK);
	}
}