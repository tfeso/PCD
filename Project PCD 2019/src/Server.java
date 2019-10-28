import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	public static int PORTO;
	private ArrayList<DealWith> dealWithList;
	private ArrayList<Client> clientsList;
	private ArrayList<Worker> workersList;
	private TasksList taskList;

	public Server(int Porto) {
		this.PORTO = Porto;
	}
	
	public void startServing() throws IOException {
		
		dealWithList = new ArrayList<DealWith>();
		clientsList = new ArrayList<Client>();
		workersList = new ArrayList<Worker>();
		taskList = new TasksList();
		ServerSocket ss = new ServerSocket(PORTO);
		System.out.println("O servidor lançou a ServerSocket: " + ss);
		
		try {
			while(true) {
				Socket s = ss.accept();
				DealWith dw = new DealWith(this,s);
				dw.start();
				dealWithList.add(dw);
			}
		}finally {
			System.out.println("A fechar a ServerSocket...");
			ss.close();
		}
		
	}
	
	public ArrayList<DealWith> getDealWithClientsList(){
		return dealWithList;
	}
	
	public static void main(String[] args) {

		try {
			new Server(Integer.parseInt(args[0])).startServing();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao iniciar o servidor, argumento passado inválido!");
		}
	}
	
	public TasksList getTaskList() {
		return taskList;
	}

	public void addClient(Client client) {
		
		clientsList.add(client);
	}

}
