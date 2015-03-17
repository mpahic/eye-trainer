package org.cloudcog.training.eye.screen;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class AnimationFactory {

	private static ArrayList<Class<? extends JPanel>> panels = new ArrayList<Class<? extends JPanel>>();
	static {
		panels.add(FlowAnimationPanel.class);
		panels.add(SkippingAnimationPanel.class);
		panels.add(SpiralingRectanglesAnimation.class);
	}
	
	public static Class<? extends JPanel> getRandomAnimationPanel() {
		Random randomizer = new Random();
		return panels.get(randomizer.nextInt(panels.size()));

	}
}
