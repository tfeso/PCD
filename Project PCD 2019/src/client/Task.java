package client;
import java.awt.Point;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Task implements Serializable {

	private Order order;
	private File image;
	private File subImage;
	private int rotation;
	private ArrayList<Point> pointsList;

	public Task(File image, File subImage, int rotation, Order order) {
		this.image = image;
		this.subImage = subImage;
		this.rotation = rotation;
		this.order = order;
		pointsList = new ArrayList<Point>();
	}
	
	public void addPoints(Point point) {
		pointsList.add(point);
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public File getSubImage() {
		return subImage;
	}
	
	public File getImage() {
		return image;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public ArrayList<Point> getPointsList() {
		return pointsList;
	}
	
}