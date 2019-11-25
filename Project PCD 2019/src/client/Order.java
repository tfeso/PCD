package client;
import java.awt.Point;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Order implements Serializable {

	private ArrayList<Integer> rotationList;
	private ArrayList<File> images;
	private ArrayList<Task> tasksList;
	private File subImage;
	private HashMap<String, ArrayList<Point>> resultMap;
	private UUID idClient;

	public Order(ArrayList<Integer> rotationList, ArrayList<File> images, File subImage, UUID idClient) {
		this.rotationList = rotationList;
		this.images = images;
		this.subImage = subImage;
		this.idClient = idClient;
		tasksList = new ArrayList<Task>();
		resultMap = new HashMap<String, ArrayList<Point>>();
		createTasks();
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

	public void printArrayList() {

		System.out.println("PrintArrayList");
		for (String key : resultMap.keySet()) {

			ArrayList<Point> listOfPoints = resultMap.get(key);
			System.out.println("Key PAL: " + key);
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
		System.out.println("AddPointToMap: " + imageName);
		if(resultMap.containsKey(imageName)) {
			System.out.println("true");
			for(Point p : pointsList) {
				resultMap.get(imageName).add(p);
				System.out.println("[ X: " + p.getX() + " ; " + p.getY() + "]");
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
		return idClient;
	}
}