package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import messages.Message;
import messages.MessagesType;

public class DealWith extends Thread {

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket s;
	private Server server;
	private int rotation = -1;
	
	public DealWith(Server server, Socket s) {
		this.server = server;
		this.s = s;
	}

	@Override
	public void run() {

		try {
			doConnections();
			serve();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("It's Closed!");
			try {
				s.close();
			} catch (IOException e) {
				System.out.println("Error in Socket Close!");
			}
		}
	}

	private void serve() throws IOException {
		try {
			while(true) {
				try {
					Message m = (Message) in.readObject();
					System.out.println("Code: " + m.getCode());
					switch(m.getCode()) {
						case "001": // NEW CLIENT
							server.addDwClient(this);
							break;
						case "101": // NEW WORKER
							rotation = Integer.parseInt(m.getContent());
							server.addDwWorker(this, rotation);
							updateWorkersInGUI();
							break;
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Class message not exists");
				}
			}
		} catch (Exception e) {
			server.removeDW(this, rotation);
			updateWorkersInGUI();
		}
	}

	private void doConnections() throws IOException {
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
	}
	
	private void updateWorkersInGUI() {
		for(DealWith dw : server.getDwClients()) {
			try {
				dw.out.writeObject(MessagesType.updateWorkers(server.getWorkersByRotation()));
			} catch (IOException e) {
				System.out.println("Error to update workers");
			}
		}
	}
	

	
}