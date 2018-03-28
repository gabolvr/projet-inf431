import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		LinkedBlockingQueue<SystemState> future_states = new LinkedBlockingQueue<SystemState>();
		Display display = new Display(future_states);
		ArrayList<Particle> particles = new ArrayList<Particle>();
		particles.add(new Particle(1000000, new Vector2D(400, 400), new Vector2D()));
		particles.add(new Particle(10, new Vector2D(400, 200), new Vector2D(80,0)));
		particles.add(new Particle(10, new Vector2D(400, 600), new Vector2D(-80,0)));
		ParticleSystem system = new ParticleSystem(particles, future_states);
		system.start();
	}
}
