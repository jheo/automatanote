import java.util.ArrayList;
import java.awt.Point;

/**
 * �ʱ��ν� �ý����� �����ν��� ���� ��ƿ��Ƽ Ŭ�����̴�. ��ƿ��Ƽ Ŭ�����̱� ������ �ν��Ͻ��� ������ �� ����. <BR />
 * 
 * @author ���ϴ��б� �������а迭 ��ǻ���������а� '03 ����
 *
 */
public final class StrokeAnalyzer {
	
	private final static double SLOPE_X = 0.2;
	private final static double SLOPE_Y = 5.0;
	private final static int CIRCLE_DIFF = 10;
	private final static int STREAK_LIMIT = 2;
	
	private final static int UPLEFT = 4;
	private final static int UPRIGHT = 3;
	private final static int DOWNLEFT = 2;
	private final static int DOWNRIGHT = 1;
	private final static int CENTER = 0;
	
	public final static int CHAINCODE = 1;
	public final static int RATIO = 2;
	
	private StrokeAnalyzer() {
		// This Class Is Utility Class
		// Can't make any instance of this class
	}
	
	/**
	 * ����Ʈ�� �迭�� Stroke �ν��Ͻ��� ��ȯ�Ѵ�.
	 * ü���ڵ带 ����ϴ� ����� ü���ڵ带 ������� �ʴ� ����� ��� ����� �� �ִ�.
	 * 
	 * @param penPath ����Ʈ�� �迭
	 * @param penArea ȹ�� ����
	 * @param length ȹ�� ����
	 * @param checkType �ν��� ��� (StrokeAnanlyzer.CHAINCODE : ü���ڵ� ��� / StrokeAnalyzer.RATIO : ü���ڵ� ����)
	 * @return ��ȯ�� Stroke �ν��Ͻ�
	 */
	
	public static Stroke checkLine(ArrayList <Point> penPath, StrokeArea penArea, int length, int checkType) {
		return (checkType == CHAINCODE) ? checkLineWithChainCode(penPath, penArea, length) : checkLineWithoutChainCode(penPath, penArea, length); 
	}
	
	/**
	 * �� ȹ�� ���踦 �м��Ͽ� �ι�° ȹ�� ���� ȹ�� ���Ե� �� �ִ����� �˻��Ѵ�.
	 * �ι�° ȹ�� ����ȹ�� ��쿡�� ȣ���ϴ� ���� �ǹ� �ִ�.
	 * (��ȿ������ ����. ���� �˰��� ������ �����.)
	 * 
	 * @param s1 ù��° ȹ
	 * @param s2 �ι��� ȹ. ����ȹ�� ��쿡�� �ǹ� �ִ�.
	 * @return �ι�° ȹ�� ó�� ȹ�� ���ľ� �Ǵ��� ����
	 */
	
	public static boolean isIncludePosition(Stroke s1, Stroke s2) {
		if (s1.getLine().equals(s2.getLine()))
			if (s2.centerPoint().x > s1.getMaxX())
				return false;
			else if (s2.centerPoint().distance(s1.centerPoint()) < 10)
				return true;
		if (s2.getMinY() > s1.getMaxY())
			return false;
		
		if (s2.getMinX() > s1.getMinX() && s2.getMaxX() < s1.getMaxX() && s2.getMaxY() < s1.getMaxY() && s2.getMinY() > s1.getMinY())
			return true;
		
		if (s2.centerPoint().x < s1.getMaxX() && s2.centerPoint().y < s1.getMinY())
			return true;
		
		if (s1.getLine().equals("2") && s2.centerPoint().x > s1.getMaxX())
			return true;
		
		return false;
		
	}
	
