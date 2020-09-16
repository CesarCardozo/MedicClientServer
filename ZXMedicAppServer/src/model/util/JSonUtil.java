package model.util;

import java.util.Date;

import com.google.gson.Gson;

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

	public static Date toDate(String dateJson) {
		return new Gson().fromJson(dateJson, Date.class);
	}

}
