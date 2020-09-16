package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import model.MedicClient;
import model.entity.Appointment;
import model.entity.AppointmentStatus;
import model.entity.Doctor;
import model.entity.MedicalSpeciality;
import model.entity.Patient;
import view.DialogLogin;
import view.DialogSignUpDoc;
import view.DialogSignUpPatient;
import view.FrameDoctor;
import view.FramePatient;
import view.MainFrame;

public class ControllerClient implements ActionListener {

	private MainFrame mainFrame;
	private FrameDoctor frameDoctor;
	private FramePatient framePatient;
	private DialogLogin dialogLoginPatient;
	private DialogSignUpDoc dialogSignUpDoc;
	private DialogSignUpPatient dialogSignUpPatient;
	private MedicClient client;

	public ControllerClient() throws IOException {
		this.mainFrame = new MainFrame(this);
		//		this.client = new MedicClient();
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
			break;
		case BTN_LOGIN_PATIENT:
			this.dialogLoginPatient = new DialogLogin(this, Actions.LOGIN_PATIENT);
			break;
		case BTN_LOGIN_DOCTOR:
			this.dialogLoginPatient = new DialogLogin(this, Actions.LOGIN_DOCTOR);
			break;
		case  BTN_SIGNUP_PATIENT:
			dialogSignUpPatient = new DialogSignUpPatient(this);
			break;
		case BTN_SIGNUP_DOCTOR:
			this.dialogSignUpDoc = new DialogSignUpDoc(this);
			break;
		default:
			break;
		}
	}

	private void loginDoctor() {
		String idDoctor = this.dialogLoginPatient.getId(); 
		String passwordDoctor = this.dialogLoginPatient.getPassword(); 
		this.dialogLoginPatient.setVisible(false);
		Doctor doctor = client.loginDoctor(idDoctor, passwordDoctor);
		// usar ese doctor a discrecion
		this.frameDoctor = new FrameDoctor(this, doctor);//hace falta pasarle la lista de citas 
	}

	private void loginPatient() {
		String idPatient = this.dialogLoginPatient.getId(); 
		String passwordPatient = this.dialogLoginPatient.getPassword();
		this.dialogLoginPatient.setVisible(false);
		Patient patient = client.loginPatient(idPatient, passwordPatient);
		// usar ese paciente a discrecion
		this.framePatient = new FramePatient(this, patient);//hace falta pasarle la lista de citas 
	}

	private void registerDoctor() {
		Doctor d = new Doctor(this.dialogSignUpDoc.getId(), this.dialogSignUpDoc.getName(), this.dialogSignUpDoc.getPhone(), 
				this.dialogSignUpDoc.getPhone(), this.dialogSignUpDoc.getSpeciality(), this.dialogSignUpDoc.getPassword());
		client.registerDoctor(d);
		//mandar este nuevo doctor a la percistencia para que pueda entrar 
	}

	private void registerPatient() {
		Patient p = new Patient(this.dialogSignUpPatient.getId(), this.dialogSignUpPatient.getName(), this.dialogSignUpPatient.getPhone(), 
				this.dialogSignUpPatient.getPhone(), this.dialogSignUpPatient.getPassword() );
		client.registerPatient(p);
		//mandar este nuevo paciente a la percistencia para que pueda entrar 
	}

	private void attendAppointment() {
		//Se seleccion de la vista desde la perspectiva del doctor la appointment que quiere atender
		Appointment a = new Appointment(new Date());
		a.setDoctor(new Doctor("1", "Cesar", "310", "correo", MedicalSpeciality.CARDIOLOGIST, "123"));
		//se reemplaza la seleccion de la appointment de arriba por la obtencion desde la vista
		client.attendAppointment(a);
	}

	private void showAppointment() {
		MedicalSpeciality speciality= MedicalSpeciality.CARDIOLOGIST;//Se debe establecer la especialidad medica desde la vista
		client.showAppointment(speciality);
		//se tiene que mostrar el array que dio el metodo anterior en la vista

	}

	private void showAppointmentDoctorStatus() {
		AppointmentStatus status = AppointmentStatus.NOT_AVAILABLE;//se debe traer de la vista 
		client.showAppointmentDoctorStatus(status);
		//se tiene que mostrar el array que dio el metodo en la vista
	}

	private void showAppointmentPatientSatus() {
		AppointmentStatus status = AppointmentStatus.AVAILABLE;//se debe traer de la vista 
		client.showAppointmentPatientSatus(status);
		//se tiene que mostrar el array que dio el metodo en la vista
	}

	private void showAppointmentDoctor() {
		client.showAppointmentDoctor();
		//eso de arriba devuelve una lista de citas del doctor hay que usarla en la vista
	}

	private void showAppointmentPatient() {
		client.showAppointmentPatient();
		//eso de arriba devuelve una lista de citas del paciente hay que usarla en la vista
	}

	private void bookAppointment() {
		//Se seleccion de la vista desde la perspectiva del usuario  la appointment que quiere cancelar
		Appointment a = new Appointment(new Date());
		a.setDoctor(new Doctor("1", "Cesar", "310", "correo", MedicalSpeciality.CARDIOLOGIST, "123"));
		//se reemplaza la seleccion de la appointment de arriba por la obtencion desde la vista
		client.bookAppointment(a);
	}

	private void cancelAppointment() {
		//Se seleccion de la vista desde la perspectiva del usuario  la appointment que quiere cancelar
		Appointment a = new Appointment(new Date());
		a.setDoctor(new Doctor("1", "Cesar", "310", "correo", MedicalSpeciality.CARDIOLOGIST, "123"));
		//se reemplaza la seleccion de la appointment de arriba por la obtencion desde la vista
		client.cancelAppointment(a);
	}

	private void deleteAppointment() {
		Date d = new Date();//aca se cambia esto por el selector de fecha de la vista
		client.deleteAppointment(d);
	}

	private void addAppointment() {
		Date d = new Date();//aca se cambia esto por el selector de fecha de la vista
		client.addAppointment(d);
		//se debe actualizar la vista para que se actualice la lista de citas (desde la perspectiva del doctor)
	}

	private void closeConection() {
		this.mainFrame.setVisible(false);
		client.closeConection();
		// se debe cerrar la vista
	}
}