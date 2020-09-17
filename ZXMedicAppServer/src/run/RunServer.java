package run;

import controller.MedicServer;
import persistence.Persistence;

public class RunServer {

	public static void main(String[] args) {
		try {
			MedicServer a = new MedicServer();
			try {
				a.getManager().setDoctortList(Persistence.readDoctors());
				a.getManager().setPatientList(Persistence.readPatients());
			} catch (Exception e) {
			}
			a.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
