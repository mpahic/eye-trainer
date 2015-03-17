package org.cloudcog.training.eye.screen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlowAnimationPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 4092195842968355754L;
	
	private ImageIcon image;
	private LabelPosition currentPosition = LabelPosition.INIT;
	private int x = PADDING_IMAGE;
	private int y = PADDING_IMAGE;
	
	private static final int PADDING_IMAGE = 10;
	
	private enum LabelPosition {LEFT, RIGHT, INIT};

    public FlowAnimationPanel() {
    	this.setLayout(new BorderLayout());
    	
    	image = ImageFactory.getRandomImage();
        
    	Timer timer = new Timer(600, this);
        timer.setInitialDelay(190);
        timer.start();
        
    }

	private void setnextPostion() {
		switch (currentPosition) {
		case LEFT:
			x= PADDING_IMAGE;
			y=y+image.getIconHeight();
			if(y +image.getIconHeight() + PADDING_IMAGE > this.getHeight()) {
				y = PADDING_IMAGE;
			}
		case INIT:
			currentPosition = LabelPosition.RIGHT;
			break;

		case RIGHT:
			x=this.getWidth() - (image.getIconWidth() + PADDING_IMAGE);
			currentPosition = LabelPosition.LEFT;
			break;

		}
		
	}

    public Dimension getPreferredSize() {
        return new Dimension(250,250);
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        g.drawImage(image.getImage(), x, y, null);
    } 

	public void actionPerformed(ActionEvent e) {
		repaint();
		setnextPostion();
		
	}
}
