import java.awt.*;
import java.util.ArrayList;

public class SystemState {
	
	public int numParticles;
	public int time;
	
	public ArrayList<Point> points;
	
	public SystemState(ArrayList<Particle> particles, int time) {
		points = new ArrayList<Point>();
		this.time = time;
		for(Particle p : particles) {
			points.add(p.point());
		}
	}
	
	public String toString() {
		String s = "";
		for(Point p : points) {
			s += "(" + p.x + ", " + p.y + ")\n";
		}
		return s;
	}
}
