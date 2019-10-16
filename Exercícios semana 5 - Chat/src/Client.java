import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket s;
	private InetAddress address;
	private GUI gui;
	
	public Client() {
		try {
			connectServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gui = new GUI(out);
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

		address = InetAddress.getByName(null);
		System.out.println("Address: " + address);
		s = new Socket(address, Server.PORTO);
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());

	}
	
	private void serve() throws IOException {
		while(true) {
			try {
				Message m = (Message) in.readObject();
				gui.getConversation().append("User " + m.getUsername() + ": " + m.getMessage() + "\n");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Client();
	}

	public InetAddress getAddress() {
		return address;
	}

	public ObjectOutputStream getObjectOutputStream() {
		return out;
	}

}
