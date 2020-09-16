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
			Patient p = new Patient("1", "Cesar", "310", "correo", "123");// reemplazar esto con la vista
			client.registerPatient(p);
			break;
		case REGISTER_DOCTOR:
			Doctor d = new Doctor("1", "Cesar", "310", "correo", MedicalSpeciality.CARDIOLOGIST, "123");// reemplazar
																										// esto con la
																										// vista
			client.registerDoctor(d);
			break;
		case LOGIN_PATIENT:
			String idPatient = "1"; // reemplazar por la vista
			String passwordPatient = "123"; // reemplazar por la vista
			client.loginPatient(idPatient, passwordPatient);
			// usar ese paciente a discrecion
			break;
		case LOGIN_DOCTOR:
			String idDoctor = "1"; // reemplazar por la vista
			String passwordDoctor = "123"; // reemplazar por la vista
			client.loginDoctor(idDoctor, passwordDoctor);
			// usar ese doctor a discrecion
			break;
		case ADD_APPOINTMENT:
			
			break;
		case DELETE_APPOINTMENT:

			break;
		case CANCEL_APPOINTMENT:

			break;
		case BOOK_APPOINTMENT:

			break;
		case SHOW_APPOINTMENT_PATIENT:

			break;
		case SHOW_APPOINTMENT_DOCTOR:

			break;
		case SHOW_APPOINTMENT_PATIENT_STATUS:

			break;
		case SHOW_APPOINTMENT_DOCTOR_STATUS:

			break;
		case SHOW_APPOINTMENT:

			break;
		case ATTEND_APPOINTMENT:

			break;
		case EXIT:
			closeConection();
		default:
			break;
		}
	}

	private void closeConection() {

	}
}