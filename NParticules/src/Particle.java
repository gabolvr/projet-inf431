import java.awt.*;

public class Particle {
	
	public static final int DELTAT = 10;
	public static final double G_CONST = 6.67408E-11;
	
	private Vector2D position, new_position;
	private Vector2D velocity, new_velocity;
	private double mass;
	
	public Particle() {
		this.mass = 1.0E5;
		this.position = new Vector2D(true);
	}
	
	public Particle(Vector2D position) {
		this.mass = 1.0E5;
		this.position = position;
	}
	
	public Particle(double mass, Vector2D position) {
		this.mass = mass;
		this.position = position;
	}
	
	public Particle(double mass, Vector2D position, Vector2D velocity) {
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
	}
	
	public void updatePosition() {
		position = new_position;
	}
	
	public Vector2D getPosition() {
		return position;
	}
	
	public Vector2D getVelocity() {
		return velocity;
	}
	
	public void updateVelocity() {
		velocity = new_velocity;
	}
	
	public void calculatePosition() {
		new_position = position.plus(velocity.times(DELTAT));
	}
	
	/*public Vector2D gravitationalForceBy(Particle p) {
		if(p == this)
			return new Vector2D(0);
		if(position.equals(p.getPosition()))
			return;
		Math.
	}*/
	
	public Point point() {
		return new Point((int) Math.round(position.x()), (int) Math.round(position.y()));
	}
}
