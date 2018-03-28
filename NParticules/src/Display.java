import java.awt.*;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.*;

public class Display extends Thread {
	
	private JFrame mainFrame;
	private JPanel mainPanel;
	private SystemJPanel systemPanel;
	private JLabel timeCounter;
	private LinkedBlockingQueue<SystemState> future_states;
	public final static int SIZE = 800;
	public final static int DISPLAY_TIME = 1;
	
	public Display(LinkedBlockingQueue<SystemState> future_states) {
		this.future_states = future_states;
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
		
		timeCounter = new JLabel("time = ");
		timeCounter.setOpaque(true);
		timeCounter.setBackground(Color.WHITE);
		mainFrame.add(timeCounter, BorderLayout.PAGE_END);
		timeCounter.setHorizontalAlignment(SwingConstants.CENTER);
		
		mainFrame.setVisible(true);
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				SystemState state = future_states.take();
				displayParticles(state);
				timeCounter.setText("time = " + state.time);
				Thread.sleep(DISPLAY_TIME);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void displayParticles(SystemState state) {
		systemPanel.clearPoints();
		for(Point p : state.points)
			systemPanel.addPoint(p);
		systemPanel.repaint();
	}
}
