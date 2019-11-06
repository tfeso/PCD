package client;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Task {

	private File image;
	private File subImage;
	private int rotation;
	private ArrayList<Point> pointsList;

	public Task(File image, File subImage, int rotation) {
		this.image = image;
		this.subImage = subImage;
		this.rotation = rotation;
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
	
}
