import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 */
public class Polyline implements Shape {
	// TODO: YOUR CODE HERE
	private ArrayList<Point> points;
	private Color color;

	public Polyline(){

	}

	public Polyline(ArrayList<Point> points, Color color){
		this.points=points;
		this.color=color;
	}

	@Override
	public void moveBy(int dx, int dy) {
		for (Point point: points){
			point.translate(dx,dy);
		}
	}

	public  void addPoint(int x, int y){
		points.add(new Point(x,y));
	}

	@Override
	public Color getColor() {

		return color;

	}

	@Override
	public void setColor(Color color) {
		this.color=color;
	}
	
	@Override
	public boolean contains(int x, int y) {
		for (int i=0; i< points.size()-1;i++){
			if (Segment.pointToSegmentDistance(x,y,(int) points.get(i).getX(),(int) points.get(i).getY(),(int) points.get(i+1).getX(),(int)points.get(i).getY())<4){
				return true;
			}
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawPolyline(points.stream().mapToInt(point->(int) point.getX()).toArray(),points.stream().mapToInt(point->(int) point.getY()).toArray(),points.size());
	}

	@Override
	public String toString() {
		String pointsStr="";
		for (Point point: points){
			pointsStr+=((int) point.getX())+" "+((int)point.getY())+"";
		}
		return "polyline " + pointsStr+ color.getRGB();
	}
}
