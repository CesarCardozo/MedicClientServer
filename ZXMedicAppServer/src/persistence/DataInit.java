package persistence;

import java.util.Date;

import com.google.gson.Gson;

import model.entity.Appointment;
import model.entity.Doctor;
import model.entity.MedicalSpeciality;
import model.entity.Patient;
import structure.TreeAvl;

public class DataInit {
	
	private TreeAvl<Patient> patientAvl;
	private TreeAvl<Doctor> doctorAvl;
	
	private String patiensListString, doctorsListString;
	
	public DataInit() {
		this.doctorAvl = new TreeAvl<Doctor>();
		this.patientAvl = new TreeAvl<Patient>();
	}
	
	@SuppressWarnings("deprecation")
	public void fillDataInit() {
		Doctor doctor1 = new Doctor("100", "Chapatin", "311998", "chapatin@uptc.eps.com", MedicalSpeciality.CARDIOLOGIST, "123");
		Doctor doctor2 = new Doctor("101", "House", "3120090", "house@uptc.eps.com", MedicalSpeciality.PHYSIATRIST,"123");
		Doctor doctor3 = new Doctor("102", "Dolittle", "3012222", "dolittle@uptc.eps.com", MedicalSpeciality.ORTHOPEDIST,"123");
		
		Patient patient1 = new Patient("10496000", "Erika", "300344", "erika@uptc.eps.com", "Toma aspirina 100 todos los dias");
		Patient patient2 = new Patient("10506000", "Laura", "300344", "laura@uptc.eps.com", "Alergica al peroxido");
		Patient patient3 = new Patient("10516000", "Paola", "300344", "paola@uptc.eps.com", "En embarazo");
		Patient patient4 = new Patient("10526000", "Liliana", "300344", "liliana@uptc.eps.com", "Toma aspirina 100 todos los dias");
		
		
		Appointment appointment= new Appointment(new Date(2020, 10, 1));
		Appointment appointment2= new Appointment(new Date(2020, 10, 5));
		Appointment appointment3= new Appointment(new Date(2020, 10, 10));
		Appointment appointment4= new Appointment(new Date(2020, 10, 15));
		
		try {
			this.doctorAvl.insert(doctor1);
			this.doctorAvl.insert(doctor2);
			this.doctorAvl.insert(doctor3);
			
			this.patientAvl.insert(patient1);
			this.patientAvl.insert(patient2);
			this.patientAvl.insert(patient3);
			this.patientAvl.insert(patient4);
			
			doctor1.addAppointment(appointment);
			doctor1.addAppointment(appointment2);
			doctor2.addAppointment(appointment3);
			doctor3.addAppointment(appointment4);
			
			this.doctorsListString = new Gson().toJson(this.doctorAvl);
			this.patiensListString = new Gson().toJson(this.patientAvl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TreeAvl<Patient> getPatientAvl() {
		return patientAvl;
	}

	public TreeAvl<Doctor> getDoctorAvl() {
		return doctorAvl;
	}

	public String getPatiensListString() {
		return patiensListString;
	}

	public String getDoctorsListString() {
		return doctorsListString;
	}
}