import java.awt.Color;
import java.awt.Graphics;

/**
 * A rectangle-shaped Shape
 * Defined by an upper-left corner (x1,y1) and a lower-right corner (x2,y2)
 * with x1<=x2 and y1<=y2
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author CBK, updated Fall 2016
 */
public class Rectangle implements Shape {
	// TODO: YOUR CODE HERE

	private int x1, y1, x2, y2;		// upper left and lower right
	private int width;
	private int height;
	private Color color;


	public Rectangle(int x1, int y1, Color color) {
		this.x1 = x1; this.x2 = x1;
		this.y1 = y1; this.y2 = y1;
		this.color = color;
	}


	/**
	 * An ellipse defined by two corners
	 */
	public Rectangle(int x1, int y1, int x2, int y2, Color color) {
		this.x1 = x1; this.x2 = x2;
		this.y1 = y1; this.y2 = y2;

		this.height= y2 - y1;
		this.width= x2 - x1;

		this.color = color;
	}

	@Override
	public void moveBy(int dx, int dy) {
		x1 += dx; y1 += dy;
		x2 += dx; y2 += dy;
	}

	public void setCorners(int x1, int y1, int x2, int y2) {
		// Ensure correct upper left and lower right
		this.x1 = Math.min(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2);
		this.y2 = Math.max(y1, y2);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}
		
	@Override
	public boolean contains(int x, int y) {

//		double a = (x2-x1)/2.0, b = (y2-y1)/2.0;
//		double dx = x - (x1 + a); // horizontal distance from center
//		double dy = y - (y1 + b); // vertical distance from center
//
//		// Apply the standard geometry formula. (See CRC, 29th edition, p. 178.)
//		return Math.pow(dx / a, 2) + Math.pow(dy / b, 2) <= 1;
		return x >= x1 && x<= x2 && y>= y1 && y <= y2;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);

		g.fillRect(x1, y1, width, height);
	}


	public String toString() {
		return "rectangle "+x1+" "+y1+" "+width+" "+height+" "+color.getRGB();
	}
}
