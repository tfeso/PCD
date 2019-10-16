import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class DealWithClients extends Thread {

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket s;
	private Server server;
	
	public DealWithClients(Server server, Socket s) {
		
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
				System.out.println(m.getUsername() + ": " + m.getMessage());
				for(DealWithClients dwc : server.getDealWithClientsList()) {
					dwc.out.writeObject(m);
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
