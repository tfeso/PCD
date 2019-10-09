import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUI {

	private JFrame main;
	private JTextField txtBox;
	private JButton btn;
	private ArrayList<Thread> listThreads;
	
	public GUI() {
		main = new JFrame();
		txtBox = new JTextField("0");
		btn = new JButton("STOP");
		buildMain();
		buttonMethod();
	}

	public void setListThreads(ArrayList<Thread> listThreads) {
		this.listThreads = listThreads;
	}
	
	private void buildMain() {
		main.setResizable(false);
		main.setSize(100, 100);
		main.setLayout(new FlowLayout());
		main.add(txtBox);
		main.add(btn);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
	}
	
	private void buttonMethod() {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Thread t: listThreads) {
					t.interrupt();
				}
			}
		});
	}
	public void setTestInTextField(String weight) {
		txtBox.setText(weight);
		main.revalidate();
	}
	
	



}