package client;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import client.Client;

public class Task {

	private BufferedImage image;
	private BufferedImage subImage;
	private int rotation;
	private ArrayList<Point> pointsList;

	public Task(BufferedImage image, BufferedImage subImage, int rotation) {
		this.image = image;
		this.subImage = subImage;
		this.rotation = rotation;
		pointsList = new ArrayList<Point>();
	}
	
	public void addPoints(Point point) {
		pointsList.add(point);
	}
	
}
