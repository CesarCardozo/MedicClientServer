package model.util;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import model.entity.Appointment;
import model.entity.Doctor;
import model.entity.Patient;

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
	
	public static ArrayList<Appointment> toArrayAppoints(String array) {
		ArrayList<Appointment> appoints = new ArrayList<Appointment>();
		JsonArray jsonArray = new Gson().fromJson(array, JsonArray.class).getAsJsonArray();
		for (JsonElement jsonElement : jsonArray) {
			appoints.add(new Gson().fromJson(jsonElement, Appointment.class));
		}
		return appoints;
	}
}
