package client;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {

	private ArrayList<Integer> rotationList;
	private ArrayList<BufferedImage> images;
	private ArrayList<Task> tasksList;
	private BufferedImage subImage;
	private HashMap<String, ArrayList<Point>> resultMap;

	public Order(ArrayList rotationList, ArrayList<BufferedImage> images, BufferedImage subImage) {
		this.rotationList = rotationList;
		this.images = images;
		this.subImage = subImage;
		tasksList = new ArrayList<Task>();
		resultMap = new HashMap<String, ArrayList<Point>>();
		createTasks();
	}

	private ArrayList<Task> createTasks() {

		System.out.println("Rotations: " + rotationList.size());
		System.out.println("Images: " + images.size());
		for(BufferedImage bi : images) {
			for(int r : rotationList) {
				tasksList.add(new Task(bi, subImage, r));
			}
			resultMap.put(bi.toString(), new ArrayList<Point>());
		}
		System.out.println("Number of Tasks: " + tasksList.size());
		return tasksList;
	}
	
	private void printArrayList() {

		for (String key : resultMap.keySet()) {
            
            ArrayList<Point> listOfPoints = resultMap.get(key);
            System.out.println("Key: " + key);
            for(Point p : listOfPoints) {
            	System.out.println("Px: " + p.x + " Py: " + p.y);
            }
     }
	}
}
