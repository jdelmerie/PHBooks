package fr.fms.entities;

public class Customer extends User {

	private String firstname;
	private String lastname;
	private String address;
	private String phone;

	public Customer(int id, String email, String password, String firstname, String lastname, String address,
			String phone) {
		super(id, email, password);
		setFirstname(firstname);
		setLastname(lastname);
		setAddress(address);
		setPhone(phone);
	}

	public Customer(int id, String email, String password, String firstname, String lastname, String address) {
		super(id, email, password);
		setFirstname(firstname);
		setLastname(lastname);
		setAddress(address);
	}

	@Override
	public String toString() {
		return super.toString() + "Customer [firstname=" + firstname + ", lastname=" + lastname + ", address=" + address
				+ ", phone=" + phone + "]";
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
