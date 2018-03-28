import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SystemJPanel extends JPanel {
	
	ArrayList<Point> points = new ArrayList<Point>();
	
	public void addPoint(Point p) {
		points.add(p);
		System.out.print(points.size());
	}
	
	public void clearPoints() {
		points.clear();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Point p : points) {
			drawPoint(p, g);
		}
	}

	private void drawPoint(Point p, Graphics g) {
		int r = 2;
		int u = p.x - r;
		int v = p.y - r;
		g.drawOval(u, v, 2 * r, 2 * r);
		g.fillOval(u, v, 2 * r, 2 * r);
	}
	
	
}
