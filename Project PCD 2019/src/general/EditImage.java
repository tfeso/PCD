package general;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class EditImage {


	public static void drawRectangule(BufferedImage image, int x, int y, int width, int height, Color color) {
		// Desenhar rectângulo sobre imagem
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(color);
		g2d.drawRect(x, y, width, height);
		g2d.dispose(); 
	}

	public static ArrayList<Point> findSubImageinImage(int rotation, BufferedImage image, BufferedImage subImage) throws IOException {

		BufferedImage subImageAux = rotateSubImage(subImage, rotation);
		ArrayList<Point> pointsList = new ArrayList<Point>();

		for(int i = 0; i < image.getWidth() - subImageAux.getWidth(); i++) {
			for(int j = 0; j < image.getHeight() - subImageAux.getHeight(); j++) {

				if(bufferedImagesEqual(image.getSubimage(i, j, subImageAux.getWidth(), subImageAux.getHeight()), subImageAux)) {
					pointsList.add(new Point(i,j));
				}
			}
		}
		if(pointsList.size() == 0) {
			return null;
		}else {
			return pointsList;
		}
	}

	private static boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
		if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
			for (int x = 0; x < img1.getWidth(); x++) {
				for (int y = 0; y < img1.getHeight(); y++) {
					if (img1.getRGB(x, y) != img2.getRGB(x, y))
						return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	private static BufferedImage rotateSubImage(BufferedImage img, int rotation) throws IOException {

		BufferedImage rotated = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		Graphics2D graphic = rotated.createGraphics();
		graphic.rotate(Math.toRadians(rotation), img.getWidth()/2, img.getHeight()/2);
		graphic.drawImage(img, null, 0, 0);
		graphic.dispose();

		return rotated;
	}

}