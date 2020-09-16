package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import model.dao.EPSManager;
import model.entity.Doctor;
import model.entity.Patient;
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
			manager.createPatient(personJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void registerDoctor() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String doctorJson = in.readUTF();
			manager.createDoctor(doctorJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loginPatient() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String id = in.readUTF();
			this.out.writeUTF(MessageActions.OK.name());
			String password = in.readUTF();
			Patient p = manager.getPatientByCredentials(id, password);
			this.out.writeUTF(JSonUtil.toJson(p));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loginDoctor() {
		try {
			this.out.writeUTF(MessageActions.OK.name());
			String id = in.readUTF();
			this.out.writeUTF(MessageActions.OK.name());
			String password = in.readUTF();
			Doctor d = manager.getDoctorByCredentials(id, password);
			this.out.writeUTF(JSonUtil.toJson(d));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeConection() {
		try {
			this.socket.close();
			this.in.close();
			this.out.close();
			this.activeConection = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
