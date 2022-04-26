package fr.fms.entities;

public class Administrator extends User {

	private String name;

	public Administrator(int id, String email, String password, String name) {
		super(id, email, password);
		setName(name);
	}

	@Override
	public String toString() {
		return super.toString() + "Administrator [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
