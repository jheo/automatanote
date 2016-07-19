import java.util.ArrayList;
import java.util.Iterator;
/**
 * Stroke���� ������ ���� �ڷᱸ��. ���ʿ��� ArrayList�� ����Ͽ�����, ���α׷� �ۼ� �� �ʿ信 ���� ������ �Ǿ���. <BR />
 * ArrayList���� ȣȯ���� �����ϱ� ���Ͽ� add, get �޼ҵ带 ������ ������ addStroke, getStroke �޼ҵ��� ����� ����ȴ�.
 * @author inhacse
 *
 */
public class StrokeQueue implements Cloneable, Iterable <Stroke> {
	private ArrayList <Stroke> strokes;
	
	/**
	 * ���ο� (����ִ�) �ڷᱸ���� �����Ѵ�.
	 */
	
	public StrokeQueue() {
		strokes = new ArrayList <Stroke> ();
	}
	
	/**
	 * ArrayList���� ȣȯ���� �����ϱ� ����.
	 * @param s �迭�� �߰��� Stroke Instance
	 */
	public void add(Stroke s) {
		addStroke(s);
	}
	
	/**
	 * ArrayList���� ȣȯ���� �����ϱ� ����.
	 * @param index ����� �ϴ� ��Ʈ��ũ�� �ε��� (0���� �����Ѵ�.)
	 */
	public Stroke get(int index) {
		return strokes.get(index);
	}
	
	/**
	 * ���տ� ȹ�� �߰��Ѵ�.
	 * @param s �迭�� �߰��� ��Ʈ��ũ�� �ν��Ͻ�
	 */
	
	public void addStroke(Stroke s) {
		strokes.add(s);
	}
	
	/**
	 * (������ ����) ť�� ù��° ��Ʈ��ũ�� ȹ���Ѵ�.
	 * @return ť�� ù��° ��Ʈ��ũ
	 */
	public Stroke getFirstStroke() {
		return strokes.get(0);
	}
	
	/**
	 * ť�� ������ ȹ�� �����Ѵ�.
	 * @return ť�� ������ ��Ʈ��ũ
	 */
	public Stroke getLastStroke() {
		return strokes.get(strokes.size() -1);
	}
	
	/**
	 * ���޵� ������ŭ�� ��Ʈ��ũ�� ��ģ��.
	 * 
	 * @param index1 ��ĥ ��Ʈ��ũ�� ù��° �ε���
	 * @param amount ��ĥ �з�
	 * @return ���������� �Ϸ�� ��� true, ������ �߻��Ͽ��� ��� false
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
	 * �־��� �ε����� ���� ȹ�� �����Ͽ� 2���� Ȥ�� 3���� ȹ���� ��ģ��. <BR />
	 * ������ ��ȭ��Ű�� ������ ����� ���տ� ���ؼ� ����� ���� �����Ѵ�.
	 * @param vowelPoint ������ ��ġ (0���� �����Ѵ�.)
	 * @param fconsoPoint ��ħ�� ��ġ, ��ħ�� ���� ��� -1�� ���޽��Ѿ� �Ѵ�.
	 * @return ���������� �и��Ǿ����� ����. (�ε����� �߸��� ��� false�� ���ϵȴ�.)
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
	 * �� ȹ�� ù��° ��ġ�� �������� 0�� �߰����ش�.
	 * @return ���������� �߰��Ǿ����� ����
	 */
	
	public boolean addZero() {
		if (strokes.size()<2 || strokes.size() > 3)
			return false;
		
		for (Stroke s : strokes)
			s.addStartSymbol();
		
		return true;
	}

	/**
	 * ť�� �ʱ�ȭ �Ѵ�.
	 */
	public void clearQueue() {
		strokes = new ArrayList <Stroke> ();
	}
	
	/**
	 * (������ ����) �� ������ ó������ �� ȹ�� ��� ���ļ� �����Ѵ�.
	 * @return ������ �ϳ��� ��Ʈ��ũ
	 */
	public Stroke getIntegratedStroke() {
	
		return getIntegratedStroke(this);
		
	}
	
	/**
	 * Ư�� ������ ó������ �� ȹ�� ��� ���ļ� �����Ѵ�.
	 * @param sq ��ĥ ��Ʈ��ũ ť �ν��Ͻ�
	 * @return ������ �ϳ��� ��Ʈ��ũ
	 */
	public Stroke getIntegratedStroke(StrokeQueue sq) {
		Stroke returnStroke = new Stroke();
		for (Stroke s : sq) {
			returnStroke.concat(s);
		}
		
		return returnStroke;
	}
	
	// ���� ������ �ʴ� �޼ҵ��
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
	 * ������ ����ִ� ���� �����Ѵ�.
	 * @return ������ ����ִٸ� true, ���ٸ� false
	 */
	public boolean isEmpty() {
		return (strokes.size() == 0);
	}
	
	/**
	 * �־��� Stroke�� ���տ��� ���� �� ȹ�� �ε����� ã�´�.
	 * @return ���� �� ȹ�� index. ���� �߻��� -1.
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
	 * ������ ��Ʈ��ũ�� �ε����� �����Ѵ�. ��Ʈ��ũ�� �������� �ϳ� ����.
	 * @return ������ ��Ʈ��ũ�� �ε���
	 */
	public int getLastIndex() {
		return strokes.size() -1;
	}
	
	// �ε����� ���� �˻�
	private boolean rangeCheck(int index){
		return (index>=0 && index<=getLastIndex());
	}
	
	/**
	 * ���� ť�� ����� ��Ʈ��ũ���� ��� �����ϴ� �ִ� ������ �����Ѵ�.
	 * @return ��Ʈ��ũ���� �ִ� ����
	 */
	public StrokeArea getMaxArea() {
		StrokeArea s = new StrokeArea();
		
		for (Stroke stroke : strokes) {
			s.checkAndSet(stroke.getStrokeArea());
		}
		
		return s;
	}
	
	/**
	 * ���� ť�� ���纻�� ���t����.
	 * @return ���� ť�� ���纻
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
	 * (����׿�) ť�� ������ ����Ѵ�.
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
