package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import model.MedicClient;
import model.entity.Appointment;
import model.entity.AppointmentStatus;
import model.entity.Doctor;
import model.entity.MedicalSpeciality;
import model.entity.Patient;
import view.DialogBookAppointment;
import view.DialogBookAppointment2;
import view.DialogCreateAppointment;
import view.DialogLogin;
import view.DialogSignUpDoc;
import view.DialogSignUpPatient;
import view.FrameDoctor;
import view.FramePatient;
import view.MainFrame;
import view.ViewUtils;

public class ControllerClient implements ActionListener {

	private MainFrame mainFrame;
	private FrameDoctor frameDoctor;
	private FramePatient framePatient;
	private DialogLogin dialogLogin;
	private DialogSignUpDoc dialogSignUpDoc;
	private DialogSignUpPatient dialogSignUpPatient;
	private DialogCreateAppointment dialogCreateAppointment;
	private DialogBookAppointment bookAppointment;
	private DialogBookAppointment2 bookAppointment2;
	private MedicClient client;

	public ControllerClient() throws IOException {
		this.mainFrame = new MainFrame(this);
		this.client = new MedicClient();
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
		case ADD_APPOINTMENT:
			// addAppointment();
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
			// showAppointmentPatientSatus();
			break;
		case SHOW_APPOINTMENT_DOCTOR_STATUS:
			// showAppointmentDoctorStatus();// esto creo se puede quitar
			break;
		case SHOW_APPOINTMENT:
			// showAppointment();
			break;
		case ATTEND_APPOINTMENT:
			attendAppointment();
			break;
		case EXIT:
			closeConection();
			break;
		case LOGIN_PATIENT:
			this.loginPatient();// Falta exception
			break;
		case LOGIN_DOCTOR:
			loginDoctor();// Falta exception
			break;
		case BTN_SIGNUP_PATIENT:
			this.mainFrame.setVisible(false);
			dialogSignUpPatient = new DialogSignUpPatient(this);
			break;
		case BTN_SIGNUP_DOCTOR:
			this.mainFrame.setVisible(false);
			this.dialogSignUpDoc = new DialogSignUpDoc(this);
			break;
		case PATIENT_BOOK_APPOINT:
			this.framePatient.setVisible(false);
			this.bookAppointment = new DialogBookAppointment(this);
			break;
		case OK_BOOK_APPOINTMENT:
			this.bookAppointment.setVisible(false);
			this.bookAppointment2 = new DialogBookAppointment2(this, showAppointment(bookAppointment.getSpeciality()),
					Actions.BOOK_APPOINTMENT);
			break;
		case OK_CREATE_APPOINT:
			addAppointment();
			break;
		case CANCEL_BOOK_APPOINTMENT:
			this.bookAppointment.setVisible(false);
			this.mainFrame.setVisible(true);
			break;
		case CANCEL_BOOK_APPOINTMENT2:
			this.bookAppointment2.setVisible(false);
			this.mainFrame.setVisible(true);
			break;
		case CANCEL_CREATE_APPOINTMENT:
			this.dialogCreateAppointment.setVisible(false);
			this.frameDoctor.setVisible(true);
			break;
		case CANCEL_LOGIN:
			this.dialogLogin.setVisible(false);
			this.mainFrame.setVisible(true);
			break;
		case CANCEL_DOCTOR:
			this.frameDoctor.setVisible(false);
			this.mainFrame.setVisible(true);
			break;
		case CANCEL_PATIENT:
			this.framePatient.setVisible(false);
			this.mainFrame.setVisible(true);
			break;
		case CREATE_APPOINT:
			this.frameDoctor.setVisible(false);
			this.dialogCreateAppointment = new DialogCreateAppointment(this);
			break;
		case TO_DELETE:
			this.bookAppointment2 = new DialogBookAppointment2(this, showAppointmentDoctor(),
					Actions.DELETE_APPOINTMENT);
			break;
		case BTN_CANCEL_APPOINTMENT:
			this.bookAppointment2 = new DialogBookAppointment2(this, showAppointmentPatient(),
					Actions.CANCEL_APPOINTMENT);
			break;
		case BTN_ATTEND_APPOINTMENT:
			this.bookAppointment2 = new DialogBookAppointment2(this, showAppointmentDoctorNotAvailable(),
					Actions.ATTEND_APPOINTMENT);
			break;
		default:
			break;
		}
	}

	private void loginDoctor() {
		String idDoctor = this.mainFrame.getId();
		String passwordDoctor = this.mainFrame.getPassword();
		try {
			client.loginDoctor(idDoctor, passwordDoctor);
			ArrayList<Appointment> appointments = new ArrayList<>();
			appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.ATTENDED));
			appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.AVAILABLE));
			appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.NOT_AVAILABLE));
			this.frameDoctor = new FrameDoctor(this, (Doctor) client.getClient(), appointments);
			this.mainFrame.setVisible(false);
		} catch (Exception e) {
			ViewUtils.showError(this.mainFrame, e.getMessage());
		}
	}

	private void loginPatient() {
		String idPatient = this.mainFrame.getId();
		String passwordPatient = this.mainFrame.getPassword();
		try {
			client.loginPatient(idPatient, passwordPatient);
			ArrayList<Appointment> appointments = new ArrayList<>();
			appointments.addAll(client.showAppointmentPatientSatus(AppointmentStatus.ATTENDED));
			appointments.addAll(client.showAppointmentPatientSatus(AppointmentStatus.AVAILABLE));
			appointments.addAll(client.showAppointmentPatientSatus(AppointmentStatus.NOT_AVAILABLE));
			this.framePatient = new FramePatient(this, (Patient) client.getClient(), appointments);
			this.mainFrame.setVisible(false);
		} catch (Exception e) {
			ViewUtils.showError(this.mainFrame, e.getMessage());
		}
	}

	private void registerDoctor() {
		Doctor d = new Doctor(this.dialogSignUpDoc.getId(), this.dialogSignUpDoc.getName(),
				this.dialogSignUpDoc.getPhone(), this.dialogSignUpDoc.getPhone(), this.dialogSignUpDoc.getSpeciality(),
				this.dialogSignUpDoc.getPassword());
		try {
			client.registerDoctor(d);
			this.dialogSignUpDoc.setVisible(false);
			this.mainFrame.setVisible(true);
		} catch (Exception e) {
			ViewUtils.showError(this.dialogSignUpDoc, e.getMessage());
		}
	}

	private void registerPatient() {
		Patient p = new Patient(this.dialogSignUpPatient.getId(), this.dialogSignUpPatient.getName(),
				this.dialogSignUpPatient.getPhone(), this.dialogSignUpPatient.getPhone(),
				this.dialogSignUpPatient.getPassword());
		try {
			client.registerPatient(p);
			this.dialogSignUpPatient.setVisible(false);
			this.mainFrame.setVisible(true);
		} catch (Exception e) {
			ViewUtils.showError(this.dialogSignUpPatient, e.getMessage());
		}
	}

	private void attendAppointment() {
		bookAppointment2.setVisible(false);
		Appointment a = bookAppointment2.getAppointment();
		client.attendAppointment(a);
		ArrayList<Appointment> appointments = new ArrayList<>();
		appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.AVAILABLE));
		appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.NOT_AVAILABLE));
		this.frameDoctor = new FrameDoctor(this, (Doctor) client.getClient(), appointments);

	}

	private ArrayList<Appointment> showAppointment(MedicalSpeciality speciality) {
		ArrayList<Appointment> allAppointments = client.showAppointment(speciality);
		ArrayList<Appointment> appointments = new ArrayList<>();
		for (Appointment appointment : allAppointments) {
			if (appointment.getDate().compareTo(new Date())>0) {
				appointments.add(appointment);
			}
		}
		return appointments;

	}

	private ArrayList<Appointment> showAppointmentDoctor() {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.AVAILABLE));
		appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.NOT_AVAILABLE));
		return appointments;
	}

	private ArrayList<Appointment> showAppointmentDoctorNotAvailable() {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.NOT_AVAILABLE));
		return appointments;
	}

	private ArrayList<Appointment> showAppointmentPatient() {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		appointments.addAll(client.showAppointmentPatientSatus(AppointmentStatus.NOT_AVAILABLE));
		return appointments;
	}

	private void bookAppointment() {
		bookAppointment2.setVisible(false);
		Appointment a = bookAppointment2.getAppointment();
		client.bookAppointment(a);
		ArrayList<Appointment> appointments = new ArrayList<>();
		appointments.addAll(client.showAppointmentPatientSatus(AppointmentStatus.AVAILABLE));
		appointments.addAll(client.showAppointmentPatientSatus(AppointmentStatus.NOT_AVAILABLE));
		appointments.addAll(client.showAppointmentPatientSatus(AppointmentStatus.ATTENDED));
		this.framePatient = new FramePatient(this, (Patient) client.getClient(), appointments);
	}

	private void cancelAppointment() {
		bookAppointment2.setVisible(false);
		Appointment a = bookAppointment2.getAppointment();
		client.cancelAppointment(a);
		ArrayList<Appointment> appointments = new ArrayList<>();
		appointments.addAll(client.showAppointmentPatientSatus(AppointmentStatus.AVAILABLE));
		appointments.addAll(client.showAppointmentPatientSatus(AppointmentStatus.NOT_AVAILABLE));
		appointments.addAll(client.showAppointmentPatientSatus(AppointmentStatus.ATTENDED));
		this.framePatient = new FramePatient(this, (Patient) client.getClient(), appointments);
	}

	private void deleteAppointment() {
		bookAppointment2.setVisible(false);
		Appointment a = bookAppointment2.getAppointment();
		client.deleteAppointment(a.getDate());
		ArrayList<Appointment> appointments = new ArrayList<>();
		appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.NOT_AVAILABLE));
		appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.AVAILABLE));
		appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.ATTENDED));
		this.frameDoctor = new FrameDoctor(this, (Doctor) client.getClient(), appointments);
	}

	private void addAppointment() {
		this.dialogCreateAppointment.setVisible(false);
		try {
			client.addAppointment(dialogCreateAppointment.getDate());
		} catch (Exception e) {
			ViewUtils.showError(frameDoctor, e.getMessage());
		}
		ArrayList<Appointment> appointments = new ArrayList<>();
		appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.NOT_AVAILABLE));
		appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.AVAILABLE));
		appointments.addAll(client.showAppointmentDoctorStatus(AppointmentStatus.ATTENDED));
		this.frameDoctor = new FrameDoctor(this, (Doctor) client.getClient(), appointments);

	}

	public void closeConection() {
		this.mainFrame.setVisible(false);
		client.closeConection();
		// se cierra la vista
		System.out.println("Exit...");
		this.mainFrame.setVisible(false);
		this.mainFrame.setDefaultCloseOperation(0);
		System.exit(0);
	}
}