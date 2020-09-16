package model.entity;


public class Patient  extends Person{

	private String histoy;
	
	public Patient(String id, String name,  String phone, String email, String password) {
		super(id, name,phone, email,password );
	}
	public Patient(String id, String name,  String phone, String email, String password, String history) {
		super(id, name,phone, email, password );
		this.histoy = history;
	}

	public Patient(String id) {
		super(id);
	}

	public String getHistoy() {
		return histoy;
	}

	public void setHistoy(String histoy) {
		this.histoy = histoy;
	}

	@Override
	public String toString() {
		return "Patient [Id: " + getId() + " Name: " + getName() + " Phone: " + getPhone() +" Em@ail: "+ getEmail() +" Histoy: "+ getHistoy() + " ]";
	}

}
