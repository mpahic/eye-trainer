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

/**
 * Hello world!
 *
 */
public class Main 
{
	static Timer timer; 
    public static void main( String[] args )
    {
    	setTrayIcon();
    	runScheduler();
    	
    }

	private static void runScheduler() {
		timer = new Timer();
    	timer.schedule(new StartTask(), 3600000);
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
		if(timer != null) {
			timer.cancel();
		}
		runScheduler();
		new StartTask().run();
		
	}

	private static void closeApplication() {
		if(timer != null) {
			timer.cancel();
		}
		System.exit(0);
	}
}
