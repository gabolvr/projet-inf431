import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

// Class that controls the system of particles, using a Thread 

public class ParticleSystem extends Thread {
	
	// List of particles
	private ArrayList<Particle> particles;
	private int numParticles;
	// The current time of the system in calculation
	private int time;
	// Blocking Queue that stores the future states to display
	// Works as a buffer or message channel between the calculation of the states (ParticleSystem) and the Display
	private LinkedBlockingQueue<SystemState> future_states;
	// Counter that indicates how many threads are still working, to be used by the synchronization barrier
	public int particlesToWait;
	
	// Constructors
	
	public ParticleSystem(int n, LinkedBlockingQueue<SystemState> future_states) {
		numParticles = n;
		time = 0;
		particles = new ArrayList<Particle>();
		for (int i = 0; i < n; i++)
			addParticle();
		this.future_states = future_states;
	}
	
	public ParticleSystem(ArrayList<Particle> particles, LinkedBlockingQueue<SystemState> future_states) {
		numParticles = particles.size();
		time = 0;
		this.particles = particles;
		for(Particle p : particles)
			p.setSystem(this, particles);
		this.future_states = future_states;
	}
	
	// Methods to add a particle to the system
	
	public void addParticle(Particle p) {
		numParticles++;
		particles.add(p);
		p.setSystem(this, particles);
	}
	
	public void addParticle() {
		numParticles++;
		Particle p = new Particle();
		particles.add(p);
		p.setSystem(this, particles);
	}
	
	// Method that return the number of particles in the system
	public int getNumParticles() {
		return numParticles;
	}
	
	// Method run for the Thread of the System
	@Override
	public void run() {
		SystemState currentState;
		try {
			while(true) {
				// Get the current state and put it in the buffer
				currentState = new SystemState(particles, time);
				future_states.put(currentState);
				
				// List of threads, one for each particle
				ArrayList<Thread> threads = new ArrayList<Thread>();
				
				// Initialize the variable for the barrier
				particlesToWait = threads.size();
				
				// Start the threads for each particle
				for(Particle p : particles) {
					Thread t = new Thread(p);
					threads.add(t);
					t.start();
				}
				
				// Wait that all the threads have terminated
				for(Thread t : threads)
					t.join();

				time += Display.DISPLAY_TIME;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
