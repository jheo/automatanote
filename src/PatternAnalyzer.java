import java.util.ArrayList;
/**
 * 패턴을 문자로 변환하기 위한 메소드들을 포함하고 있는 유틸리티 클래스로, 인스턴스를 생성할 수 없다. <BR />
 * 이 클래스는 주어진 Stroke 인스턴스의 조합을 받아들여서 백트래킹, 영역검사, 중심점 검사를 통해서 인식 문자를 리턴한다. <BR />
 * 
 * @author Heo June
 *
 */
public final class PatternAnalyzer {
	
	private static String lastChar = "";
	private static PatternMap pHouse = PatternMap.getInstance();
	
	private PatternAnalyzer() {
		
	}
	
	/**
	 * 퇴각검색을 활용하여 모음을 분리한다. 모호한 부분에 대해서 자체적으로 후처리 과정을 거친다.
	 * 
	 * @param strokes 인식할 획의 집합
	 * @return 인식한 문자의 결과값 (미인식된 경우 빈 문자열)
	 */
	public static String backtracking(StrokeQueue strokes) {
		ArrayList <StrokeQueue> results = new ArrayList <StrokeQueue> ();
		ArrayList <Integer> fPoints = new ArrayList <Integer> ();
		ArrayList <Integer> vPoints = new ArrayList <Integer> ();
		
		StrokeQueue sq = new StrokeQueue();
		int fPoint = -1;
		
		while (fPoint < strokes.getLastIndex() + 1) {
		for(int vPoint=1; (fPoint == -1) ? (vPoint<=strokes.getLastIndex()): (vPoint<fPoint); vPoint++) {
			sq.clearQueue();
			sq = strokes.clone();
			sq.deterChar(vPoint, fPoint);
			
			if (isComplete(sq, (fPoint != -1))){
				results.add(sq.clone());
				vPoints.add(vPoint);
				fPoints.add(fPoint);
			}
		}
		
		fPoint = (fPoint == -1) ? 2 : fPoint+1;
		}
		
		switch (results.size()) {
		case 0:
			return "";
		case 1:
			return lastChar = combineQueue(results.get(0));
		default:	
			for (int i = 0; i<results.size(); i++) {
				if (compareSide(results.get(i).get(0), results.get(i).get(1)).equals(Pattern.LENGTHWISE))
					if (comparePositionY(strokes.clone(), vPoints.get(i)))
						return lastChar = combineQueue(results.get(i));
			}
			for (int i = 0; i<results.size(); i++) {
				if (compareSide(results.get(i).get(0), results.get(i).get(1)).equals(Pattern.SQUARE))
					return lastChar = combineQueue(results.get(i));
			}
		
		return lastChar = combineQueue(results.get(0));
		
			
		}
	}
	
	/**
	 * (사용되지 않음) 최종적으로 인식되었던 문자를 리턴한다.
	 * @return 최종 인식 문자
	 */
	
	public static String getLastChar() {
		return lastChar;
	}
	
	// 백트래킹의 후처리 과정을 담당한다. 비효율적인 구조로, 알고리즘 수정이 요구된다.
	
	private static boolean comparePositionY(StrokeQueue strokes, int vPoint) {
		if (strokes.get(vPoint).getLine().equals("1")) {
			int vMin = strokes.get(vPoint).getMinY();
			int cMax = strokes.get(vPoint-1).getMaxY();
			return vMin<cMax;
		}
			else if (vPoint + 1 < strokes.getLastIndex() && strokes.get(vPoint+1).getLine().equals("1")) {
				int vMin = strokes.get(vPoint+1).getMinY();
				int cMax = strokes.get(vPoint-1).getMaxY();
				return vMin<cMax;
			
		} else {
			int vMin = strokes.get(vPoint-1).getMinY();
			int cMax = strokes.getStrokeInPoint(0, (vPoint == 1) ? 0 : vPoint-2).getMaxY();
			return vMin<cMax;
		}
	}
	
	// 모음을 위해서 영역을 구분한다. (명확한 구조로 수정이 불필요함)
	private static String compareSide(StrokeArea s1, StrokeArea s2) {
		if (s1.centerPoint().x < s2.getMinX())
			return Pattern.LENGTHWISE;
		if (s1.centerPoint().y < s2.getMinY())
			return Pattern.WIDTHWISE;
		return Pattern.SQUARE;
	}
	
	// 최종적으로 인식된 획들의 유니코드를 조합하여 한 문자를 리턴한다.
	private static String combineQueue(StrokeQueue sq) {
		
		try{
		int cCode, vCode, fCode;
		Character result;
		
		cCode = pHouse.findPattern(new Pattern(sq.get(0)), Pattern.CONSO).getUnicode();
		vCode = pHouse.findVowel(new Pattern(sq.get(1)), compareSide(sq.get(0), sq.get(1)) ).getUnicode();
		fCode = (sq.getLastIndex() == 1) ? 0 : pHouse.findPattern(new Pattern(sq.get(2)), Pattern.FCONSO).getUnicode();
		result = (char) ( (cCode * 588 + vCode * 28 + fCode) + 44032);
		
		return result.toString();
		} catch (NullPointerException e) {
			return "";
		}
	}
	
	// 구분된 문자가 모두 인식될 수 있는지 판단한다.
	
	private static boolean isComplete(StrokeQueue sq, boolean hasFinal) {
	
		if (pHouse.findPattern(new Pattern(sq.get(0)), Pattern.CONSO) == null)
			return false;
		if (pHouse.findVowel(new Pattern(sq.get(1)), compareSide(sq.get(0), sq.get(1))) == null)
			return false;
		if (!hasFinal)
			return true;
		if (pHouse.findPattern(new Pattern(sq.get(2)), Pattern.FCONSO)== null)
			return false;
		else
			return true;
		
	}
	
	/**
	 * (사용되지 않음) 영역에 따라서 획의 포함관계를 구분한다.
	 * @param strokes 획의 집합
	 * @return 포함관계를 고려하여 합쳐진 획들의 집합
	 */
	public static ArrayList <Stroke> seperateFromArea(ArrayList <Stroke> strokes) {
		ArrayList <Stroke> strs = new ArrayList <Stroke> ();
		Stroke prevStroke = strokes.get(0);
		
		for (Stroke s : strokes) {
			if (s == prevStroke)
				continue;
			
			if (!prevStroke.compareArea(s).equals(StrokeArea.SEPERATED))
				prevStroke.concat(s);
			else{
				strs.add(prevStroke);
				prevStroke = s;
			}
		}
		strs.add(prevStroke);
		return strs;
	}
	
}
