import java.util.HashMap;
import java.io.*;
/**
 * Pattern ��ü���� �����ϰ� �ִ� �ݷ��� Ŭ����. <BR />
 * �ʼ�, �߼�, ������ ���� ������ hashmap�� �����ϸ鼭 ������ ����� �˻��� ����Ѵ�. <BR />
 * Singleton Design Pattern�� ����Ǿ���. �ν��Ͻ��� �� �� ���� �����Ǹ�, �����ڸ� ���� ���� �� ������ getInstance�޼ҵ带 ����Ͽ� �ν��Ͻ��� �޴´�. <BR />
 * 
 * @author Heo June
 *
 */

public class PatternMap{
	private static final PatternMap onlyOneHouse = new PatternMap();
	
	private HashMap <String, Pattern> consoPatterns;
	private HashMap <String, Pattern> vowelPatterns;
	private HashMap <String, Pattern> fconsoPatterns;
	
	private PatternMap () {	
		consoPatterns = new HashMap <String, Pattern> ();
		vowelPatterns = new HashMap <String, Pattern> ();
		fconsoPatterns = new HashMap <String, Pattern> ();
		
		loadPattern();
	}

	/**
	 * PatternMap �ν��Ͻ��� ��´�. �̱��� Ŭ�����̱� ������ ������ �ν��Ͻ��� ��ȯ�ȴ�.
	 * @return PatternMap�� singleton instance
	 */
	public static PatternMap getInstance() {
		return onlyOneHouse;
	}
	
	/**
	 * ������ �߰��Ѵ�.
	 * @param freshPattern �߰��� ����
	 */
	public void addPattern(Pattern freshPattern) {
		getPatternMap(freshPattern.getSoundType()).put(freshPattern.getKey(), freshPattern);
	}
	
	/**
	 * 3���� hashmap�� ���ϴ� �ڷᱸ���� �����Ѵ�.
	 * @param soundType ���޹ޱ� ���ϴ� ����Ÿ�� (��,��,����)
	 * @return �ش�Ǵ� hashMap
	 */
	public HashMap <String, Pattern> getPatternMap(int soundType) {
		switch(soundType){
		case Pattern.CONSO :
			return consoPatterns;
		case Pattern.VOWEL :
			return vowelPatterns;
		case Pattern.FCONSO :
			return fconsoPatterns;
		default:
			return null;
		}
	}
	
	/**
	 * ������ �˻��Ѵ�. �߼��� ��� findVowel �޼ҵ� ����� �����Ѵ�.
	 * @param wantedPattern �˻��� ����
	 * @param findType �˻��� ������ ��,��,���� ����
	 * @return
	 */
	public Pattern findPattern(Pattern wantedPattern, int findType) {
		Pattern result;
		
		result = getPatternMap(findType).get(wantedPattern.getKey());
		return (result == null) ? getPatternMap(findType).get(wantedPattern.getLines() + "D") : result;
	}
	
	/**
	 * ������ ã�´�. findPattern �޼ҵ�� �ٸ� ����, ������ ��ġ���踦 �м��Ͽ� ������ Ȱ���ϱ� ������ �νķ��� ����.
	 * @param wantedPattern �˻��� ����
	 * @param vowelPosition ������ ��ġ (PatternAnalyzer.compareSide �޼ҵ带 ����ϸ� ���ϴ�.)
	 * @return
	 */
	
	public Pattern findVowel(Pattern wantedPattern, String vowelPosition) {
		Pattern result;
		
		result = vowelPatterns.get(wantedPattern.getLines() + vowelPosition);
		return result;
	}
	
	/**
	 * ���� ���������� �����Ѵ�. data ���丮�� pattern.an ���Ͽ� ����ȴ�.
	 */
	
	public void savePatterns(){
		try{
		FileOutputStream fos = new FileOutputStream("./data/pattern.an");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(consoPatterns);
		oos.writeObject(vowelPatterns);
		oos.writeObject(fconsoPatterns);
		
		oos.close();
		fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���������� ���Ϸ� ���� �ҷ��ͼ� �ʱ�ȭ �Ѵ�. �� �κ��� warning�� �ʿ������� �߻��Ѵ�.
	 */
	
	public void loadPattern() {
		try{
			FileInputStream fis = new FileInputStream("./data/pattern.an");
			ObjectInputStream ois = new ObjectInputStream (fis);
	
			this.consoPatterns = (HashMap <String, Pattern>) ois.readObject();
			this.vowelPatterns = (HashMap <String, Pattern>) ois.readObject();
			this.fconsoPatterns = (HashMap <String, Pattern>) ois.readObject();
			
			ois.close();
			fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassCastException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Illegal Patterns!");
				e.printStackTrace();		
			}
	}
	
}