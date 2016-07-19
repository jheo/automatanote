
public class Stroke extends StrokeArea {
	private String strokeLine;
	private int length;
	
	public static final Stroke BACK = new Stroke();
	public static final Stroke ENTER = new Stroke();
	public static final Stroke SPACE = new Stroke();
	
	public Stroke() {
		super();
		strokeLine = new String();
		length = 0;
	}
	
	public Stroke clone() {
		return new Stroke(super.clone(), strokeLine, length);
	}
	
	public Stroke(StrokeArea sArea, String strokeLine, int length) {
		super(sArea);
		this.strokeLine = strokeLine;
		this.length = length;
	}
	
	public Stroke(int minX, int minY, int maxX, int maxY, String strokeLine, int length) {
		super(minX, minY, maxX, maxY);
		this.strokeLine = strokeLine;
		this.length = length;
	}
	
	public Stroke(Stroke s) {
		super(s.getStrokeArea());
		this.strokeLine = s.getLine();
		this.length = s.getLength();
	}
	
	public void setLine(String line) {
		this.strokeLine = line;
	}
	
	public String getLine() {
		return new String (strokeLine);
	}
	
	public int getLength() {
		return length;
	}
	
	public StrokeArea getStrokeArea() {
		return new StrokeArea(minX, minY, maxX, maxY);
	}
	
	public void addStartSymbol() {
		strokeLine = "0" + strokeLine;
	}
	
	public void concat(Stroke anotherStroke) {
		checkAndSet(anotherStroke.getStrokeArea());
		strokeLine += "0"+anotherStroke.getLine();
	}
	
	public Pattern getPattern() {
		return new Pattern(getLine(), getShape());
	}
	
	public String toString() {
		return super.toString() + "\n" + strokeLine + "\n";
	}
	
}
