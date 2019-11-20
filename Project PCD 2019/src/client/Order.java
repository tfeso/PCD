package client;
import java.awt.Point;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import general.Barrier;
import general.OrderBarrier;

public class Order implements Serializable {

	private ArrayList<Integer> rotationList;
	private ArrayList<File> images;
	private ArrayList<Task> tasksList;
	private File subImage;
	private HashMap<String, ArrayList<Point>> resultMap;
	private UUID id;

	public Order(ArrayList<Integer> rotationList, ArrayList<File> images, File subImage) {
		this.rotationList = rotationList;
		this.images = images;
		this.subImage = subImage;
		tasksList = new ArrayList<Task>();
		resultMap = new HashMap<String, ArrayList<Point>>();
		createTasks();
		id = UUID.randomUUID();
	}

	private ArrayList<Task> createTasks() {

		System.out.println("Rotations: " + rotationList.size());
		System.out.println("Images: " + images.size());
		for(File fImage : images) {
			for(int r : rotationList) {
				tasksList.add(new Task(fImage, subImage, r, this));
			}
			resultMap.put(fImage.getName(), new ArrayList<Point>());
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

	public HashMap<String, ArrayList<Point>> getResultMap() {
		return resultMap;
	}

	public ArrayList<Task> getTasksList() {
		return tasksList;
	}

	public void addPointToMap(String imageName, ArrayList<Point> pointsList) {
		if(resultMap.containsKey(imageName)) {
			for(Point p : pointsList) {
				resultMap.get(imageName).add(p);
			}
		}
	}
	
	public File getFileByName(String name) {
		for(File f : images) {
			if(f.getName().equals(name)) {
				return f;
			}
		}
		return null;
	}
	 public UUID getId() {
		return id;
	}
}