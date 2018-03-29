import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

// Panel to display the system of particles

public class SystemJPanel extends JPanel {
	
	ArrayList<Point> points = new ArrayList<Point>();
	
	// Adds a point to the list
	public void addPoint(Point p) {
		points.add(p);
	}
	
	// Remove all the points from the lsit
	public void clearPoints() {
		points.clear();
	}
	
	// Display all the points of the list
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < points.size(); i++) {
			if (points.get(i) != null)
				drawPoint(points.get(i), g);
		}
	}
	
	// Display one point
	private void drawPoint(Point p, Graphics g) {
		int r = 2;
		int u = p.x - r;
		int v = p.y - r;
		g.drawOval(u, v, 2 * r, 2 * r);
		g.fillOval(u, v, 2 * r, 2 * r);
	}
	
	
}
