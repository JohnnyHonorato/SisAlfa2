package br.com.sisalfa.model;

public class Challenge {
	private int id;
	private int idUser;
	private int idContext;
	private String name;
	private byte[] image;

	public Challenge(int idUser, int idContext, String name) {
		this.idUser = idUser;
		this.idContext = idContext;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdContext() {
		return idContext;
	}

	public void setIdContext(int idContext) {
		this.idContext = idContext;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}