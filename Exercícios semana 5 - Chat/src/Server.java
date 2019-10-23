import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	public static final int PORTO = 8080;
	private ArrayList<DealWithClients> dealWithClientsList;
	private ArrayList<Client> clientsList;

	
	public void startServing() throws IOException {
		
		dealWithClientsList = new ArrayList<DealWithClients>();
		clientsList = new ArrayList<Client>();
		ServerSocket ss = new ServerSocket(PORTO);
		System.out.println("O servidor lançou a ServerSocket: " + ss);
		
		try {
			while(true) {
				Socket s = ss.accept();
				DealWithClients dwc = new DealWithClients(this,s);
				dwc.start();
				dealWithClientsList.add(dwc);
			}
		}finally {
			System.out.println("A fechar a ServerSocket...");
			ss.close();
		}
		
	}
	
	public ArrayList<DealWithClients> getDealWithClientsList(){
		return dealWithClientsList;
	}
	
	public static void main(String[] args) {

		try {
			new Server().startServing();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addClient(Client client) {
		
		clientsList.add(client);
	}

}
