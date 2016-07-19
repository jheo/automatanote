import java.util.ArrayList;
/**
 * ������ ���ڷ� ��ȯ�ϱ� ���� �޼ҵ���� �����ϰ� �ִ� ��ƿ��Ƽ Ŭ������, �ν��Ͻ��� ������ �� ����. <BR />
 * �� Ŭ������ �־��� Stroke �ν��Ͻ��� ������ �޾Ƶ鿩�� ��Ʈ��ŷ, �����˻�, �߽��� �˻縦 ���ؼ� �ν� ���ڸ� �����Ѵ�. <BR />
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
	 * �𰢰˻��� Ȱ���Ͽ� ������ �и��Ѵ�. ��ȣ�� �κп� ���ؼ� ��ü������ ��ó�� ������ ��ģ��.
	 * 
	 * @param strokes �ν��� ȹ�� ����
	 * @return �ν��� ������ ����� (���νĵ� ��� �� ���ڿ�)
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
	 * (������ ����) ���������� �νĵǾ��� ���ڸ� �����Ѵ�.
	 * @return ���� �ν� ����
	 */
	
	public static String getLastChar() {
		return lastChar;
	}
	
	// ��Ʈ��ŷ�� ��ó�� ������ ����Ѵ�. ��ȿ������ ������, �˰��� ������ �䱸�ȴ�.
	
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
	
	// ������ ���ؼ� ������ �����Ѵ�. (��Ȯ�� ������ ������ ���ʿ���)
	private static String compareSide(StrokeArea s1, StrokeArea s2) {
		if (s1.centerPoint().x < s2.getMinX())
			return Pattern.LENGTHWISE;
		if (s1.centerPoint().y < s2.getMinY())
			return Pattern.WIDTHWISE;
		return Pattern.SQUARE;
	}
	
	// ���������� �νĵ� ȹ���� �����ڵ带 �����Ͽ� �� ���ڸ� �����Ѵ�.
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
	
	// ���е� ���ڰ� ��� �νĵ� �� �ִ��� �Ǵ��Ѵ�.
	
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
	 * (������ ����) ������ ���� ȹ�� ���԰��踦 �����Ѵ�.
	 * @param strokes ȹ�� ����
	 * @return ���԰��踦 ����Ͽ� ������ ȹ���� ����
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
