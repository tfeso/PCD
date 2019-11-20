package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import client.Task;
import general.OrderBarrier;
import messages.Message;
import messages.MessagesType;

public class DealWith extends Thread {

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket s;
	private Server server;
	private int rotation = -1;
	
	public DealWith(Server server, Socket s) {
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
			System.out.println("It's Closed!");
			try {
				s.close();
			} catch (IOException e) {
				System.out.println("Error in Socket Close!");
			}
		}
	}

	private void serve() throws IOException {
		try {
			while(true) {
				try {
					Message m = (Message) in.readObject();
					System.out.println("Code: " + m.getCode());
					switch(m.getCode()) {
						case "001": // NEW CLIENT
							server.addDwClient(this);
							updateWorkersInGUI();
							break;
						case "101": // NEW WORKER
							rotation = Integer.parseInt(m.getContent());
							server.addDwWorker(this, rotation);
							updateWorkersInGUI();
							break;
						case "201": // PUT TASKS IN BLOQUINGQUEUE
							OrderBarrier ob = new OrderBarrier(m.getTasksList().size(), m.getTasksList().get(0).getOrder());
							server.addToBarrierList(ob);
							for(Task t : m.getTasksList()) {
								server.getTaskList().offer(t);
								System.out.println("Offer: " + t.getImage() + " Rotation: " + t.getRotation());
							}
							ob.barrierEntry();
							out.writeObject(MessagesType.endOrder());
							break;
						case "204": // GET TASK IN BLOQUINGQUEUE
							Task t = (Task)server.getTaskList().poll();
							out.writeObject(MessagesType.taskDelivery(t));
							System.out.println("Poll: " + t.getImage() + " Rotation: " + t.getRotation());
							break;
						case "206":
							Task taskFromWorker = m.getTaskDelivery();
							OrderBarrier barrier = server.getBarrierByOrderId(taskFromWorker.getOrder().getId());
							barrier.getOrder().addPointToMap(taskFromWorker.getImage().getName(), taskFromWorker.getPointsList());
							barrier.barrierEntryTeste();
							break;
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Class message not exists");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			server.removeDW(this, rotation);
			updateWorkersInGUI();
		}
	}

	private void doConnections() throws IOException {
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
	}
	
	private void updateWorkersInGUI() {
		for(DealWith dw : server.getDwClients()) {
			try {
				dw.out.writeObject(MessagesType.updateWorkers(server.getWorkersByRotation()));
			} catch (IOException e) {
				System.out.println("Error to update workers");
			}
		}
	}	
}