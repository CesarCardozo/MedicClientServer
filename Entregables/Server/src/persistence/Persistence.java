package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.entity.Doctor;
import model.entity.Patient;
import model.util.JSonUtil;
import structure.TreeAvl;

public class Persistence {

	//para desarrollo
	public static final String pathPatients = "src/persistence/Patients.json";
	public static final String pathDoctors = "src/persistence/Doctors.json";
	//para despliegue
//	public static final String pathPatients = "./Patients.json";
//	public static final String pathDoctors = "./Doctors.json";
	
	public static synchronized void WritePatients(String listPatientString) throws IOException {
		File archivo = new File(pathPatients);
		BufferedWriter bw;
		bw = new BufferedWriter(new FileWriter(archivo));
		bw.write(listPatientString);
		bw.close();
	}

	public static synchronized void WriteDoctors(String listDoctorString) throws IOException {
		File archivo2 = new File(pathDoctors);
		BufferedWriter bw2;
		bw2 = new BufferedWriter(new FileWriter(archivo2));
		bw2.write(listDoctorString);
		bw2.close();
	}

	public static synchronized TreeAvl<Patient> readPatients() throws IOException {
		File archivo = new File(pathPatients);
		BufferedReader br = new BufferedReader(new FileReader(archivo));
		String content = br.readLine();
		br.close();
		return JSonUtil.toPatients(content);
	}

	public static synchronized TreeAvl<Doctor> readDoctors() throws IOException {
		File archivo = new File(pathDoctors);
		BufferedReader br = new BufferedReader(new FileReader(archivo));
		String content = br.readLine();
		br.close();
		return JSonUtil.toDoctors(content);
	}
}
