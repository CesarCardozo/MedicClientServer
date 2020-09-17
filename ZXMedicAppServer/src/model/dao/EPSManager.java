package model.dao;

import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;

import model.entity.Appointment;
import model.entity.AppointmentStatus;
import model.entity.Doctor;
import model.entity.MedicalSpeciality;
import model.entity.Patient;
import model.exception.AlreadyExists;
import model.exception.IncorrectData;
import model.util.JSonUtil;
import structure.TreeAvl;

public class EPSManager {

	private TreeAvl<Patient> patientList;
	private TreeAvl<Doctor> doctortList;

	public EPSManager() {
		this.patientList = new TreeAvl<Patient>();
		this.doctortList = new TreeAvl<Doctor>();
	}

	/**
	 * crea una unica cita a una hora fecha al doctor determinado
	 * 
	 * @param doctor    a quien se le asigna
	 * @param date      unica fecha
	 * @param hourunica hora
	 * @throws AlreadyExists 
	 * @throws Exception
	 */
	public void createAppointment(Doctor doctor, Date date) throws AlreadyExists{
		Appointment a = new Appointment(date);
		Doctor d;
		try {
			d = doctortList.search(doctor).getInfo();
			a.setDoctor(d.getId());
			System.out.println(a.toString());
			d.addAppointment(a);
		} catch (Exception e) {
			throw new AlreadyExists("There is already an appointment in the selected date");
		}
		
	}

	/**
	 * metodo que elimina la cita del sistema (desde la perspectiva de un doctor)
	 * 
	 * @param appointment
	 * @param doctor
	 * @throws Exception
	 */
	public void deleteAppointment(Doctor doctor, Date date) throws Exception {
		Doctor d = this.doctortList.search(doctor).getInfo();
		Appointment a = new Appointment(date);
		d.getAppointmentList().Delete(d.getAppointmentList().search(a).getInfo());
	}

	/**
	 * metodo que cancela la cita en sistema (desde la perspectiva de un paciente)
	 * 
	 * @param appointment
	 * @param doctor
	 * @throws Exception
	 */
	public void cancelAppointment(Appointment a) throws Exception {
		Appointment appointment = doctortList.search(new Doctor(a.getDoctor())).getInfo().getAppointmentList().search(a).getInfo();
		appointment.setStatus(AppointmentStatus.AVAILABLE);
		appointment.setPatient(null);
	}

	/**
	 * metodo que agenda una cit en sistema (desde la perspectiva de un paciente)
	 * 
	 * @param patient
	 * @param doctor
	 * @param date
	 * @throws Exception
	 */
	public synchronized void bookAppointment(Patient patient, Appointment appointment) throws Exception {
		Appointment a = doctortList.search(new Doctor(appointment.getDoctor())).getInfo().getAppointmentList()
				.search(new Appointment(appointment.getDate())).getInfo();
		a.setPatient(patient);
		a.setStatus(AppointmentStatus.NOT_AVAILABLE);
	}

