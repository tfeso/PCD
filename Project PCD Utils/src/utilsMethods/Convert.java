package utilsMethods;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Convert {

	public static BufferedImage fileToBufferedImage(File imageFile) throws IOException {

		BufferedImage image = ImageIO.read(imageFile);
		return image;

	}

	public static byte[] BufferedImageToArray(BufferedImage image) throws IOException {
		// convert BufferedImage to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		baos.flush();
		byte[] img = baos.toByteArray();
		baos.close();
		return img;
	}


	public static BufferedImage bytearrayToBufferedImage(byte[] img) throws IOException {
		// convert byte array to BufferedImage
		InputStream in = new ByteArrayInputStream(img);
		BufferedImage bImageFromConvert;
		try {
			bImageFromConvert = ImageIO.read(in);
			//ImageIO.write(bImageFromConvert, "png", new File("/Users/tsimao/git/PCD/Project PCD 2019/newImages/out.jpg"));
			return bImageFromConvert;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static byte [] FileToByteArray(File file) {
		
		try {
			return BufferedImageToArray(fileToBufferedImage(file));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void bufferedImageToFIle(BufferedImage bufferedImage, String extension, String path) {
		try {
			ImageIO.write(bufferedImage, extension, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
