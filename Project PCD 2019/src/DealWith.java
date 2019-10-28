import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class DealWith extends Thread {

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket s;
	private Server server;
	
	public DealWith(Server server, Socket s) {
		
		this.server = server;
		this.s = s;
	}
	
	@Override
	public void run() {

		try {
			//server.addClient(s);
			doConnections();
			serve();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("A fechar...");
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void serve() throws IOException {
		while(true) {
			try {
				Message m = (Message) in.readObject();
				if(m.getType().equals("WORKER")) {
					System.out.println(m.getType() + ": " + m.getRotation());
					for(DealWith dwc : server.getDealWithClientsList()) {
						dwc.out.writeObject(m);
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void doConnections() throws IOException {
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
	}
}