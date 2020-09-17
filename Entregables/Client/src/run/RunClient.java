package run;

import java.io.IOException;

import controller.ControllerClient;

public class RunClient {

	public static void main(String[] args) {
		try {
			new ControllerClient();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
