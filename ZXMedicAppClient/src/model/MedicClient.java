package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import controller.Actions;
import model.entity.Appointment;
import model.entity.Doctor;
import model.entity.MedicalSpeciality;
import model.entity.Patient;
import model.entity.Person;
import model.util.GlobalConstants;
import model.util.JSonUtil;

public class MedicClient {

	// datos concernientes a la comunicacion por sockets
	private Socket socketCliente;
	private DataOutputStream output;
	private DataInputStream input;
	// Datos del cliente que puede ser paciente o doctor
	private Person client;
	private Boolean isPatient;

	public MedicClient() throws IOException {
		System.out.println("Cliente lanzado");
		socketCliente = new Socket(GlobalConstants.HOST, GlobalConstants.PORT);
		output = new DataOutputStream(socketCliente.getOutputStream());// incializar el que va a enviar mensajes al //
																		// servidor
		input = new DataInputStream(socketCliente.getInputStream());// incializar lo que llega del servidor
		String saludoInicial = input.readUTF();
		System.out.println(saludoInicial);
	}

	public void registerPatient(Patient p) {
		try {
			output.writeUTF(Actions.REGISTER_PATIENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson(p));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registerDoctor(Doctor d) {
		try {
			output.writeUTF(Actions.REGISTER_DOCTOR.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson(d));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Patient loginPatient(String id, String password) {
		try {
			output.writeUTF(Actions.LOGIN_PATIENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(id);
				response = input.readUTF();
				if (response.equals(Actions.OK.name())) {
					output.writeUTF(password);
					response = input.readUTF();
					client = JSonUtil.toPatient(response);
					isPatient = true;
					return (Patient) client;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Doctor loginDoctor(String id, String password) {
		try {
			output.writeUTF(Actions.LOGIN_DOCTOR.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(id);
				response = input.readUTF();
				if (response.equals(Actions.OK.name())) {
					output.writeUTF(password);
					response = input.readUTF();
					client = JSonUtil.toDoctor(response);
					isPatient = false;
					return (Doctor) client;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addAppointment(Date d) {
		try {
			output.writeUTF(Actions.ADD_APPOINTMENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson((Doctor) this.client));
				output.writeUTF(JSonUtil.toJson(d));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConection() {
		try {
			output.writeUTF(Actions.EXIT.name());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void attendAppointment(Appointment a) {
		try {
			output.writeUTF(Actions.ATTEND_APPOINTMENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson(a));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Appointment> showAppointment(MedicalSpeciality speciality) {
		try {
			output.writeUTF(Actions.SHOW_APPOINTMENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(speciality.name());
				JSonUtil.toArrayAppoints(input.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void showAppointmentDoctorStatus() {
		// TODO Auto-generated method stub

	}

	public void showAppointmentPatientSatus() {
		// TODO Auto-generated method stub

	}

	public ArrayList<Appointment> showAppointmentDoctor() {
		try {
			output.writeUTF(Actions.SHOW_APPOINTMENT_DOCTOR.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson((Doctor)this.client));
				JSonUtil.toArrayAppoints(input.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Appointment> showAppointmentPatient() {
		try {
			output.writeUTF(Actions.SHOW_APPOINTMENT_PATIENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson((Patient)this.client));
				JSonUtil.toArrayAppoints(input.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void bookAppointment(Appointment a) {
		try {
			output.writeUTF(Actions.BOOK_APPOINTMENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson(a));
				output.writeUTF(JSonUtil.toJson((Patient) this.client));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cancelAppointment(Appointment a) {
		try {
			output.writeUTF(Actions.CANCEL_APPOINTMENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson(a));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteAppointment(Date d) {
		try {
			output.writeUTF(Actions.DELETE_APPOINTMENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson((Doctor) this.client));
				output.writeUTF(JSonUtil.toJson(d));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
