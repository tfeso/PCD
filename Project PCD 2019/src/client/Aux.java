package client;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import general.Convert;
import general.EditImage;

public class Aux {

	private GUI gui;
	private Order order;

	public Aux(GUI gui, Order order) {
		this.gui = gui;
		this.order = order;
		insertPoints();
	}

	public void insertPoints() {

		ArrayList<Point> pointsList = new ArrayList<Point>();
		for(Task t : order.getTasksList()) {

			try {
				pointsList = EditImage.findSubImageinImage(t.getRotation(), Convert.fileToBufferedImage(t.getImage()), Convert.fileToBufferedImage(t.getSubImage()));
				if(pointsList != null) {
					order.addPointToMap(t.getImage().getAbsolutePath(), pointsList);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
