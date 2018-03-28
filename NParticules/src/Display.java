import java.awt.*;
import javax.swing.*;

public class Display extends Thread {
	
	private JFrame mainFrame;
	private JPanel mainPanel;
	private SystemJPanel systemPanel;
	
	public final static int SIZE = 500;
	
	public Display() {
		initializePanel();
		start();
	}
	
	private void initializePanel() {
		mainFrame = new JFrame("N Particles Simulation");
		mainFrame.setSize(SIZE, SIZE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new BorderLayout());
		systemPanel = new SystemJPanel();
		systemPanel.setBackground(Color.WHITE);
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.add(systemPanel, BorderLayout.CENTER);
		mainFrame.setVisible(true);
	}
	
	@Override
	public void run() {
		try {
			while(true) {

				Thread.sleep(Particle.DELTAT);
				displayParticles();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void displayParticles() {
		systemPanel.clearPoints();
		systemPanel.addPoint(new Particle().point());
		systemPanel.addPoint(new Particle().point());
		systemPanel.repaint();
	}
}
