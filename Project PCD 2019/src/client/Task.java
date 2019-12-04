package client;
import java.awt.Point;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable {

	private Order order;
	private CustomImage image;
	private CustomImage subImage;
	private int rotation;
	private ArrayList<Point> pointsList;
	private int numberOfPoints = 0;

	public Task(CustomImage image, CustomImage subImage, int rotation, Order order) {
		this.image = image;
		this.subImage = subImage;
		this.rotation = rotation;
		this.order = order;
		pointsList = new ArrayList<Point>();
	}
	
	public void addPoints(Point point) {
		pointsList.add(point);
		numberOfPoints++;
	}
	
	public void setListPoints(ArrayList<Point> pointsList) {
		this.pointsList = pointsList;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public CustomImage getSubImage() {
		return subImage;
	}
	
	public CustomImage getImage() {
		return image;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public ArrayList<Point> getPointsList() {
		return pointsList;
	}
	
	public int getNumberOfPoints() {
		return numberOfPoints;
	}
	
}