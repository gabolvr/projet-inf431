import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class ParticleSystem extends Thread {
	
	public ArrayList<Particle> particles;
	public int numParticles;
	private int time;
	
	public ArrayList<Thread> particle_threads;
	private LinkedBlockingQueue<SystemState> future_states;
	public int particlesToWait;
	
	public ParticleSystem(int n, LinkedBlockingQueue<SystemState> future_states) {
		numParticles = n;
		time = 0;
		particle_threads = new ArrayList<Thread>();
		particles = new ArrayList<Particle>();
		for (int i = 0; i < n; i++)
			addParticle();
		this.future_states = future_states;
	}
	
	public ParticleSystem(ArrayList<Particle> particles, LinkedBlockingQueue<SystemState> future_states) {
		numParticles = particles.size();
		time = 0;
		this.particles = particles;
		particle_threads = new ArrayList<Thread>();
		for(Particle p : particles) {
			particle_threads.add(new Thread(p));
			p.setSystem(this);
		}
		this.future_states = future_states;
	}
	
	public void addParticle(Particle p) {
		numParticles++;
		particles.add(p);
		p.setSystem(this);
		particle_threads.add(new Thread(p));
	}
	
	public void addParticle() {
		numParticles++;
		Particle p = new Particle();
		particles.add(p);
		p.setSystem(this);
		particle_threads.add(new Thread(p));
	}
	
	@Override
	public void run() {
		SystemState currentState;
		try {
			while(true) {
				currentState = new SystemState(particles, time);
				future_states.put(currentState);
				ArrayList<Thread> threads = new ArrayList<Thread>();
				particlesToWait = threads.size();

				for(Thread pt : particle_threads) {
					//pt.start();
					Thread t = new Thread(pt);
					threads.add(t);
					t.start();
				}

				/*for(Thread pt : particle_threads)
					pt.join();*/
				
				for(Thread t : threads)
					t.join();

				/*for(Particle p : particles)
					p.updateParticle();*/

				time += Display.DISPLAY_TIME;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
