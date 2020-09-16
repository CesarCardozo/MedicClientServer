package run;

import java.io.IOException;

import persistence.DataInit;
import persistence.Write;

public class TestServer {
	public static void main(String[] args) {
		DataInit dataInit = new DataInit();
		dataInit.fillDataInit();
		Write write = new Write();
		try {
			write.WriteAll(dataInit.getPatiensListString(), dataInit.getDoctorsListString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
