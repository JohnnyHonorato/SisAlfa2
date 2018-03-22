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

import br.com.sisalfa.DAO.JDBCDAOFactory;
import br.com.sisalfa.DAO.UserDAOJDBC;
import br.com.sisalfa.model.User;

@Path("user")
public class UserService {

	private JDBCDAOFactory factory = new JDBCDAOFactory();
	private UserDAOJDBC userBD = factory.createUserDAO();
	private Gson gson = new Gson();

	@GET
	@Path("getAllUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsers() {
		try {
			String users = gson.toJson(userBD.getAll());
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("getUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@PathParam("id") int id) {
		try {
			String user = gson.toJson(userBD.getById(id));
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("addUser")
	public Response addUser(User user) {
		userBD.insert(user);
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Path("deleteUser/{id}")
	public Response deleteUser(@PathParam("id") int id) {
		try {
			userBD.delete(id);
			return Response.status(Status.OK).build();
		} catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	// Usar o Path ou Query ?
}