	/**
	 * ü���ڵ带 ������� �ʰ� ����Ʈ �迭�� �м��Ͽ� ȹ�� �����Ѵ�.
	 * ���� �˰����� ��ǥ�ڷ� ����.
	 * 
	 * @param penPath ����Ʈ �迭
	 * @param penArea ȹ�� ũ�� ����
	 * @param length ȹ�� ����
	 * @return Stroke �ν��Ͻ�
	 */
	public static Stroke checkLineWithoutChainCode(ArrayList <Point> penPath, StrokeArea penArea, int length){
		String direction = new String();
		int lastIndex = penPath.size() - 1;
		int centerIndex = penPath.size() / 2;
		Point begin = penPath.get(0);
		Point end = penPath.get(lastIndex);
		Point center = penPath.get(centerIndex);
		
		if (checkPoint(begin, penArea) == UPLEFT && checkPoint(end, penArea) == UPRIGHT && begin.distance(end) > CIRCLE_DIFF)
			return Stroke.SPACE;
		if (end.x < center.x && begin.x > center.x && penArea.getRaito() > 3)
			return Stroke.BACK;
		if (checkPoint(begin, penArea) == UPRIGHT && checkPoint(center, penArea) == DOWNRIGHT && checkPoint(end, penArea) == DOWNLEFT && penArea.getRaito() > 0.3 && penArea.getRaito() < 3)
			return Stroke.ENTER;
		
		if (penArea.getRaito() < 0.3 || penArea.getRaito() > 3) {
			direction = (penArea.getRaito() < 0.3) ? Pattern.VERTICAL : Pattern.HORIZONTAL;
		} else { 
		// check circle shape
			if (begin.distance(end) < CIRCLE_DIFF) {
				direction += Pattern.CIRCLE;
			} else {
				if (checkPoint(begin, penArea) == UPLEFT && (checkPoint(end, penArea) == DOWNRIGHT))
					direction = (checkPoint(center, penArea) == UPRIGHT) ? "12" : (checkPoint(center, penArea) == DOWNLEFT) ? "21" : "4";
				else if (checkPoint(begin, penArea) == UPLEFT && checkPoint(end,penArea) == DOWNLEFT)
					direction = "13";
				else
					direction = ( (checkPoint(begin,penArea) == checkPoint(end,penArea)) || (checkPoint(begin,penArea) > 2 && checkPoint(end,penArea) > 2) ) ? "5" : "3";
			}
		
		
		}
		
		return new Stroke(penArea, direction, length);
		
	}
	
	/**
	 * ȹ�� ���� ������ ���޹޾Ƽ� �� ȹ�� ������ ����κп� ��ġ�ϴ��� ��ȯ�Ѵ�.
	 * ü���ڵ带 ������� �ʴ� �˰��� ���ӵ�.
	 * 
	 * @param p �˻��� ����Ʈ
	 * @param pa �˻��� ����
	 * @return �˻� ���
	 */
	
	public static int checkPoint(Point p, StrokeArea pa) {
		int xDiff =(int) ((float)(pa.centerPoint().x - pa.getMinX()) / 2);
		int yDiff =(int) ((float)(pa.centerPoint().y - pa.getMinY()) / 2);
		
		if (p.x > pa.centerPoint().x - xDiff && p.x < pa.centerPoint().x + xDiff && p.y > pa.centerPoint().y - yDiff && p.y < pa.centerPoint().y + yDiff )
			return CENTER;
		
		if (p.x < pa.centerPoint().x && p.y < pa.centerPoint().y)
			return UPLEFT;
		if (p.x > pa.centerPoint().x && p.y < pa.centerPoint().y)
			return UPRIGHT;
		if (p.x < pa.centerPoint().x && p.y > pa.centerPoint().y)
			return DOWNLEFT;
		if (p.x > pa.centerPoint().x && p.y > pa.centerPoint().y)
			return DOWNRIGHT;
		
		return CENTER;
	}
	
	
	/**
	 * (���� ������ ����) <BR />
	 * 
	 * �־��� ����Ʈ�� �迭�� �м��Ͽ� �淮ȭ�� ���� ���ڿ����� ������ Stroke �ν��Ͻ��� ��ȯ�Ѵ�.<BR />
	 * ���ڿ��� �˻�� ������ ���� �̷�� ����. <BR />
	 * 
	 * <OL>
	 * <LI /> ���� �˻� : ���� ���� �������� �������� CIRCLE_DIFF ��� �̳��� ��� ������ �Ǵ��Ѵ�. <BR />
	 * <LI /> ���⼺ �м� : ����Ʈ �迭���� ���⸦ �����Ͽ� ����, ����, ���� �밢������ �����Ѵ�. <BR />
	 * <LI /> ���� �淮ȭ 1 : �м��� ���� ���ڿ���, STREAK_LIMIT ��� ���� ���� ���ӵǴ� ���ڿ��� �����Ѵ�. <BR />
	 * <LI /> ���� �淮ȭ 2 : STREAK_LIMIT�� �Ѿ���� ���ӵǴ� ���ڿ��� �����Ѵ�. <BR />
	 * <LI /> ���� ���� : ���� - �밢�� - ���ο� ���� �ǹ� ���� ���ڿ��� ���� - ���� ���·� ��ȯ�Ѵ�. 4���� ������ ��� ��Ÿ���� ���ڿ��� �������� ��ȯ�Ѵ�. <BR /> 
	 * </OL>
	 * 
	 * ���� �� �м��� ��ΰ� 11111221114444422222221222 �� ���� ������ ���, ���� �淮ȭ �������� STREAK_LIMIT ���ϸ�ŭ ���ӵ� ���ڿ��� �����ϴ� ������ ����
	 * 11111444442222222 �� ���·� ��ȯ�ǰ�, ���ӵ� ���ڿ��� �����ϴ� ������ ���� 142 �� ���·� ��ȯ�ȴ�. ���� �˰����� �� ���� ������ ���ÿ� �����Ѵ�. <BR />
	 * 142 ������ ���ڿ���, ���� ���·δ� - \ | �� ���·�, ���� ȹ���� ����ȹ���� ��ȯ�Ǵ� �������� �밢�� ȹ�� ���ǹ��ϰ� �߰��� ������ �Ǵ��Ͽ�, ���������� 12 ���·� ���ȴ�. <BR />
	 * (�ѱ� �ڸ𿡼� ����ȹ�� ����ȹ ���̿� �밢�� ȹ�� ���� ���ڴ� ����.) <BR />
	 * 
	 * ���������� ���ϵǴ� Stroke �ν��Ͻ��� ���� ���޵� StrokeArea�� length, �׸��� ��ȯ�� ���ڿ��� ���ؼ� �����ȴ�. <BR />
	 * 
	 * @param penPath ���콺 ��� ����Ʈ�� ����
	 * @param penArea ȹ�� �ּ�/�ִ� ����
	 * @param length ȹ�� ����
	 *
	 * @return ��θ� �м��Ͽ�  ������ Stroke �ν��Ͻ�
	 * 
	 **/
	
