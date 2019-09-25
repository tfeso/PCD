import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Window {

	private JFrame frameMain;
	private JTextField horseOne;
	private JTextField horseTwo;
	private JTextField horseThree;
	private JButton btnRun;
	private Race race;
	
	public Window() {
		race = new Race(this);
		horseOne = new JTextField("90");
		horseOne.setEnabled(false);
		horseTwo = new JTextField("90");
		horseTwo.setEnabled(false);
		horseThree = new JTextField("90");
		horseThree.setEnabled(false);
		btnRun = new JButton("RUN");
		buildWindow();
		buttonsActions();
	}
	
	private void buildWindow() {
		frameMain = new JFrame();
		frameMain.setSize(100, 100);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.setResizable(false);
		frameMain.setLayout(new FlowLayout());
		frameMain.add(horseOne);
		frameMain.add(horseTwo);
		frameMain.add(horseThree);
		frameMain.add(btnRun);
	}
	
	public JFrame getMainFrame() {
		return frameMain;
	}

	
	private void buttonsActions() {
		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				race.RunRace();
				btnRun.setEnabled(false);
			}
		});
	}
	
	public void openWindow() {
		frameMain.setVisible(true);
	}
	
	public void decrement(Cavalo cavalo) {
		switch(cavalo.getIdentificador())
		{
		case(1001):
			horseOne.setText(Integer.toString(cavalo.getMoviments()));
			break;
		case(1002):
			horseTwo.setText(Integer.toString(cavalo.getMoviments()));
			break;
		case(1003):
			horseThree.setText(Integer.toString(cavalo.getMoviments()));
			break;
		}
	}
	
	public static void main(String[] args) {
		Window wind = new Window();
		wind.openWindow();
	}
	
	
}