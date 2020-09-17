package run;

import controller.MedicServer;
import model.entity.MedicalSpeciality;

public class RunServer {

	public static void main(String[] args) {
		try {
			MedicServer a = new MedicServer();
			a.getManager().createDoctor("1", "Cardiologo", "123", "correo", MedicalSpeciality.CARDIOLOGIST, "123");
			a.getManager().createDoctor("2", "Dentista", "123", "correo", MedicalSpeciality.DENTISTRY, "123");
			a.getManager().createDoctor("3", "General", "123", "correo", MedicalSpeciality.GENERAL, "123");
			a.getManager().createDoctor("4", "Orto", "123", "correo", MedicalSpeciality.ORTHOPEDIST, "123");
			a.getManager().createDoctor("5", "Pediatr", "123", "correo", MedicalSpeciality.PEDIATRIC, "123");
			a.getManager().createPatient("1", "p1", "123", "correo", "", "123");
			a.getManager().createPatient("2", "p2", "123", "correo", "", "123");
			a.getManager().createPatient("3", "p3", "123", "correo", "", "123");
			a.getManager().createPatient("4", "p4", "123", "correo", "", "123");
			a.getManager().createPatient("5", "p5", "123", "correo", "", "123");
			
			a.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
