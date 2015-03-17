package org.cloudcog.training.eye.screen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;


public class SpiralingRectanglesAnimation extends JPanel implements ActionListener {
	private static final long serialVersionUID = 7954300497789625771L;
	
	private LabelPosition currentPosition = LabelPosition.LEFT;
	private Integer x,y;
	private final ArrayList<Line> lines = new ArrayList<Line>();
	
	private static final int PADDING_IMAGE = 10;
	private static int passingCounter = 1;
	
	private enum LabelPosition {LEFT, UP, RIGHT, DOWN};

    public SpiralingRectanglesAnimation() {
    	Timer timer = new Timer(200, this);
        timer.setInitialDelay(190);
        timer.start();
        
    }

	private void setnextPostion() {
		if(x == null || y == null) {

	        x = Math.round(this.getWidth() / 2);
	        y = Math.round(this.getHeight() / 2);
		}
		switch (currentPosition) {
		case LEFT:
			lines.add(new Line(x, y, x - PADDING_IMAGE * passingCounter * 2 + 2 * PADDING_IMAGE, y));
			x= x - PADDING_IMAGE * passingCounter * 2 + 2 * PADDING_IMAGE;
			currentPosition = LabelPosition.UP;
			break;

		case UP:
			lines.add(new Line(x, y, x, y - PADDING_IMAGE * passingCounter * 2 + 2 * PADDING_IMAGE));
			y= y - PADDING_IMAGE * passingCounter * 2 +  2 * PADDING_IMAGE;
			currentPosition = LabelPosition.RIGHT;
			break;

		case RIGHT:
			lines.add(new Line(x, y, x + PADDING_IMAGE * passingCounter * 2 - PADDING_IMAGE, y));
			x= x + PADDING_IMAGE * passingCounter * 2 - PADDING_IMAGE;
			currentPosition = LabelPosition.DOWN;
			break;
			
		case DOWN:
			lines.add(new Line(x, y, x, y + PADDING_IMAGE * passingCounter * 2 - PADDING_IMAGE));
			y= y + PADDING_IMAGE * passingCounter * 2 - PADDING_IMAGE;
			currentPosition = LabelPosition.LEFT;
			passingCounter++;
			break;
		}
		
	}

    public Dimension getPreferredSize() {
        return new Dimension(250,250);
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        for(final Line r : lines) {
            r.paint(g);
        }
    } 

	public void actionPerformed(ActionEvent e) {
		setnextPostion();
		repaint();
		
	}
	private class Line {
	    public final int x1;
	    public final int x2;
	    public final int y1;
	    public final int y2;
	    public Line(int x1, int y1, int x2, int y2) {
	        this.x1 = x1;
	        this.x2 = x2;
	        this.y1 = y1;
	        this.y2 = y2;
	    }
	    public void paint(Graphics g) {
	        g.drawLine(this.x1, this.y1, this.x2, this.y2);
	    }
	}
}
