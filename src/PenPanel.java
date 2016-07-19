import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

import java.util.ArrayList;
/**
 * JPanel을 확장한 클래스로, 마우스의 움직임을 Point의 ArrayList로 저장하여 전달하는 역할을 한다. <BR />
 * 이 Panel은 기본적으로 마우스의 드래그 이벤트를 감지하여 이동 경로를 선으로 그려주며, 드래그가 끝날때,
 * PatternChecker 클래스의 checkLine 메소드로 경로를 전달한다. <BR />
 * 
 * @author Heo June
 *
 */

public class PenPanel extends JPanel implements MouseInputListener{
	private static final long serialVersionUID = 20071130L;
	
	private final Color DEFAULT_PEN_COLOR = Color.BLUE;
	
	// Mouse Position
	private Point point;
	private Point prev;
	
	private StrokeArea currentArea;
	private StrokeArea maxArea;
	
	private int length;

	private StrokeQueue strokes;
	private ArrayList <Point> penPath;
	
	private boolean clearFlag;
	private boolean focus;
	
	private int charDiff = 60;
	
	private MainGUI mother;
	
	public PenPanel() {
		strokes = new StrokeQueue ();
		maxArea = new StrokeArea();
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}
	
	public void setMotherGUI (MainGUI m) {
		this.mother = m;
	}
	
	public boolean isFocused() {
		return focus;
	}
	
	public void setFocus(boolean b) {
		focus = b;
	}
	
	public boolean isDirty() {
		return !clearFlag;
	}
	
	public void mouseClicked(MouseEvent e) {
	
	}

	public void mouseEntered(MouseEvent e) {
	
	}

	public void mouseExited(MouseEvent e) {
			
	}

	public void mousePressed(MouseEvent e) {
		
		if (!strokes.isEmpty() && e.getPoint().x>strokes.getMaxArea().getMaxX() + charDiff) {
			recog();
		}
		
		
		
		if (PatternAnalyzer.backtracking(strokes) != "" && charDiff == 60)
			charDiff = 40;
		
		currentArea = new StrokeArea();
		penPath = new ArrayList <Point> ();
		prev = e.getPoint();
		clearFlag = false;
		
		penPath.add(prev);
		
	}

	private void recog() {
		String s;
		charDiff = (strokes.getMaxArea().getMaxX() - strokes.getMaxArea().getMinX()) / 3;
		if (charDiff<20)
			charDiff = 20;
		mother.textPrint(s = PatternAnalyzer.backtracking(strokes));
		mother.debugPrint("Character recognized. result is "+s);
		mother.debugPrint("Setting character interval to "+charDiff+" pixel");
		length = 0;
		clearQueue();
	}
	
	public void clearQueue() {
		strokes = new StrokeQueue();
	}

	public void mouseReleased(MouseEvent e) {
		
		Stroke s = StrokeAnalyzer.checkLineWithoutChainCode(penPath, currentArea, length);
		if (s == Stroke.ENTER){
			mother.textPrint("\n");
			mother.debugPrint("New line gesture detected.");
			clearAll();
			return;
		}
		
		if (s == Stroke.SPACE) {
			mother.textPrint(" ");
			mother.debugPrint("Space gesture detected.");
			return;
		}
		
		if (s == Stroke.BACK) {
			clearAll();
			mother.debugPrint("Clear gesture detected.");
			return;
		}
		
		if (PatternAnalyzer.backtracking(strokes) != "")
			charDiff = (strokes.getMaxArea().getMaxX() - strokes.getMaxArea().getMinX()) / 3;
		
		paintArea(maxArea.getMinX(), maxArea.getMaxX(), maxArea.getMaxX()+charDiff, e.getX());
		if ((strokes.getLastIndex() > 0) && s.getLine().equals("1") && (StrokeAnalyzer.isIncludePosition(strokes.getLastStroke(), s)) ){
					strokes.getLastStroke().concat(s);
					
		}
		else
			strokes.add(s);
		
		
		mother.analysis(this);
	}

	public void mouseDragged(MouseEvent e) {
		
		paintArea(maxArea.getMinX(), maxArea.getMaxX(), maxArea.getMaxX()+charDiff, e.getX());
		
		
		point = e.getPoint();
		length += prev.distance(point);
		currentArea.checkAndSet(point);
		maxArea.checkAndSet(point);
		penPath.add(point);
		paintPenLine(prev, point);
		prev = point;

	}

	public void mouseMoved(MouseEvent e) {
		if (length>1)
			paintArea(maxArea.getMinX(), maxArea.getMaxX(), maxArea.getMaxX()+charDiff, e.getX());
	}
	
	public StrokeQueue getStrokes() {
		return strokes.clone();
	}
	
	public void clearAll() {
		mother.debugPrint("Clearing Panel & stroke data.");
		clearArea();
		clearFlag = true;
		charDiff = 100;
		strokes = new StrokeQueue ();
		maxArea = new StrokeArea();
	}
	
	/**
	 * Component에 선을 그린다. 그리는 선의 색은 DEFAULT_PEN_COLOR 상수에 정의된 색이다.
	 * @param prev 선의 시작 점
	 * @param next 선이 끝나는 점
	 */
	public void paintPenLine(Point prev, Point next) {
		Graphics g = this.getGraphics();
		g.setColor(DEFAULT_PEN_COLOR);
		g.drawLine(prev.x, prev.y, next.x, next.y);
	}
	
	public void clearArea() {
		this.setBackground(Color.WHITE);
		getGraphics().clearRect(1, 1, this.getSize().width , this.getSize().height);
		this.setBorder(new LineBorder(Color.blue));
	}
	
	public void clearArea(int startX, int endX) {
		this.setBackground(Color.WHITE);
		getGraphics().setColor(Color.white);
		getGraphics().clearRect(startX, 11, endX - startX , this.getSize().height);
		this.setBorder(new LineBorder(Color.blue));
	}
	
	
	public void paintDot(Point dotPoint) {
		Graphics g = this.getGraphics();
		g.setColor(Color.red);
		g.fillOval(dotPoint.x-2, dotPoint.y-2, 5, 5);
	}
	
	public void paintArea(int areaStart, int areaEnd, int nextArea, int positionX) {
		int width = this.getWidth();
		int height = 10;
		Graphics g = this.getGraphics();
		
		g.setColor(Color.white);
		g.fillRect(areaEnd, 0, nextArea, height);
		
		g.setColor(Color.blue);
		g.fillRect(0, 0, areaEnd, height);
		g.drawLine(0, 0, width, 0);
		g.drawLine(0, 10, width, height);
		if (positionX>nextArea)
			g.setColor(Color.red);
		else
			g.setColor(Color.green);
		
		g.fillRect(nextArea, 0, width - nextArea, height);
		
		if (positionX<areaEnd)
			g.setColor(Color.yellow);
		else if (positionX<nextArea)
			g.setColor(Color.red);
		else
			g.setColor(Color.white);
		
		g.drawLine(positionX,0,positionX,height);
	}
	
	public void paintSlider(int posX){
		Graphics g = getGraphics();
		if (posX>690)
			posX = 690;
		
		g.setColor(Color.black);
		g.fillRect(posX-10, 0, 20, 10);
		g.setColor(Color.white);
		g.fillRect(posX-10, 11, 20, this.getHeight()-11);
	}
	
}