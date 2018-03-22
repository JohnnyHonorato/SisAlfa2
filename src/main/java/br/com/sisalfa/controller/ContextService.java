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

import br.com.sisalfa.DAO.ContextDAOJDBC;
import br.com.sisalfa.DAO.JDBCDAOFactory;
import br.com.sisalfa.model.Context;

@Path("context")
public class ContextService {

	private JDBCDAOFactory factory = new JDBCDAOFactory();
	private ContextDAOJDBC contextBD = factory.createContextDAO();
	private Gson gson = new Gson();

	@GET
	@Path("getAllContexts")
	@Produces(MediaType.APPLICATION_JSON)
	public String getContexts() {
		try {
			String contexts = gson.toJson(contextBD.getAll());
			return contexts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("getContext/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getContext(@PathParam("id") int id) {
		try {
			String context = gson.toJson(contextBD.getById(id));
			return context;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("getContextsFromUser/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getContextFromUser(@PathParam("idUser") int id) {
		try {
			String contexts = gson.toJson(contextBD.getByUserId(id));
			return contexts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("addContext")
	public Response addContext(Context context) {
		try {
			contextBD.insert(context);
			return Response.status(Status.OK).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("deleteContext/{id}")
	public Response deleteContext(@PathParam("id") int id) {
		try {
			contextBD.delete(id);
			return Response.status(Status.OK).build();
		} catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}