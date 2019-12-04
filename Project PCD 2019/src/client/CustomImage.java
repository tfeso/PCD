package client;

import java.io.Serializable;

public class CustomImage implements Serializable {
	
	private String name;
	private byte [] image;

	public CustomImage(String name, byte [] image) {
		this.name = name;
		this.image = image;
	}

	public byte[] getImage() {
		return image;
	}
	
	public String getName() {
		return name;
	}
}
