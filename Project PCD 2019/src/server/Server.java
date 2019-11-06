package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import general.TasksList;
import worker.Worker;

public class Server {

	public static int PORTO;
	private ArrayList<DealWith> dealWithList;
	private ArrayList<DealWith> workersList;
	private TasksList taskList;

	public Server(int Porto) {
		this.PORTO = Porto;
	}

	public void startServing() throws IOException {

		dealWithList = new ArrayList<DealWith>();
		taskList = new TasksList();
		workersList = new ArrayList<DealWith>();
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

	public ArrayList<DealWith> getDealWithWorkers(){
		
		ArrayList<DealWith> dwc = new ArrayList<DealWith>();
		for(DealWith d : dealWithList) {
			if(d.isWorker())
				dwc.add(d);
		}
		return dwc;
	}

	public ArrayList<DealWith> getDealWithClients(){
		
		ArrayList<DealWith> dwc = new ArrayList<DealWith>();
		for(DealWith d : dealWithList) {
			if(!d.isWorker())
				dwc.add(d);
		}
		return dwc;
	}
	
	public void addWorker(Worker worker) {
		//workersList.add(worker);
	}
	
	public void removeDealWith(DealWith dw) {
		
		dealWithList.remove(dw);
		
		for(DealWith d : getDealWithWorkers()) {
			
			
		}
	}
}
