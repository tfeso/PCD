package client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;

import messages.Message;
import messages.MessagesType;

public class Client {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket s;
	private InetAddress address;
	private GUI gui;
	private int PORTO;
	private UUID idClient;

	public Client(String address, int PORTO) {
		try {
			this.PORTO = PORTO;
			this.address = InetAddress.getByName(address);
			idClient = UUID.randomUUID();
			connectServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gui = new GUI(this);
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
		System.out.println("Enviou mensagem");
		while(true) {
			try {
				Message m = (Message) in.readObject();
				System.out.println("Code: " + m.getCode());
				
				switch(m.getCode()) {
					case "103":
						System.out.println("Update Workers");
						gui.updateWorkersInList(m.getWorkersToUpdate());
						break;
					case "302":
						gui.loadRightPanel();
						gui.getSearchButton();
						gui.setOrder(m.getOrder());
						break;
					default:
						System.out.println("Error");
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
	
	public UUID getIdClient() {
		return idClient;
	}
}