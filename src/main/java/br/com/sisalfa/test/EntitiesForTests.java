package br.com.sisalfa.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.sisalfa.model.Challenge;
import br.com.sisalfa.model.Context;
import br.com.sisalfa.model.User;

public class EntitiesForTests {

	private User user;
	private Context context;
	private Challenge challenge;

	public EntitiesForTests() {
		user = new User();
		user.setName("Johnny");
		context = new Context("Contexto1", user.getId());
		challenge = new Challenge(user.getId(), context.getId(), "Desafio1");
		challenge.setImage(returnImagem());
	}

	public User getUser() {
		return user;
	}

	public Context getContext() {
		return context;
	}
	
	public Challenge getChallenge() {
		return challenge;
	}
	
	private byte[] returnImagem() {
		byte[] img;
		FileInputStream in;
		File file = new File("/home/johnny/eclipse-workspace/sisalfa/src/test/GonGI.jpg"); 		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			in = new FileInputStream(file);
			int b;
			while ((b = in.read()) > -1) {
				out.write(b);
			}
			out.close();
			in.close();
			img = out.toByteArray();
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
