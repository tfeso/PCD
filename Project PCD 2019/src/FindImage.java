import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class FindImage {

	private File image;
	private File subImage0;
	private File subImage90;
	private File subImage180;
	private File subImage270;

	public FindImage() {
		image = new File("/Users/tsimao/git/PCD/Project PCD 2019/./img/out/image7_0-90-180-270.png");
		subImage0 = new File("/Users/tsimao/git/PCD/Project PCD 2019/./img/Superman.png");
		subImage90 = new File("/Users/tsimao/git/PCD/Project PCD 2019/./img/Superman.png");
		subImage180 = new File("/Users/tsimao/git/PCD/Project PCD 2019/./img/Superman.png");
		subImage270 = new File("/Users/tsimao/git/PCD/Project PCD 2019/./img/Superman.png");
	}

	private BufferedImage fileToBufferedImage(File imageFile) throws IOException {

		BufferedImage image = ImageIO.read(imageFile);
		return image;

	}

	private byte[] BufferedImageToArray(BufferedImage image) throws IOException {
		// convert BufferedImage to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		baos.flush();
		byte[] img = baos.toByteArray();
		baos.close();
		return img;
	}


	private BufferedImage bytearrayToBufferedImage(byte[] img) throws IOException {
		// convert byte array to BufferedImage
		InputStream in = new ByteArrayInputStream(img);
		BufferedImage bImageFromConvert;
		try {
			bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, "png", new File("/Users/tsimao/git/PCD/Project PCD 2019/newImages/out.jpg"));
			return bImageFromConvert;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}

	private void drawRectangule(BufferedImage image, int x, int y, int width, int height) {
		//desenhar rectângulo sobre imagem
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(Color.RED);
		g2d.drawRect(x, y, width, height);
		g2d.dispose(); 
	}

	private void findSubImage(int rotation) throws IOException {

		ArrayList<BufferedImage> listBf = new ArrayList<BufferedImage>();
		BufferedImage img = fileToBufferedImage(image);
		listBf.add(rotateSubImage(fileToBufferedImage(subImage0), 0));
		listBf.add(rotateSubImage(fileToBufferedImage(subImage90), 90));
		listBf.add(rotateSubImage(fileToBufferedImage(subImage180), 180));
		listBf.add(rotateSubImage(fileToBufferedImage(subImage270), 270));

		for(int i = 0; i < img.getWidth() - listBf.get(0).getWidth(); i++) {
			for(int j = 0; j < img.getHeight() - listBf.get(0).getHeight(); j++) {

				for(BufferedImage bf : listBf) {
					if(bufferedImagesEqual(img.getSubimage(i, j, bf.getWidth(), bf.getHeight()), bf)) {
						drawRectangule(img, i, j, bf.getWidth(), bf.getHeight());
					}
				}
			}
		}
		ImageIO.write(img, "png", new File("/Users/tsimao/git/PCD/Project PCD 2019/newImages/out2.png"));
	}

	private boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
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

	private BufferedImage rotateSubImage(BufferedImage img, int rotation) throws IOException{

		BufferedImage rotated = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		Graphics2D graphic = rotated.createGraphics();
		graphic.rotate(Math.toRadians(rotation), img.getWidth()/2, img.getHeight()/2);
		graphic.drawImage(img, null, 0, 0);
		graphic.dispose();

		ImageIO.write(rotated, "png", new File("/Users/tsimao/git/PCD/Project PCD 2019/newImages/out.png"));
		return rotated;
	}

	public static void main(String[] args) throws IOException {

		FindImage findImage = new FindImage();
		File subImage = new File("/Users/tsimao/git/PCD/Project PCD 2019/./img/Superman.png");
		findImage.findSubImage(0);
	}
}
