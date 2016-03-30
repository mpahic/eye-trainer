package org.cloudcog.training.eye;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Timer;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main 
{
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	static Timer timer; 
    public static void main( String[] args )
    {
    	setTrayIcon();
    	runScheduler();
    	
    }

	private static void runScheduler() {
		log.info("Starting scheduler");
		timer = new Timer();
		timer.schedule(new StartTask(), 3600000, 3600000);
	}

	private static void setTrayIcon() {
		if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        
        URL imageResource = Main.class.getClassLoader().getResource("org/cloudcog/training/eye/icons/eye.png");
        
        final TrayIcon trayIcon =
                new TrayIcon(new ImageIcon(imageResource).getImage());
                
        final SystemTray tray = SystemTray.getSystemTray();
       
        // Create a pop-up menu components
        MenuItem runApp = new MenuItem("Run now");
        runApp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Main.runAppNow();
				
			}
		});
        MenuItem aboutItem = new MenuItem("About");

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Main.closeApplication();
				
			}
		});
       
        //Add components to pop-up menu
        popup.add(runApp);
        popup.addSeparator();
        popup.add(aboutItem);
        popup.add(exitItem);
       
        trayIcon.setPopupMenu(popup);
       
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
		
	}
	
	protected static void runAppNow() {
		new StartTask().run();
		
	}

	private static void closeApplication() {
		log.info("Closing the app");
		if(timer != null) {
			timer.cancel();
		}
		System.exit(0);
	}
}
