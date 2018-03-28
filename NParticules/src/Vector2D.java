
public class Vector2D {
	
	private double x, y;
	
	public Vector2D() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2D(boolean random) {
		this.x = Math.random() * Display.SIZE;
		this.y = Math.random() * Display.SIZE;
	}
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(double k) {
		this.x = k;
		this.y = k;
	}
	
	public double x() {
		return this.x;
	}
	
	public double y() {
		return this.y;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	public double norm() {
		return Math.sqrt(x * x + y * y);
	}
	
	public void add(Vector2D v) {
		this.x += v.x();
		this.y += v.y();
	}
	
	public void subtract(Vector2D v) {
		this.x -= v.x();
		this.y -= v.y();
	}
	
	public Vector2D plus(Vector2D v) {
		return new Vector2D(this.x + v.x(), this.y() + v.y());
	}
	
	public Vector2D minus(Vector2D v) {
		return new Vector2D(this.x - v.x(), this.y() - v.y());
	}
	
	public Vector2D times(double k) {
		return new Vector2D(this.x * k, this.y() * k);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Vector2D))
			return false;
		Vector2D other = (Vector2D) o;
		return x == other.x && y == other.y;
	}
}
