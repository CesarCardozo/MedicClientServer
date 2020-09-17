package model.entity;

import structure.Comparador;

public class Person implements Comparador {

	private String id, name;
	private String phone;
	private String email;
	private String password;

	public Person(String id, String name, String phone, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public Person(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

	@Override
	public boolean equalsTo(Object q) {
		Person person = (Person) q;
		return this.id.equals(person.getId());
	}

	@Override
	public boolean lessThan(Object q) {
		Person person = (Person) q;
		return this.id.compareTo(person.getId()) < 0;
	}

	@Override
	public boolean lessEqualsTo(Object q) {
		Person person = (Person) q;
		return this.id.compareTo(person.getId()) <= 0;
	}

	@Override
	public boolean greaterThan(Object q) {
		Person person = (Person) q;
		return this.id.compareTo(person.getId()) > 0;
	}

	@Override
	public boolean greaterEqualsT(Object q) {
		Person person = (Person) q;
		return this.id.compareTo(person.getId()) >= 0;
	}
}
