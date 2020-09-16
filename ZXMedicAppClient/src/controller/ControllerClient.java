package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.MedicClient;
import model.entity.Doctor;
import model.entity.MedicalSpeciality;
import model.entity.Patient;
import view.FrameDoctor;
import view.FramePatient;
import view.MainFrame;

public class ControllerClient implements ActionListener {

	private MainFrame mainFrame;
	private FrameDoctor frameDoctor;
	private FramePatient framePatient;
	private MedicClient client;

	public ControllerClient() {
		this.mainFrame = new MainFrame(this);
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case REGISTER_PATIENT:
			registerPatient();
			break;
		case REGISTER_DOCTOR:
			registerDoctor();
			break;
		case LOGIN_PATIENT:
			loginPatient();
			break;
		case LOGIN_DOCTOR:
			loginDoctor();
			break;
		case ADD_APPOINTMENT:
			addAppointment();
			break;
		case DELETE_APPOINTMENT:
			deleteAppointment();
			break;
		case CANCEL_APPOINTMENT:
			cancelAppointment();
			break;
		case BOOK_APPOINTMENT:
			bookAppointment();
			break;
		case SHOW_APPOINTMENT_PATIENT:
			showAppointmentPatient();
			break;
		case SHOW_APPOINTMENT_DOCTOR:
			showAppointmentDoctor();
			break;
		case SHOW_APPOINTMENT_PATIENT_STATUS:
			showAppointmentPatientSatus();
			break;
		case SHOW_APPOINTMENT_DOCTOR_STATUS:
			showAppointmentDoctorStatus();
			break;
		case SHOW_APPOINTMENT:
			showAppointment();
			break;
		case ATTEND_APPOINTMENT:
			attendAppointment();
			break;
		case EXIT:
			closeConection();
		default:
			break;
		}
	}

	private void loginDoctor() {
		String idDoctor = "1"; // reemplazar por la vista
		String passwordDoctor = "123"; // reemplazar por la vista
		client.loginDoctor(idDoctor, passwordDoctor);
		// usar ese doctor a discrecion
	}

	private void loginPatient() {
		String idPatient = "1"; // reemplazar por la vista
		String passwordPatient = "123"; // reemplazar por la vista
		client.loginPatient(idPatient, passwordPatient);
		// usar ese paciente a discrecion
	}

	private void registerDoctor() {
		Doctor d = new Doctor("1", "Cesar", "310", "correo", MedicalSpeciality.CARDIOLOGIST, "123");// reemplazar
																									// esto con la
																									// vista
		client.registerDoctor(d);
	}

	private void registerPatient() {
		Patient p = new Patient("1", "Cesar", "310", "correo", "123");// reemplazar esto con la vista
		client.registerPatient(p);
	}

	private void attendAppointment() {
		// TODO Auto-generated method stub

	}

	private void showAppointment() {
		// TODO Auto-generated method stub

	}

	private void showAppointmentDoctorStatus() {
		// TODO Auto-generated method stub

	}

	private void showAppointmentPatientSatus() {
		// TODO Auto-generated method stub

	}

	private void showAppointmentDoctor() {
		// TODO Auto-generated method stub

	}

	private void showAppointmentPatient() {
		// TODO Auto-generated method stub

	}

	private void bookAppointment() {
		// TODO Auto-generated method stub

	}

	private void cancelAppointment() {
		// TODO Auto-generated method stub

	}

	private void deleteAppointment() {
		// TODO Auto-generated method stub

	}

	private void addAppointment() {
		// TODO Auto-generated method stub

	}

	private void closeConection() {
		client.closeConectrion();
	}
}