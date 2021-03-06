package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import controller.Actions;
import model.entity.Appointment;
import model.entity.AppointmentStatus;
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

	public MedicClient() throws IOException {
		System.out.println("Cliente lanzado");
		socketCliente = new Socket(GlobalConstants.HOST, GlobalConstants.PORT);
		output = new DataOutputStream(socketCliente.getOutputStream());// incializar el que va a enviar mensajes al //
		// servidor
		input = new DataInputStream(socketCliente.getInputStream());// incializar lo que llega del servidor
		String saludoInicial = input.readUTF();
		System.out.println(saludoInicial);
	}

	public void registerPatient(Patient p) throws Exception {
		output.writeUTF(Actions.REGISTER_PATIENT.name());
		String response = input.readUTF();
		if (response.equals(Actions.OK.name())) {
			output.writeUTF(JSonUtil.toJson(p));
			response = input.readUTF();
			if (response.equals(Actions.ERROR.name())) {
				response = input.readUTF();
				throw new Exception(response);
			}
		}
	}

	public void registerDoctor(Doctor d) throws Exception {
		output.writeUTF(Actions.REGISTER_DOCTOR.name());
		String response = input.readUTF();
		if (response.equals(Actions.OK.name())) {
			output.writeUTF(JSonUtil.toJson(d));
			response = input.readUTF();
			if (response.equals(Actions.ERROR.name())) {
				response = input.readUTF();
				throw new Exception(response);
			}
		}
	}

	public void loginPatient(String id, String password)  throws Exception{
		try {
			output.writeUTF(Actions.LOGIN_PATIENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(id);
				response = input.readUTF();
				if (response.equals(Actions.OK.name())) {
					output.writeUTF(password);
					response = input.readUTF();
					if (response.equals(Actions.OK.name())) {
						response = input.readUTF(); 
						client = JSonUtil.toPatient(response);
					}else {
						response = input.readUTF(); 
						throw new Exception(response);
					}
				}
			}
		} catch (IOException e) {
			throw new Exception("Internal error");
		}
	}

	public void loginDoctor(String id, String password) throws Exception {
		try {
			output.writeUTF(Actions.LOGIN_DOCTOR.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(id);
				response = input.readUTF();
				if (response.equals(Actions.OK.name())) {
					output.writeUTF(password);
					response = input.readUTF();
					if (response.equals(Actions.OK.name())) {
						response = input.readUTF(); 
						client = JSonUtil.toDoctor(response);
					}else {
						response = input.readUTF(); 
						throw new Exception(response);
					}
				}
			}
		} catch (IOException e) {
			throw new Exception("Internal error");
		}
	}

	public void addAppointment(Date d) throws Exception {
		try {
			output.writeUTF(Actions.ADD_APPOINTMENT.name());
			String response = input.readUTF();
			System.out.println(response);
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson((Doctor) this.client));
				output.writeUTF(JSonUtil.toJson(d));
				response = input.readUTF();
				if (response.equals(Actions.ERROR.name())) {
					response = input.readUTF();
					throw new Exception(response);
				}
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}
	}

	public void closeConection() {
		try {
			output.writeUTF(Actions.EXIT.name());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void attendAppointment(Appointment a, String diagnostic) {
		try {
			output.writeUTF(Actions.ATTEND_APPOINTMENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson(a));
				output.writeUTF(diagnostic);
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
				return JSonUtil.toArrayAppoints(input.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Appointment> showAppointmentDoctorStatus(AppointmentStatus status) {
		try {
			output.writeUTF(Actions.SHOW_APPOINTMENT_DOCTOR_STATUS.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson((Doctor) this.client));
				output.writeUTF(status.name());
				return JSonUtil.toArrayAppoints(input.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Appointment> showAppointmentPatientSatus(AppointmentStatus status) {
		try {
			output.writeUTF(Actions.SHOW_APPOINTMENT_PATIENT_STATUS.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson((Patient) this.client));
				output.writeUTF(status.name());
				response = input.readUTF();
				return JSonUtil.toArrayAppoints(response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<Appointment>();
	}

	public ArrayList<Appointment> showAppointmentDoctor() {
		try {
			output.writeUTF(Actions.SHOW_APPOINTMENT_DOCTOR.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson((Doctor) this.client));
				response = input.readUTF();
				JSonUtil.toArrayAppoints(response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<Appointment>();
	}

	public ArrayList<Appointment> showAppointmentPatient() {
		try {
			output.writeUTF(Actions.SHOW_APPOINTMENT_PATIENT.name());
			String response = input.readUTF();
			if (response.equals(Actions.OK.name())) {
				output.writeUTF(JSonUtil.toJson((Patient) this.client));
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
			e.printStackTrace();
		}
	}

	public Person getClient() {
		return client;
	}

	public void setClient(Person client) {
		this.client = client;
	}
}
