package server;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import client.Order;
import client.Task;
import messages.Message;
import messages.MessagesType;

public class DealWithWorker extends DealWith {

	private int rotation;
	public DealWithWorker(Server server, Socket s, int rotation) {
		super(server, s);
		this.rotation = rotation;
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
				getSocket().close();
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
							break;
						case "101": // NEW WORKER
							rotation = Integer.parseInt(m.getContent());
							server.addDwWorker(this, rotation);
							updateWorkersInGUI();
							break;
						case "201": // NEW TASKS IN BLOQUINGQUEUE
							server.getOrdersList().add(m.getTasksList().get(0).getOrder());
							for(Task t : m.getTasksList()) {
								server.getTaskList().offer(t);
								System.out.println("Offer: " + t.getImage() + " Rotation: " + t.getRotation());
							}
							break;
						case "204": // GET TASK IN BLOQUINGQUEUE
							out.writeObject(MessagesType.taskDelivery((Task)server.getTaskList().poll()));
							break;
						case "206": // RECEIVED TASK FROM WORKER
							Task t = m.getTaskDelivery();
							Order order = server.getOrderById(t.getOrder().getId());
							order.addPointToMap(t.getImage().getName(), t.getPointsList());
							order.getBarrier().barrierEntry();
							JOptionPane.showMessageDialog(null, "Tasks completed: " + order.getBarrier().size());
							break;
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Class message not exists");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			getServer().removeDW(this, rotation);
			//updateWorkersInGUI();
		}
	}

	
}