	/**
	 * metodo que muestra la lista de citas por paciente
	 * 
	 * @param idPAtient
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Appointment> showAppointementPatient(String idPAtient) throws Exception {
		Patient patient = this.patientList.search(new Patient(idPAtient)).getInfo();
		TreeAvl<Appointment> appointmentsPatient = new TreeAvl<Appointment>();
		ArrayList<Doctor> doctors = this.doctortList.inOrden();
		for (Doctor doctor : doctors) {
			ArrayList<Appointment> appointments = doctor.getAppointmentList().inOrden();
			for (Appointment appointment : appointments) {
				if (appointment.getDate()!=null&&appointment.getPatient().equals(patient)) {
					appointmentsPatient.insert(appointment);
				}
			}
		}
		return appointmentsPatient.inOrden();
	}

	/**
	 * metodo que muestra la lista de citas del paciente, segun estado de la cita
	 * 
	 * @param idPAtient
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Appointment> showAppointementPatient(String idPAtient, AppointmentStatus appointmentStatus)
			throws Exception {
		Patient patient = this.patientList.search(new Patient(idPAtient)).getInfo();
		TreeAvl<Appointment> appointmentsPatient = new TreeAvl<Appointment>();
		ArrayList<Doctor> doctors = this.doctortList.inOrden();
		for (Doctor doctor : doctors) {
			ArrayList<Appointment> appointments = doctor.getAppointmentList().inOrden();
			for (Appointment appointment : appointments) {
				if (appointment.getPatient()!=null && appointment.getPatient().getId().equals(patient.getId()) && appointment.getStatus().equals(appointmentStatus)) {
					appointmentsPatient.insert(appointment);
				}
			}
		}
		return appointmentsPatient.inOrden();
	}

	/**
	 * metodo que muestra la lista de citas por paciente
	 * 
	 * @param idDoctor
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Appointment> showAppointementDoctor(String idDoctor) throws Exception {
		Doctor doctor = this.doctortList.search(new Doctor(idDoctor)).getInfo();
		return doctor.getAppointmentList().inOrden();
	}

	/**
	 * metodo que muestra la lista de citas del doctor, segun estado de la cita
	 * 
	 * @param idDoctor
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Appointment> showAppointementDoctor(String idDoctor, AppointmentStatus appointmentStatus)
			throws Exception {
		ArrayList<Appointment> appointments = this.doctortList.search(new Doctor(idDoctor)).getInfo()
				.getAppointmentList().inOrden();
		TreeAvl<Appointment> appointmentsFromStatus = new TreeAvl<>();
		for (Appointment appointment : appointments) {
			if (appointment.getStatus().equals(appointmentStatus)) {
				appointmentsFromStatus.insert(appointment);
			}
		}
		return appointmentsFromStatus.inOrden();
	}

	/**
	 * metodo que trae las citas disponibles deacuerdo a especialidad medica
	 * 
	 * @param medicalSpeciality
	 * @return
	 */
	public ArrayList<Appointment> showAppointment(MedicalSpeciality medicalSpeciality) {
		ArrayList<Appointment> appointmentsFromSpeciality = new ArrayList<>();
		ArrayList<Doctor> doctors = this.doctortList.inOrden();
		for (Doctor doctor : doctors) {
			if (doctor.getSpeciality().equals(medicalSpeciality)) {
				ArrayList<Appointment> doctorsApointment = doctor.getAppointmentList().inOrden();
				for (Appointment appointment : doctorsApointment) {
					if (appointment.getStatus().equals(AppointmentStatus.AVAILABLE)) {
						appointmentsFromSpeciality.add(appointment);
					}
				}
			}
		}
		return appointmentsFromSpeciality;
	}

	/**
	 * metodo que permite cambiar de estado a una cita a atendida (desde la
	 * perspectiva de un doctor)
	 * 
	 * @param a
	 * @throws Exception
	 */
	public void attendAppointment(Appointment a) throws Exception {
		doctortList.search(new Doctor(a.getDoctor())).getInfo().getAppointmentList().search(a).getInfo()
				.setStatus(AppointmentStatus.ATTENDED);
	}

	/**
	 * metodo que permite crear un paciente
	 * 
	 * @param id
	 * @param name
	 * @param phone
	 * @param email
	 * @param history
	 * @throws Exception
	 */
	public void createPatient(String id, String name, String phone, String email, String history, String password)
			throws Exception {
		Patient p = new Patient(id, name, phone, email, password, history);
		this.addPattient(p);
	}

	public void createPatient(String patientJson) throws AlreadyExists {
		this.addPattient(JSonUtil.toPatient(patientJson));
	}

	/**
	 * metodo que permite crear un paciente
	 * 
	 * @param patient
	 * @throws Exception
	 */
	private void addPattient(Patient patient) throws AlreadyExists {
		try {
			this.patientList.insert(patient);
		} catch (Exception e) {
			throw new AlreadyExists("The patient already exists in the system");
		}
	}

	/**
	 * metodo que permite crear un doctor
	 * 
	 * @param id
	 * @param name
	 * @param phone
	 * @param email
	 * @param speciality
	 * @throws Exception
	 */
	public void createDoctor(String id, String name, String phone, String email, MedicalSpeciality speciality,
			String password) throws Exception {
		Doctor d = new Doctor(id, name, phone, email, speciality, password);
		this.addDoctor(d);
	}

