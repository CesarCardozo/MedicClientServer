package model.util;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import model.entity.Appointment;
import model.entity.Doctor;
import model.entity.Patient;
import structure.TreeAvl;

public class JSonUtil {

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static Patient toPatient(String patientJson) {
		return new Gson().fromJson(patientJson, Patient.class);
	}

	public static Doctor toDoctor(String doctorJson) {
		return new Gson().fromJson(doctorJson, Doctor.class);
	}

	public static Date toDate(String dateJson) {
		return new Gson().fromJson(dateJson, Date.class);
	}

	public static Appointment toAppointment(String appointmentJson) {
		return new Gson().fromJson(appointmentJson, Appointment.class);

	}

	public static TreeAvl<Doctor> toDoctors(String array) {
		TreeAvl<Doctor> doctors = new TreeAvl<Doctor>();
		JsonArray jsonArray = new Gson().fromJson(array, JsonArray.class).getAsJsonArray();
		for (JsonElement jsonElement : jsonArray) {
//			JsonArray jsonArrayAppointments = jsonElement.getAsJsonObject().get("appointmentList").getAsJsonArray();
//			ArrayList<Appointment> appointments = new ArrayList<Appointment>();
//			for (JsonElement appointmentJsonElement : jsonArrayAppointments) {
//				appointments.add(new Gson().fromJson(jsonElement, Appointment.class));
//			}
			Doctor d = new Gson().fromJson(jsonElement, Doctor.class);
			try {
				doctors.insert(d);
			} catch (Exception e) {
			}
		}
		return doctors;
	}
	
	public static TreeAvl<Patient> toPatients(String array) {
		TreeAvl<Patient> patients = new TreeAvl<Patient>();
		JsonArray jsonArray = new Gson().fromJson(array, JsonArray.class).getAsJsonArray();
		for (JsonElement jsonElement : jsonArray) {
			Patient p = new Gson().fromJson(jsonElement, Patient.class);
			try {
				patients.insert(p);
			} catch (Exception e) {
			}
		}
		return patients;
	}
}
