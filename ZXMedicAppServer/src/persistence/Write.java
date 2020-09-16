package persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Write {
	
	public void WritePatients(String listPatientString) throws IOException {
		File archivo = new File("src/persistence/Patients.json");
		BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(listPatientString);       
        bw.close();
	}  
	
	public void WriteDoctors(String listDoctorString) throws IOException {
        File archivo2 = new File("src/persistence/Doctors.json");
		BufferedWriter bw2;
            bw2 = new BufferedWriter(new FileWriter(archivo2));
            bw2.write(listDoctorString);       
        bw2.close();
	}
	
	public void WriteAppoints(String ListAppoints) throws IOException {
        File archivo3 = new File("src/persistence/Appoints.json");
		BufferedWriter bw3;
            bw3 = new BufferedWriter(new FileWriter(archivo3));
            bw3.write(ListAppoints);       
        bw3.close();
	}
	
	public void WriteAll(String listPatientString, String ListDoctors) throws IOException {
		WritePatients(listPatientString);
		WriteDoctors(ListDoctors);
	}
}
