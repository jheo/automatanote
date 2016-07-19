import java.awt.Graphics;
import java.awt.Point;

public class StrokeArea {
	protected int minX, minY, maxX, maxY;
	public static final int SHAPEDIFF = 20;
	
	public static final String SEPERATED = "SP";
	public static final String INCLUDE = "INC";
	public static final String OVER = "OV";
	
	public StrokeArea () {
		minX = Integer.MAX_VALUE;
		minY = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		maxY = Integer.MIN_VALUE;
	}

	public StrokeArea (int minX, int minY, int maxX, int maxY) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	public StrokeArea clone() {
		return new StrokeArea (minX, minY, maxX, maxY);
	}
	
	public StrokeArea (StrokeArea s) {
		this(s.getMinX(), s.getMinY(), s.getMaxX(), s.getMaxY());
	}
	
	public String compareArea(StrokeArea s) {
		int sMinX = s.getMinX();
		int sMinY = s.getMinY();
		int sMaxX = s.getMaxX();
		int sMaxY = s.getMaxY();
		System.out.println("Current Stroke : " +this);
		System.out.println("Another Stroke : "+ s);
		if (minX>sMaxX || minY>sMaxY || sMinX > maxX || sMinY > maxY)
			return SEPERATED;
		else if ((minX < sMinX && maxX > sMaxX && minY < sMinY && maxY > sMaxY) || (sMinX < minX && sMaxX > maxX && sMinY < minY && sMaxY > maxY))
			return INCLUDE;
		else
			return OVER;
	}
	
	public boolean compareVowelArea(StrokeArea s) {
		return (s.centerPoint().x > maxX || s.centerPoint().y > maxY);
	}
	
	public void printRaito(){
		System.out.println(getRaito());
	}
	
	public float getRaito() {
		return (float) ((maxX - minX) + 1) / ((float) (maxY - minY) + 1);
	}
	
	public void checkAndSet (int minX, int minY, int maxX, int maxY) {
		this.minX = Math.min(this.minX, minX);
		this.minY = Math.min(this.minY, minY);
		this.maxX = Math.max(this.maxX, maxX);
		this.maxY = Math.max(this.maxY, maxY);
	}
	
	public void checkAndSet (Point p) {
		this.minX = Math.min(this.minX, p.x);
		this.minY = Math.min(this.minY, p.y);
		this.maxX = Math.max(this.maxX, p.x);
		this.maxY = Math.max(this.maxY, p.y);
	}
	
	public void checkAndSet (StrokeArea s) {
		checkAndSet(s.getMinX(), s.getMinY(), s.getMaxX(), s.getMaxY());
	}
	
	public Point centerPoint () {
		return new Point (minX + (maxX - minX)/2, minY + (maxY - minY)/2);
	}
	
	public int getMinX() {
		return minX;
	}
	
	public int getMinY() {
		return minY;
	}
	
	public int getMaxX() {
		return maxX;
	}
	
	public int getMaxY() {
		return maxY;
	}
	
	public StrokeArea getArea() {
		return new StrokeArea(minX, minY, maxX, maxY);
	}
	
	public boolean isEmpty() {
		return (maxX == Integer.MAX_VALUE);
	}
	
	public String getShape() {
		if ((maxX - minX) > (maxY - minY) + SHAPEDIFF) {
			return Pattern.WIDTHWISE;
		} else if ((maxY - minY) > (maxX - minX) + SHAPEDIFF) {
			return Pattern.LENGTHWISE;	
		} else
			return Pattern.SQUARE;
	}
	
	public void drawArea(Graphics g) {
		g.drawRect(minX, minY, maxX - minX, maxY - minY);
	}
	
	public String toString() {
		return "minX : "+minX+" / maxX : "+maxX+" / minY : "+minY+" / maxY :"+maxY;
	}
}
