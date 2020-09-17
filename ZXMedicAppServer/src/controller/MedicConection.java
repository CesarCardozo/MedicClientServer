package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import model.dao.EPSManager;
import model.entity.AppointmentStatus;
import model.entity.Doctor;
import model.entity.MedicalSpeciality;
import model.entity.Patient;
import model.exception.AlreadyExists;
import model.exception.IncorrectData;
import model.util.JSonUtil;

public class MedicConection extends Thread {

	private int id;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String clientIp;
	private Boolean activeConection;
	private EPSManager manager;

	public MedicConection(Socket socket, int id, String clientIp, EPSManager manager) throws IOException {
		this.id = id;
		this.clientIp = clientIp;
		this.socket = socket;
		this.activeConection = true;
		this.in = new DataInputStream(this.socket.getInputStream());
		this.out = new DataOutputStream(this.socket.getOutputStream());
		this.manager = manager;
		this.start();
	}

	@Override
	public void run() {
		try {
			this.out.writeUTF("Cliente aceptado" + this.toString());
			while (activeConection) {
				System.out.println("esperando mensaje");
				String mensajeCliente = this.in.readUTF();
				System.out.println(mensajeCliente);
				handleRequest(mensajeCliente);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleRequest(String action) {
		switch (MessageActions.valueOf(action)) {
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

	private void registerPatient() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String personJson = in.readUTF();
			try {
				manager.createPatient(personJson);
				this.out.writeUTF(MessageActions.OK.name());
			} catch (AlreadyExists e) {
				this.out.writeUTF(MessageActions.ERROR.name());
				this.out.writeUTF(e.getMessage());
			}
		} catch (IOException e) {
			System.out.println("Se perdio conexion con el cliente");
		}
	}

	private void registerDoctor() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String doctorJson = in.readUTF();
			try {
				manager.createDoctor(doctorJson);
				this.out.writeUTF(MessageActions.OK.name());
			} catch (AlreadyExists e) {
				this.out.writeUTF(MessageActions.ERROR.name());
				this.out.writeUTF(e.getMessage());
			}
		} catch (IOException e) {
			System.out.println("Se perdio conexion con el cliente");
		}
	}

	private void loginPatient() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String id = in.readUTF();
			this.out.writeUTF(MessageActions.OK.name());
			String password = in.readUTF();
			try {
				Patient p = manager.getPatientByCredentials(id, password);
				this.out.writeUTF(MessageActions.OK.name());
				this.out.writeUTF(JSonUtil.toJson(p));
			} catch (IncorrectData e) {
				this.out.writeUTF(MessageActions.ERROR.name());
				this.out.writeUTF(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println("Se perdio conexion con el cliente");
		}
	}

	private void loginDoctor() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String id = in.readUTF();
			this.out.writeUTF(MessageActions.OK.name());
			String password = in.readUTF();
			try {
				Doctor d = manager.getDoctorByCredentials(id, password);
				this.out.writeUTF(MessageActions.OK.name());
				this.out.writeUTF(JSonUtil.toJson(d));
			} catch (IncorrectData e) {
				this.out.writeUTF(MessageActions.ERROR.name());
				this.out.writeUTF(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println("Se perdio conexion con el cliente");
		}
	}

	private void closeConection() {
		try {
			this.socket.close();
			this.in.close();
			this.out.close();
			this.activeConection = false;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void attendAppointment() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String appointmentJson = in.readUTF();
			manager.attendAppointment(JSonUtil.toAppointment(appointmentJson));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showAppointment() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String specialityString = in.readUTF();
			out.writeUTF(JSonUtil.toJson(manager.showAppointment(MedicalSpeciality.valueOf(specialityString))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showAppointmentDoctorStatus() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String doctor = in.readUTF();
			String statusAppoint = in.readUTF();
			out.writeUTF(JSonUtil.toJson(manager.showAppointementDoctor(JSonUtil.toDoctor(doctor).getId(),
					AppointmentStatus.valueOf(statusAppoint))));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showAppointmentPatientSatus() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String patient = in.readUTF();
			String statusAppoint = in.readUTF();
			out.writeUTF(JSonUtil.toJson(manager.showAppointementPatient(JSonUtil.toPatient(patient).getId(),
					AppointmentStatus.valueOf(statusAppoint))));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showAppointmentDoctor() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String doctor = in.readUTF();
			out.writeUTF(JSonUtil.toJson(manager.showAppointementDoctor(JSonUtil.toDoctor(doctor).getId())));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void showAppointmentPatient() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String patientJson = in.readUTF();
			out.writeUTF(JSonUtil.toJson(manager.showAppointementDoctor(JSonUtil.toPatient(patientJson).getId())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void bookAppointment() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String appointmentJson = in.readUTF();
			String patientJson = in.readUTF();
			manager.bookAppointment(JSonUtil.toPatient(patientJson), JSonUtil.toAppointment(appointmentJson));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cancelAppointment() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String appointmentJson = in.readUTF();
			manager.cancelAppointment(JSonUtil.toAppointment(appointmentJson));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteAppointment() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String doctorJson = in.readUTF();
			String dateJson = in.readUTF();
			manager.deleteAppointment(JSonUtil.toDoctor(doctorJson), JSonUtil.toDate(dateJson));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addAppointment() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String doctorJson = in.readUTF();
			String dateJson = in.readUTF();
			try {
				manager.createAppointment(JSonUtil.toDoctor(doctorJson), JSonUtil.toDate(dateJson));
				this.out.writeUTF(MessageActions.OK.name());
			} catch (Exception e) {
				this.out.writeUTF(MessageActions.ERROR.name());
				this.out.writeUTF(e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket the socket to set
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * @return the in
	 */
	public DataInputStream getIn() {
		return in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(DataInputStream in) {
		this.in = in;
	}

	/**
	 * @return the out
	 */
	public DataOutputStream getOut() {
		return out;
	}

	/**
	 * @param out the out to set
	 */
	public void setOut(DataOutputStream out) {
		this.out = out;
	}

	@Override
	public String toString() {
		return id + ". AdviceConection [clientIp=" + clientIp + "]";
	}

	/**
	 * @return the clientIp
	 */
	public String getClientIp() {
		return clientIp;
	}

	/**
	 * @param clientIp the clientIp to set
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}