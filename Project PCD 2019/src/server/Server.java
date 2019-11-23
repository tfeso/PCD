package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import general.OrderBarrier;
import general.TasksBlockingQueue;

public class Server {

	public static int PORTO;
	private ArrayList<DealWith> dwClientsList;
	private ArrayList<DealWith> dwWorkersList;
	private ArrayList<OrderBarrier> barrierList;
	private HashMap<Integer, Integer> workersByRotation;
	private TasksBlockingQueue taskList;

	public Server(int Porto) {
		this.PORTO = Porto;
		workersByRotation = new HashMap<Integer, Integer>();
	}

	public void startServing() throws IOException {

		dwClientsList = new ArrayList<DealWith>();
		dwWorkersList = new ArrayList<DealWith>();
		barrierList = new ArrayList<OrderBarrier>();
		taskList = new TasksBlockingQueue();
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

	public TasksBlockingQueue getTaskList() {
		return taskList;
	}

	public ArrayList<DealWith> getDwWorkers(){
		return dwWorkersList;
	}

	public ArrayList<DealWith> getDwClients(){
		return dwClientsList;
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

	public void addDwClient(DealWith dw) {
		dwClientsList.add(dw);
		System.out.println("Number Of Clients: " + dwClientsList.size());
	}

	public void addDwWorker(DealWith dw, int rotation) {
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

	public OrderBarrier getBarrierByOrderId(String orderId) {
		for(OrderBarrier ob : barrierList) {
			if(ob.getOrder().getId().toString().equals(orderId)) {
				System.out.println("Encontrou barreira: " + ob.getOrder().getId().toString());
				return ob;
			}
		}
		System.out.println("Não encontrou barreira");
		return null;
	}
	
	public void addToBarrierList(OrderBarrier barrier) {
		barrierList.add(barrier);
	}
	
	private TasksBlockingQueue getTasksList() {
		return taskList;
	}
}