	public void createDoctor(String doctorJson) throws AlreadyExists {
		try {
			this.addDoctor(JSonUtil.toDoctor(doctorJson));
		} catch (Exception e) {
			throw new AlreadyExists("The doctor already exists in the system");
		}
	}

	/**
	 * metodo que agrega un doctor a la lista de doctores
	 * 
	 * @param doctor
	 * @throws Exception
	 */
	private void addDoctor(Doctor doctor) throws Exception {
		this.doctortList.insert(doctor);
	}

	/**
	 * metodoq que obtiene un paciente deacuerdo al id provisto
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Patient searchPatient(String id) throws IncorrectData {
		Patient patient = new Patient(id);
		try {
			return this.patientList.search(patient).getInfo();
		} catch (Exception e) {
			throw new IncorrectData("Your credentials, username or password are incorrect");
		}
	}

	/**
	 * metodo que obtiene un doctor deacuerdo al id provisto
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Doctor searchDoctor(String id) throws IncorrectData {
		Doctor doctor = new Doctor(id);
		try {
			return this.doctortList.search(doctor).getInfo();
		} catch (Exception e) {
			throw new IncorrectData("Your credentials, username or password are incorrect");
		}
	}

	public TreeAvl<Patient> getPatientList() {
		return patientList;
	}

	public TreeAvl<Doctor> getDoctortList() {
		return doctortList;
	}

	// --------------------------------------------------------------------------

	/**
	 * metodo que retona todas las citas del doctor en un string tipo json
	 * 
	 * @param idDoctor
	 * @return
	 * @throws Exception
	 */
	public String getAppointDoctorJson(String idDoctor) throws Exception {
		String listAppointmentString = new Gson().toJson(showAppointementDoctor(idDoctor));
		return listAppointmentString;
	}

	/**
	 * metodo que retona todas las citas por status del doctor en un string tipo
	 * json
	 * 
	 * @param idDoctor
	 * @return
	 * @throws Exception
	 */
	public String getAppointDoctorJson(String idDoctor, AppointmentStatus appointmentStatus) throws Exception {
		String listAppointmentString = new Gson().toJson(showAppointementDoctor(idDoctor, appointmentStatus));
		return listAppointmentString;
	}

	/**
	 * metodo que retona todas las citas del paciente en un string tipo json
	 * 
	 * @param idPatient
	 * @return
	 * @throws Exception
	 */
	public String getAppointPatientJson(String idPatient) throws Exception {
		String listAppointmentString = new Gson().toJson(showAppointementDoctor(idPatient));
		return listAppointmentString;
	}

	/**
	 * metodo que retona todas las citas por status del paciente en un string tipo
	 * json
	 * 
	 * @param idPatient
	 * @param appointmentStatus
	 * @return
	 * @throws Exception
	 */
	public String getAppointPatientJson(String idPatient, AppointmentStatus appointmentStatus) throws Exception {
		String listAppointmentString = new Gson().toJson(showAppointementDoctor(idPatient, appointmentStatus));
		return listAppointmentString;
	}

	public Patient getPatientByCredentials(String id, String password) throws IncorrectData {
		try {
			Patient p = this.patientList.search(new Patient(id)).getInfo();
			if (p.getPassword().equals(password)) {
				return p;
			}else {
				throw new IncorrectData("The password is'nt correct");
			}
		}catch (IncorrectData ie) {
			throw ie;
		}catch (Exception e) {
			throw new IncorrectData("The patient doesnt exist in the system");
		}
	}

	public Doctor getDoctorByCredentials(String id, String password) throws IncorrectData {
		try {
			Doctor d = this.doctortList.search(new Doctor(id)).getInfo();
			if (d.getPassword().equals(password)) {
				return d;
			}else {
				throw new IncorrectData("The password is'nt correct");
			}
		}catch (IncorrectData ie) {
			throw ie;
		}catch (Exception e) {
			throw new IncorrectData("The doctor doesnt exist in the system");
		}
	}

}