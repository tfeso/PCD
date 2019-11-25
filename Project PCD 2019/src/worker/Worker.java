package worker;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import client.Task;
import general.Convert;
import general.EditImage;
import messages.Message;
import messages.MessagesType;

public class Worker extends Thread {

	private InetAddress address;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private int PORTO;
	private int rotation;
	private Socket s;

	public Worker(String address, int PORTO, int rotation) {
		try {
			this.PORTO = PORTO;
			this.rotation = rotation;
			this.address = InetAddress.getByName(address);
			connectServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void connectServer() throws IOException{
		System.out.println("Address: " + address);
		s = new Socket(address, PORTO);
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
	}

	@Override
	public void run() {
		try {
			out.writeObject(MessagesType.newWorker(rotation));
			while(!isInterrupted()) {
			
				out.writeObject(MessagesType.getTask());
				
				try {
					Message m = (Message) in.readObject();
					switch(m.getCode()) {
						case "205":
							Task t = m.getTaskDelivery();
							System.out.println("Image name: " + t.getImage().getName());
							ArrayList<Point> listPoints = EditImage.findSubImageinImage(t.getRotation(), 
													      								Convert.fileToBufferedImage(t.getImage()), 
													      								Convert.fileToBufferedImage(t.getSubImage()));
							
							System.out.println("Rotation: " + rotation);
							System.out.println("List Points: " + listPoints.size());
							t.setListPoints(listPoints);
							System.out.println("Task Points: " + t.getPointsList().size());
							System.out.println("-------------------------------------");
							
//							if(listPoints != null) 
//							{
//								for(Point p : listPoints) 
//								{
//									t.addPoints(p);
//									System.out.println("[" + (int)p.getX() + ";" + (int)p.getY() + "]");
//								}
//							}
							
							out.writeObject(MessagesType.taskFromWorker(t));
							break;
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Worker(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2])).start();
	}

}