package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.dao.EPSManager;
import model.util.GlobalConstants;

public class MedicServer extends Thread {

	private ServerSocket serverSocket;
	private Socket socket;
	private ArrayList<MedicConection> conections;
	private EPSManager manager;

	public MedicServer() throws IOException {
		super();
		serverSocket = new ServerSocket(GlobalConstants.PORT);
		conections = new ArrayList<MedicConection>();
		this.manager = new EPSManager();
		System.out.println("EsperandoConexiones");
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.socket = serverSocket.accept();
				conections.add(new MedicConection(socket, conections.size(), this.socket.getRemoteSocketAddress().toString(),this.manager));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
