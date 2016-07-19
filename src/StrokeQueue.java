import java.util.ArrayList;
import java.util.Iterator;
/**
 * Stroke들의 집합을 위한 자료구조. 최초에는 ArrayList를 사용하였으나, 프로그램 작성 중 필요에 의해 재정의 되었다. <BR />
 * ArrayList와의 호환성을 유지하기 위하여 add, get 메소드를 가지고 있으나 addStroke, getStroke 메소드의 사용이 권장된다.
 * @author inhacse
 *
 */
public class StrokeQueue implements Cloneable, Iterable <Stroke> {
	private ArrayList <Stroke> strokes;
	
	/**
	 * 새로운 (비어있는) 자료구조를 생성한다.
	 */
	
	public StrokeQueue() {
		strokes = new ArrayList <Stroke> ();
	}
	
	/**
	 * ArrayList와의 호환성을 유지하기 위함.
	 * @param s 배열에 추가할 Stroke Instance
	 */
	public void add(Stroke s) {
		addStroke(s);
	}
	
	/**
	 * ArrayList와의 호환성을 유지하기 위함.
	 * @param index 얻고자 하는 스트로크의 인덱스 (0부터 시작한다.)
	 */
	public Stroke get(int index) {
		return strokes.get(index);
	}
	
	/**
	 * 집합에 획을 추가한다.
	 * @param s 배열에 추가할 스트로크의 인스턴스
	 */
	
	public void addStroke(Stroke s) {
		strokes.add(s);
	}
	
	/**
	 * (사용되지 않음) 큐의 첫번째 스트로크를 획득한다.
	 * @return 큐의 첫번째 스트로크
	 */
	public Stroke getFirstStroke() {
		return strokes.get(0);
	}
	
	/**
	 * 큐의 마지막 획을 리턴한다.
	 * @return 큐의 마지막 스트로크
	 */
	public Stroke getLastStroke() {
		return strokes.get(strokes.size() -1);
	}
	
	/**
	 * 전달된 범위만큼의 스트로크를 합친다.
	 * 
	 * @param index1 합칠 스트로크의 첫번째 인덱스
	 * @param amount 합칠 분량
	 * @return 정상적으로 완료될 경우 true, 에러가 발생하였을 경우 false
	 */
	
	public boolean concatRange(int startIndex, int amount) {
		if (!rangeCheck(startIndex))
			return false;
		
		if (!rangeCheck(startIndex + amount))
			amount = strokes.size() - startIndex;
		
		for (int i=1; i<amount; i++)
			strokes.get(startIndex).concat(strokes.remove(startIndex+1));
		
		return true;
	}
	
	/**
	 * 주어진 인덱스에 따라서 획을 구분하여 2가지 혹은 3가지 획으로 합친다. <BR />
	 * 집합을 변화시키기 때문에 복사된 집합에 대해서 사용할 것을 권장한다.
	 * @param vowelPoint 모음의 위치 (0부터 시작한다.)
	 * @param fconsoPoint 받침의 위치, 받침이 없는 경우 -1을 전달시켜야 한다.
	 * @return 성공적으로 분리되었는지 여부. (인덱스가 잘못된 경우 false가 리턴된다.)
	 */
	public boolean deterChar(int vowelPoint, int fconsoPoint) {
		if (fconsoPoint != -1 && !(vowelPoint<fconsoPoint) )
			return false;
		
		if (fconsoPoint == -1) {
			concatRange(vowelPoint, getLastIndex() - fconsoPoint +1);
			concatRange(0, vowelPoint);
			return addZero();
		} else {
			
		concatRange(fconsoPoint, getLastIndex() - fconsoPoint + 1);
		concatRange(vowelPoint, fconsoPoint-vowelPoint);
		concatRange(0, vowelPoint);
		}
		
		return addZero();
	}
	
	/**
	 * 각 획의 첫번째 위치에 구분자인 0을 추가해준다.
	 * @return 성공적으로 추가되었는지 여부
	 */
	
	public boolean addZero() {
		if (strokes.size()<2 || strokes.size() > 3)
			return false;
		
		for (Stroke s : strokes)
			s.addStartSymbol();
		
		return true;
	}

