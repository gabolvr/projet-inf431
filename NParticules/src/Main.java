import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		// Blocking Queue that stores the future states to display (buffer between ParticleSystem and Display)
		LinkedBlockingQueue<SystemState> future_states = new LinkedBlockingQueue<SystemState>();
		
		// Graphic window that will display the system of particles
		Display display = new Display(future_states);
		
		// List of particles 
		ArrayList<Particle> particles = new ArrayList<Particle>();
		
		// Ex 1 : Solar system
		// Heavy particle that works like a sun
		particles.add(new Particle(1000000, new Vector2D(400, 400), new Vector2D()));
		// Lighter particles that will orbit around the sun
		particles.add(new Particle(10, new Vector2D(400, 200), new Vector2D(80,0)));
		particles.add(new Particle(10, new Vector2D(400, 600), new Vector2D(-80,0)));
		particles.add(new Particle(10, new Vector2D(600, 600), new Vector2D(-70,15)));
		
		ParticleSystem system = new ParticleSystem(particles, future_states);
		
		// Ex 2 : System with 20 random particles
		//ParticleSystem system = new ParticleSystem(20, future_states);
		
		// Starting the simulation and the display
		display.start();
		system.start();
	}
}
