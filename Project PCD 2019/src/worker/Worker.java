package worker;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
							
							ArrayList<Point> listPoints = EditImage.findSubImageinImage(m.getTaskDelivery().getRotation(), 
													      								Convert.fileToBufferedImage(m.getTaskDelivery().getImage()), 
													      								Convert.fileToBufferedImage(m.getTaskDelivery().getSubImage()));
							String a = "";
							if(listPoints != null) {
								for(Point p : listPoints) {
									a += "X: " + p.getX() + " Y: " + p.getY() + System.lineSeparator(); 
								}
							}
							System.out.println("Points" + System.lineSeparator() + a);
							
							//JOptionPane.showMessageDialog(null, a);
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