	/**
	 * 큐를 초기화 한다.
	 */
	public void clearQueue() {
		strokes = new ArrayList <Stroke> ();
	}
	
	/**
	 * (사용되지 않음) 이 집합의 처음부터 끝 획을 모두 합쳐서 리턴한다.
	 * @return 합쳐진 하나의 스트로크
	 */
	public Stroke getIntegratedStroke() {
	
		return getIntegratedStroke(this);
		
	}
	
	/**
	 * 특정 집합의 처음부터 끝 획을 모두 합쳐서 리턴한다.
	 * @param sq 합칠 스트로크 큐 인스턴스
	 * @return 합쳐진 하나의 스트로크
	 */
	public Stroke getIntegratedStroke(StrokeQueue sq) {
		Stroke returnStroke = new Stroke();
		for (Stroke s : sq) {
			returnStroke.concat(s);
		}
		
		return returnStroke;
	}
	
	// 이하 사용되지 않는 메소드들
	public StrokeQueue getSubQueueToIndex(int endIndex) {
		return getSubQueue(0,endIndex);
	}
	
	public StrokeQueue getSubQueueFromIndex(int startIndex) {
		return getSubQueue(startIndex, getLastIndex() - startIndex);
	}
	
	public StrokeQueue getSubQueueToFromIndex(int start, int end) {
		return getSubQueue(start, end - start + 1);
	}
	
	public Stroke getStrokeInPoint(int start, int end) {
		return getIntegratedStroke(getSubQueueToFromIndex(start, end));
	}
	
	public StrokeQueue getSubQueue(int startIndex, int amount) {
		StrokeQueue s = new StrokeQueue();
		
		if (amount<1)
			amount = 1;
		
		if (!rangeCheck(startIndex))
			return null;
		
		if (!rangeCheck(startIndex + amount))
			amount = strokes.size() - startIndex;
		
		for (int i=startIndex; i<startIndex + amount; i++) {
			s.addStroke(strokes.get(i));
		}
		
		return s;
	}
	
	/**
	 * 집합이 비어있는 지를 리턴한다.
	 * @return 집합이 비어있다면 true, 없다면 false
	 */
	public boolean isEmpty() {
		return (strokes.size() == 0);
	}
	
	/**
	 * 주어진 Stroke의 집합에서 가장 긴 획의 인덱스를 찾는다.
	 * @return 가장 긴 획의 index. 에러 발생시 -1.
	 */
	public int getLongestStrokeIndex() {
		int index = -1;
		int maxLength = -1;
		
		for (int i=0; i<strokes.size(); i++) {
			if (strokes.get(i).getLength() > maxLength)
				index = i;
		}
		
		return index;
	}
	
	/**
	 * 마지막 스트로크의 인덱스를 리턴한다. 스트로크의 갯수보다 하나 적다.
	 * @return 마지막 스트로크의 인덱스
	 */
	public int getLastIndex() {
		return strokes.size() -1;
	}
	
	// 인덱스의 영역 검사
	private boolean rangeCheck(int index){
		return (index>=0 && index<=getLastIndex());
	}
	
	/**
	 * 현재 큐에 저장된 스트로크들을 모두 포함하는 최대 영역을 리턴한다.
	 * @return 스트로크들의 최대 영역
	 */
	public StrokeArea getMaxArea() {
		StrokeArea s = new StrokeArea();
		
		for (Stroke stroke : strokes) {
			s.checkAndSet(stroke.getStrokeArea());
		}
		
		return s;
	}
	
	/**
	 * 현재 큐의 복사본을 리턶나다.
	 * @return 현재 큐의 복사본
	 */
	
	public StrokeQueue clone() {
		StrokeQueue s = new StrokeQueue();
		for (Stroke strokeElement : strokes)
			s.addStroke(strokeElement.clone());
		return s;
	}

	public Iterator<Stroke> iterator() {
		return strokes.iterator();
	}	
	
	/**
	 * (디버그용) 큐의 내용을 출력한다.
	 */
	public void printQueueElements() {
		for (Stroke s : strokes)
			System.out.println(s);
	}
	
	public String toString() {
		String rString = new String();
		
		for (Stroke s : strokes)
			rString += s;
		
		return rString;
	}
}
