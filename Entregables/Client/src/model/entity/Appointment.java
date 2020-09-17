package model.entity;

import java.util.Date;

import structure.Comparador;

public class Appointment implements Comparador{
	
	private Date date;
	private AppointmentStatus status;
	private Patient patient;
	private String doctor;

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public Appointment(Date date) {
		this.date = date;
		this.status = AppointmentStatus.AVAILABLE;
	}

	public Date getDate() {
		return date;
	}

	public void setHour(Date date) {
		this.date = date;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Date: " + date + (patient!=null?("| Patient: "+patient):"");
	}

	@Override
	public boolean equalsTo(Object q) {
		Appointment appointment = (Appointment) q;
		return this.date.equals(appointment.getDate());
	}

	@Override
	public boolean lessThan(Object q) {
		Appointment appointment = (Appointment) q;
		return this.date.compareTo(appointment.getDate()) <0;
	}

	@Override
	public boolean lessEqualsTo(Object q) {
		Appointment appointment = (Appointment) q;
		return this.date.compareTo(appointment.getDate()) <=0;
	}

	@Override
	public boolean greaterThan(Object q) {
		Appointment appointment = (Appointment) q;
		return this.date.compareTo(appointment.getDate()) >0;
	}

	@Override
	public boolean greaterEqualsT(Object q) {
		Appointment appointment = (Appointment) q;
		return this.date.compareTo(appointment.getDate()) >=0;
	}
}