package org.cloudcog.training.eye;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.cloudcog.training.eye.screen.AnimationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartTask extends TimerTask {
	private static final Logger log = LoggerFactory.getLogger(StartTask.class);

    private static JFrame f;
    private static void createAndShowGUI() throws InstantiationException, IllegalAccessException {

		f = new JFrame("Eye trainer");
        JPanel panel = AnimationFactory.getRandomAnimationPanel().newInstance();
        f.add(panel);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true);

        Timer timer = new Timer(30000, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(f!= null && f.isActive()) {
					f.dispose();
				}
				
			}
		});
        timer.start();
        f.setVisible(true);
        f.toFront();
    }

	@Override
	public void run() {
		log.info("Starting new training");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
                    createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
		
	}
}
