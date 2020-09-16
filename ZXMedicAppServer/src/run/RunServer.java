package run;

import java.io.IOException;

import controller.MedicServer;


public class RunServer {

	public static void main(String[] args) {
		try {
			MedicServer a = new MedicServer();
			a.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
