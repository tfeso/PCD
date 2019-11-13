package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import client.Order;
import general.TasksList;

public class Server {

	public static int PORTO;
	private ArrayList<DealWithClient> dwClientsList;
	private ArrayList<DealWithWorker> dwWorkersList;
	private HashMap<Integer, Integer> workersByRotation;
	private TasksList sharedResourceTasks;
	private ArrayList<Order> ordersList;

	public Server(int Porto) {
		this.PORTO = Porto;
	}

	public void startServing() throws IOException {

		dwClientsList = new ArrayList<DealWithClient>();
		dwWorkersList = new ArrayList<DealWithWorker>();
		sharedResourceTasks = new TasksList();
		ordersList = new ArrayList<Order>();
		workersByRotation = new HashMap<Integer, Integer>();
		ServerSocket ss = new ServerSocket(PORTO);
		System.out.println("The server launch the ServerSocket: " + ss);

		try {
			while(true) {
				Socket s = ss.accept();
				DealWith dw = new DealWith(this,s);
				dw.start();
			}
		}finally {
			System.out.println("Closing ServerSocket...");
			ss.close();
		}
	}

	public static void main(String[] args) {
		try {
			new Server(Integer.parseInt(args[0])).startServing();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error on inicializate the server, argument invalid!");
		}
	}

	public TasksList getTaskList() {
		return sharedResourceTasks;
	}

	public ArrayList<DealWithWorker> getDwWorkers(){
		return dwWorkersList;
	}

	public ArrayList<DealWithClient> getDwClients(){
		return dwClientsList;
	}
	
	public ArrayList<Order> getOrdersList() {
		return ordersList;
	}

	public void removeDW(DealWith dealWith, int rotation) {
		Iterator itClients = dwClientsList.iterator();
		while (itClients.hasNext()) {
			if (((DealWith)itClients.next()).equals(dealWith)) {
				itClients.remove();
				System.out.println("Number of clients: " + dwClientsList.size());
				return;
			}
		}

		Iterator itWorkers = dwWorkersList.iterator();
		while (itWorkers.hasNext()) {
			if (((DealWith)itWorkers.next()).equals(dealWith)) {
				itWorkers.remove();
				if(rotation != -1) { 
					if(workersByRotation.containsKey(rotation)) {
						int numberOfActiveWorkers = workersByRotation.get(rotation);
						if(numberOfActiveWorkers > 0)
							numberOfActiveWorkers--;
						workersByRotation.put(rotation,numberOfActiveWorkers);
					} else {
						workersByRotation.put(rotation, 1);
					}
					printWorkersByRotation();
				}
				System.out.println("Number of workers: " + dwWorkersList.size());
				return;
			}
		}
	}

	public void addDwClient(DealWithClient dw) {
		dwClientsList.add(dw);
		System.out.println("Number Of Clients: " + dwClientsList.size());
	}

	public void addDwWorker(DealWithWorker dw, int rotation) {
		dwWorkersList.add(dw);
		if(workersByRotation.containsKey(rotation)) {
			int numberOfActiveWorkers = workersByRotation.get(rotation) + 1;
			workersByRotation.put(rotation,numberOfActiveWorkers);
		} else {
			workersByRotation.put(rotation, 1);
		}
		System.out.println("Number Of Workers: " + dwWorkersList.size());
		printWorkersByRotation();
	}
	
	private void printWorkersByRotation() {
		System.out.println("Workers by rotation:");
		for (Integer key : workersByRotation.keySet()) {
			System.out.println("Rotation: " + key + " Number of workers: " + workersByRotation.get(key));
		}
	}
	
	public HashMap<Integer, Integer> getWorkersByRotation() {
		return workersByRotation;
	}
	
	public Order getOrderById(UUID id) {
		
		for(Order o : ordersList) {
			if(o.getId().equals(id)) {
				System.out.println("Tasks completed: " + o.getBarrier().size());
				return o;
			}
		}
		return null;
	}

	private TasksList getTasksList() {
		return sharedResourceTasks;
	}
}