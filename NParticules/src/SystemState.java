import java.awt.*;
import java.util.ArrayList;

// Class that represents a state of the system of particles at a given time

public class SystemState {
	
	public int numParticles;
	public int time;
	
	// List of points for the points that will be printed
	public ArrayList<Point> points;
	
	// Creates a state in a certain time with a list of particles
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