	public static Stroke checkLineWithChainCode(ArrayList <Point> penPath, StrokeArea penArea, int length){
		String direction = new String();
		int lastIndex = penPath.size() - 1;
		
		Point prev = new Point();
		Point next = new Point();
		
		
		

		// check circle shape
			if (prev.distance(next) < CIRCLE_DIFF) {
				direction += Pattern.CIRCLE;
			} else {
				// pre-processing direction
				
				for (int i = 1;i<lastIndex + 1;i++) {
					next = penPath.get(i);
					direction += checkPoint(prev, next);
					prev = next;
				}
			
		
		// post-processing direction
		direction = (direction.equals("5")) ? direction : checkDirection(direction);
		}
		return new Stroke(penArea, direction, length);
		
	}
	
	
	// ������ ����
	public static String extractString(StrokeQueue strokes) {
		String s = new String();
		
		for (Stroke stroke : strokes) {
			s += "0" + stroke.getLine();
		}
		
		return s;
	}
	
	public static Pattern extractPattern(StrokeQueue strokes) {
		Stroke s = new Stroke();
		
		for (Stroke stroke : strokes) {
			s.checkAndSet(stroke);
		}
		
		s.setLine(extractString(strokes));
		
		return s.getPattern();
	}
	
	// ü���ڵ� �˰��� ���ӵ� �޼ҵ�
	
	private static String checkDirection(String direction) {
		char prevChar;
		char currChar;
		char lastChar = '0';
		
		int streak = 0;
		String returnString = new String();
		prevChar = direction.charAt(0);
		
		for (int i = 1; i<direction.length(); i++) {
			currChar = direction.charAt(i);
			if (currChar == prevChar) 
				streak++;
			else
				streak = 0;
			if (streak>STREAK_LIMIT) {
				if (lastChar != currChar) {
					streak = 0;
					returnString += currChar;
					lastChar = currChar;
				}
			}
		prevChar = currChar;
		}
		
		return checkStroke(returnString);	
	}
	
	// ü���ڵ� �˰����� ��ó�� ����
	private static String checkStroke(String direction) {
		int count = 0;
		
		for (int i = 1; i<6; i++)
			if (direction.contains(i+""))
				count++;
		
		direction = (count>3) ? "5" : direction;
		
		direction = direction.replaceAll("241", "21");
		direction = direction.replaceAll("231", "21");
		direction = direction.replaceAll("142", "12");
		direction = direction.replaceAll("132", "12");
		
		return direction;
		
	}
	
	// ü���ڵ� �˰��� ���ӵ�
	private static String checkPoint(Point prev, Point next) {
		float dx, dy;
		float slope;
		dx = next.x - prev.x;
		dy = next.y - prev.y;
		
		// check slope
		if (dx == 0) {
			return Pattern.VERTICAL;
		} else if (dy == 0) {
			return Pattern.HORIZONTAL;
		} else {
			slope = Math.abs(dy/dx);
			if (slope>0 && slope < SLOPE_X)
				return Pattern.HORIZONTAL;
			else if (slope>SLOPE_X && slope < SLOPE_Y)
				return ((dy/dx)>0) ? Pattern.LRDIAG : Pattern.RLDIAG;
			else 	
				return Pattern.VERTICAL;
			
		}
		
	}
	
	
}
