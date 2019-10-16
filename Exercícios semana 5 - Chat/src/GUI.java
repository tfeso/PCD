import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GUI {

	private JFrame frame;
	private JTextField message;
	private JTextArea conversation;
	private ObjectOutputStream out;
	
	public GUI(ObjectOutputStream out) {
		this.out = out;
		conversation = new JTextArea("");
		message = new JTextField("");
		frame = new JFrame("");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		getUsername();
		enterWait();
	}
	
	public JTextArea getConversation() {
		return conversation;
	}
	
	private void enterWait() {
		
		message.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				
					try {
						out.writeObject(new Message(message.getText(), frame.getTitle()));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					message.setText("");
				}
			}
		});
	}


	private void addFrameContent() {

		frame.setLayout(new BorderLayout());
		frame.add(message, BorderLayout.NORTH);
		frame.add(conversation);
		conversation.setEnabled(false);
		frame.setSize(500, 500);
		frame.setResizable(true);
		frame.setVisible(true);
		
	}
	
	private void getUsername() {
		
		frame.setTitle(JOptionPane.showInputDialog(frame, "What's your name?"));
		
	}
	
	public static void main(String[] args) {
		
		
	}
	
}
