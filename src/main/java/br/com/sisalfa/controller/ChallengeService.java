package br.com.sisalfa.controller;

import java.sql.SQLException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import br.com.sisalfa.DAO.ChallengeDAOJDBC;
import br.com.sisalfa.model.Challenge;


@Path("challenge")
public class ChallengeService {

	private ChallengeDAOJDBC challengeBD = new ChallengeDAOJDBC();
	private Gson gson = new Gson();

	@GET
	@Path("getAllChallenges")
	@Produces(MediaType.APPLICATION_JSON)
	public String getChallenges() {
		try {
			String challenges = gson.toJson(challengeBD.getAll());
			return challenges;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("getChallenge/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getChallenge(@PathParam("id") int id) {
		try {
			String challenge = gson.toJson(challengeBD.getById(id));
			return challenge;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("getChallengeFromUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getChallengeFromUser(@PathParam("id") int id) {
		try {

			String challenges = gson.toJson(challengeBD.getByUserId(id));
			return challenges;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("getChallengeFromContext/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getChallengeFromContext(@PathParam("id") int id) {
		try {

			String challenges = gson.toJson(challengeBD.getByContextId(id));
			return challenges;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("addChallenge")
	public Response addChallenge(Challenge challenge) {
		try {

			challengeBD.insert(challenge);

			return Response.status(Status.OK).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("deleteChallenge/{id}")
	public Response deleteChallenge(@PathParam("id") int id) {
		try {
			challengeBD.delete(id);
			return Response.status(Status.OK).build();
		} catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}