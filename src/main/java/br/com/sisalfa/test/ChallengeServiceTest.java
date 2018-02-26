package br.com.sisalfa.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.sisalfa.controller.ChallengeService;


public class ChallengeServiceTest {

	ChallengeService challengeService;
	Entities entities;
	int STATUS_OK = 200;

	@Before
	public void setUp() {
		entities = new Entities();
		challengeService = new ChallengeService();
		challengeService.addChallenge(entities.getChallenge());
	}
	
	@Test
	public void testGetChallenges(){
		assertNotNull(challengeService.getChallenges());
	}
	
	@Test
	public void testGetChallenge(){
		assertNotNull(challengeService.getChallenge(1));
	}
	
	@Test
	public void testGetChallengeFromUser(){
		assertNotNull(challengeService.getChallengeFromUser(0));
	}
	
	@Test
	public void testGetChallengeFromContext() {
		assertNotNull(challengeService.getChallengeFromContext(0));
	}
	
	@Test
	public void testAddChallenge() {
		assertTrue(challengeService.addChallenge(entities.getChallenge()).getStatus() == STATUS_OK);
	}
	
	@Test
	public void testDeleteChallenge() {
		assertTrue(challengeService.deleteChallenge(1).getStatus() == STATUS_OK);
	}
}
