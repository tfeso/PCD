import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Task {

	private Client client;
	private BufferedImage image;
	private BufferedImage subImage;
	private int rotation;
	private ArrayList<Point> pointsList;
	
	public Task(Client client, BufferedImage image, BufferedImage subImage, int rotation) {
		
		this.client = client;
		this.image = image;
		this.subImage = subImage;
		this.rotation = rotation;
		pointsList = new ArrayList<Point>();
	}
	
	
	
	
}
