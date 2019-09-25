import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Window {

	private JFrame frameMain;
	private JButton btnStop;
	private ThreadA tA;
	private ThreadB tB;
	
	public Window(ThreadA tA, ThreadB tB) {
		this.tA = tA;
		this.tB = tB;
		btnStop = new JButton("STOP");
		buildWindow();
		buttonsActions();
		openWindow();
		startThreads();
	}
	
	private void startThreads() {
		tA.start();
		tB.start();
	}

	private void buildWindow() {
		frameMain = new JFrame();
		frameMain.setSize(100, 100);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.setResizable(false);
		frameMain.setLayout(new FlowLayout());
		frameMain.add(btnStop);
	}
	
	public JFrame getMainFrame() {
		return frameMain;
	}

	private void buttonsActions() {
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tA.interrupt();
				tB.interrupt();
			}
		});
	}
	
	public void openWindow() {
		frameMain.setVisible(true);
	}
	
	public static void main(String[] args) {

		ThreadA tA = new ThreadA();
		ThreadB tB = new ThreadB();
		
		Window wind = new Window(tA, tB);

		try {
			tA.join();
			tB.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread A. Registos: " + tA.getCounter());
		System.out.println("Thread B. Registos: " + tB.getCounter());
	}
	
	
}