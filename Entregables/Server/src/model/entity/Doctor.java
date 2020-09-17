package model.entity;

import structure.TreeAvl;

public class Doctor extends Person {

	private MedicalSpeciality speciality;
	private TreeAvl<Appointment> appointmentList;

	public Doctor(String id, String name, String phone, String email, MedicalSpeciality speciality, String password) {
		super(id, name, phone, email, password);
		this.speciality = speciality;
		this.appointmentList = new TreeAvl<>();
	}

	public void addAppointment(Appointment appointment) throws Exception {
		this.appointmentList.insert(appointment);
	}

	public Doctor(String id) {
		super(id);
	}

	public MedicalSpeciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(MedicalSpeciality speciality) {
		this.speciality = speciality;
	}

	/**
	 * @return the appointmentList
	 */
	public TreeAvl<Appointment> getAppointmentList() {
		return appointmentList;
	}

	/**
	 * @param appointmentList the appointmentList to set
	 */
	public void setAppointmentList(TreeAvl<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}

	@Override
	public String toString() {
		return "Doctor [Id: " + getId() + " Name: " + getName() + " Phone: " + getPhone() + " Em@ail: " + getEmail()
				+ " Speciality:" + speciality + " ]";
	}
}
