import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Frame {
	
private JFrame frame;

	public Frame() {

		frame = new JFrame("Hello");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
	}

	private void addFrameContent() {


		JPanel painelDados = new JPanel();		
		painelDados.setLayout(new GridLayout(4,2));

		JLabel label = new JLabel("Title: ");
		painelDados.add(label);
		JTextField labelTitle = new JTextField("Hello");
		painelDados.add(labelTitle);
		
		JLabel width = new JLabel("width");
		painelDados.add(width);
		JTextField labelWidth = new JTextField("300");
		painelDados.add(labelWidth);
		
		JLabel height = new JLabel("height");
		painelDados.add(height);
		JTextField labelHeight = new JTextField("200");
		painelDados.add(labelHeight);
		
		JButton btnUpdate = new JButton("update");
		painelDados.add(btnUpdate);
		

		JCheckBox check = new JCheckBox("center");
		painelDados.add(check);
		
		JPanel painelPrincipal = new JPanel();
		
		painelPrincipal.setLayout(new FlowLayout());
		painelPrincipal.add(painelDados);

		frame.add(painelPrincipal);
		frame.setSize(300, 200);
		
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				frame.setTitle(labelTitle.getText());
				frame.setSize(Integer.parseInt(labelWidth.getText()), Integer.parseInt(labelHeight.getText()));	
				
				if(check.isSelected()) {
					frame.setLocationRelativeTo(null);
					System.out.println(check.isEnabled());
				}
			}
		});
		
	}
	
	public void open() {
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Frame window = new Frame();
		window.open();
	}

}