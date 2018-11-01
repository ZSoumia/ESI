
public class Point implements Comparable{
	
	public double x, y ,distanceFromConsideredPoint =0;
	public String pointClass;
	
	public Point(double x , double y) {
		this.x = x;
		this.y = y;
	}
	public Point(double x,double y,String pclass) {
		this.x = x;
		this.y = y;
		this.pointClass = pclass;
	}
	public String getPointClass() {
		return pointClass;
	}

	public void setPointClass(String pointClass) {
		this.pointClass = pointClass;
	}

	public double getDistanceFromConsideredPoint() {
		return distanceFromConsideredPoint;
	}

	public void setDistanceFromCenter(double d) {
		this.distanceFromConsideredPoint = d;
	}
	@Override
	public String toString() {
		return " point ("+this.x+" , "+this.y+" ) and class = "+this.pointClass+" and distance = "+this.getDistanceFromConsideredPoint();
	}

	@Override
	public int compareTo(Object o) {
		if((((Point) o).distanceFromConsideredPoint - this.getDistanceFromConsideredPoint())< 0) 
			return 1;
		else return -1;
	}
	

	
	
}
