package br.com.sisalfa.controller;


import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("hello")
public class HelloController {
	
	@GET
	public String getMsg() {
		return "Bem vindo";
	}
}
