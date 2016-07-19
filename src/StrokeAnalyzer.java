import java.util.ArrayList;
import java.awt.Point;

/**
 * 필기인식 시스템의 패턴인식을 위한 유틸리티 클래스이다. 유틸리티 클래스이기 때문에 인스턴스를 생성할 수 없다. <BR />
 * 
 * @author 인하대학교 정보공학계열 컴퓨터정보공학과 '03 허준
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
	 * 포인트의 배열을 Stroke 인스턴스로 변환한다.
	 * 체인코드를 사용하는 방법과 체인코드를 사용하지 않는 방법을 골라서 사용할 수 있다.
	 * 
	 * @param penPath 포인트의 배열
	 * @param penArea 획의 영역
	 * @param length 획의 길이
	 * @param checkType 인식할 방법 (StrokeAnanlyzer.CHAINCODE : 체인코드 사용 / StrokeAnalyzer.RATIO : 체인코드 비사용)
	 * @return 변환된 Stroke 인스턴스
	 */
	
	public static Stroke checkLine(ArrayList <Point> penPath, StrokeArea penArea, int length, int checkType) {
		return (checkType == CHAINCODE) ? checkLineWithChainCode(penPath, penArea, length) : checkLineWithoutChainCode(penPath, penArea, length); 
	}
	
	/**
	 * 두 획의 관계를 분석하여 두번째 획이 앞의 획에 포함될 수 있는지를 검사한다.
	 * 두번째 획이 가로획인 경우에만 호출하는 것이 의미 있다.
	 * (비효율적인 구조. 추후 알고리즘 수정이 요망됨.)
	 * 
	 * @param s1 첫번째 획
	 * @param s2 두번쨰 획. 가로획인 경우에만 의미 있다.
	 * @return 두번째 획을 처음 획에 합쳐야 되는지 여부
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
	 * 체인코드를 사용하지 않고 포인트 배열을 분석하여 획을 생성한다.
	 * 생성 알고리즘은 발표자료 참고.
	 * 
	 * @param penPath 포인트 배열
	 * @param penArea 획의 크기 영역
	 * @param length 획의 길이
	 * @return Stroke 인스턴스
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
	 * 획의 점과 영역을 전달받아서 그 획이 영역의 어느부분에 위치하는지 반환한다.
	 * 체인코드를 사용하지 않는 알고리즘에 종속됨.
	 * 
	 * @param p 검사할 포인트
	 * @param pa 검사할 영역
	 * @return 검사 결과
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
	 * (현재 사용되지 않음) <BR />
	 * 
	 * 주어진 포인트의 배열을 분석하여 경량화된 방향 문자열으로 생성된 Stroke 인스턴스를 반환한다.<BR />
	 * 문자열의 검사는 다음과 같이 이루어 진다. <BR />
	 * 
	 * <OL>
	 * <LI /> 원형 검사 : 만약 선의 시작점과 종료점이 CIRCLE_DIFF 상수 이내일 경우 원으로 판단한다. <BR />
	 * <LI /> 방향성 분석 : 포인트 배열간의 기울기를 측정하여 가로, 세로, 양쪽 대각선으로 구분한다. <BR />
	 * <LI /> 방향 경량화 1 : 분석된 방향 문자열중, STREAK_LIMIT 상수 보다 적게 연속되는 문자열은 제거한다. <BR />
	 * <LI /> 방향 경량화 2 : STREAK_LIMIT를 넘어가더라도 연속되는 문자열은 제거한다. <BR />
	 * <LI /> 최종 보정 : 가로 - 대각선 - 세로와 같이 의미 없는 문자열은 가로 - 세로 형태로 변환한다. 4가지 방향이 모두 나타나는 문자열은 원형으로 변환한다. <BR /> 
	 * </OL>
	 * 
	 * 예를 들어서 분석된 경로가 11111221114444422222221222 와 같은 형태일 경우, 방향 경량화 과정에서 STREAK_LIMIT 이하만큼 연속된 문자열을 제거하는 과정을 통해
	 * 11111444442222222 의 형태로 변환되고, 연속된 문자열을 제거하는 과정을 통해 142 의 형태로 변환된다. 실제 알고리즘은 두 가지 과정을 동시에 수행한다. <BR />
	 * 142 형태의 문자열은, 실제 형태로는 - \ | 의 형태로, 가로 획에서 세로획으로 변환되는 과정에서 대각선 획이 무의미하게 추가된 것으로 판단하여, 최종적으로 12 형태로 축약된다. <BR />
	 * (한글 자모에서 가로획과 세로획 사이에 대각선 획이 들어가는 문자는 없다.) <BR />
	 * 
	 * 최종적으로 리턴되는 Stroke 인스턴스는 최초 전달된 StrokeArea와 length, 그리고 변환된 문자열을 통해서 생성된다. <BR />
	 * 
	 * @param penPath 마우스 경로 포인트의 집합
	 * @param penArea 획의 최소/최대 범위
	 * @param length 획의 길이
	 *
	 * @return 경로를 분석하여  생성된 Stroke 인스턴스
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
	
	
	// 사용되지 않음
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
	
	// 체인코드 알고리즘에 종속된 메소드
	
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
	
	// 체인코드 알고리즘의 후처리 과정
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
	
	// 체인코드 알고리즘에 종속됨
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
