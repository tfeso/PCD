import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Visualizador {
	
	private JFrame frame;
	
	public Visualizador() {
		frame = new JFrame("Photos");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
	}

	private void addFrameContent() {

								
	}
	
	public void open() {
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Visualizador window = new Visualizador();
		window.open();
	}

}
