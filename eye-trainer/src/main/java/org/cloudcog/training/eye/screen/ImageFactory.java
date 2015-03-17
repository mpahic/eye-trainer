package org.cloudcog.training.eye.screen;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class ImageFactory {

	private static ArrayList<String> imageStrings = new ArrayList<String>();
	static {
		imageStrings.add("org/cloudcog/training/eye/icons/soccer-ball.png");
		imageStrings.add("org/cloudcog/training/eye/icons/base-ball.png");
		imageStrings.add("org/cloudcog/training/eye/icons/basket-ball.png");
		imageStrings.add("org/cloudcog/training/eye/icons/egg-ball.png");
		imageStrings.add("org/cloudcog/training/eye/icons/tennis-ball.png");
	}

	public static ImageIcon getRandomImage() {
		URL resource = ImageFactory.class.getClassLoader().getResource(getRandomString());
    	return new ImageIcon(resource);

	}

	private static String getRandomString() {
		Random randomizer = new Random();
		return imageStrings.get(randomizer.nextInt(imageStrings.size()));

	}
}
