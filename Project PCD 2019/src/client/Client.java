package client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

import messages.Message;
import messages.MessagesType;

public class Client {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket s;
	private InetAddress address;
	private GUI gui;
	private int PORTO;

	public Client(String address, int PORTO) {
		try {
			this.PORTO = PORTO;
			this.address = InetAddress.getByName(address);
			connectServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gui = new GUI();
		runClient();
	}

	public void runClient() {
		try {
			serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void connectServer() throws IOException{

		System.out.println("Address: " + address);
		s = new Socket(address, PORTO);
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());

	}

	private void serve() throws IOException {
		out.writeObject(MessagesType.newClient());
		while(true) {
			Message m;
			try {
				m = (Message) in.readObject();
				
				switch(m.getCode()) {
					case "103":
						//gui.addWorkerToList(Integer.parseInt(m.getContent()));
						gui.updateWorkersInList(m.getWorkersToUpdate());
						break;
				}
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Client(args[0],Integer.parseInt(args[1]));
	}

	public InetAddress getAddress() {
		return address;
	}

	public ObjectOutputStream getObjectOutputStream() {
		return out;
	}
	

	private String printWorkersByRotationString(HashMap<Integer, Integer> workersByRotation) {
		String a = "";
		for (Integer key : workersByRotation.keySet()) {
			a += "Rotation: " + key + " Number of workers: " + workersByRotation.get(key) + System.lineSeparator();
		}
return a;	}
	
	

}
