package org.cloudcog.training.eye.screen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SkippingAnimationPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -3021762802427530501L;
	
	private ImageIcon image;
	private LabelPosition currentPosition = LabelPosition.ONE;
	private int x,y = PADDING_IMAGE;
	
	private static final int PADDING_IMAGE = 10;
	
	private enum LabelPosition {ONE, TWO, THREE, FOUR};

    public SkippingAnimationPanel() {
    	this.setLayout(new BorderLayout());
    	
    	image = ImageFactory.getRandomImage();
        
    	Timer timer = new Timer(700, this);
        timer.setInitialDelay(190);
        timer.start();
        
    }

	private void setnextPostion() {
		switch (currentPosition) {
		case ONE:
			x= PADDING_IMAGE;
			y= PADDING_IMAGE;
			currentPosition = LabelPosition.TWO;
			break;

		case TWO:
			x=this.getWidth() - (image.getIconWidth() + PADDING_IMAGE);
			y= PADDING_IMAGE;
			currentPosition = LabelPosition.THREE;
			break;
		case THREE:
			x= PADDING_IMAGE;
			y= this.getHeight() - (image.getIconHeight() + PADDING_IMAGE);
			currentPosition = LabelPosition.FOUR;
			break;
		case FOUR:
			x=this.getWidth() - (image.getIconWidth() + PADDING_IMAGE);
			y=this.getHeight() - (image.getIconHeight() + PADDING_IMAGE);
			currentPosition = LabelPosition.ONE;
			break;
		default:
			currentPosition = LabelPosition.ONE;
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
		setnextPostion();
		repaint();
		
	}
}
