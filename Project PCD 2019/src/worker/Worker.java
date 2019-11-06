package worker;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import general.Message;
import server.Server;

public class Worker extends Thread {

	private InetAddress address;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private int PORTO;
	private int rotation;
	private Socket s;
	private Server server;

	public Worker(String address, int PORTO, int rotation) {

		try {
			this.PORTO = PORTO;
			this.rotation = rotation;
			this.address = InetAddress.getByName(address);
			connectServer();
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

	@Override
	public void run() {
		try {
			out.writeObject(new Message("WORKER", rotation));
			//TasksList taskList = server.getTaskList();
			while(!isInterrupted()) {


			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Worker(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2])).start();

	}

}