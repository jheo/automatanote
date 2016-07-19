import java.util.HashMap;
import java.io.*;
/**
 * Pattern 객체들을 저장하고 있는 콜렉션 클래스. <BR />
 * 초성, 중성, 종성에 대한 각각의 hashmap을 유지하면서 패턴의 저장과 검색을 담당한다. <BR />
 * Singleton Design Pattern이 적용되었다. 인스턴스는 단 한 개만 생성되며, 생성자를 통해 얻을 수 없으며 getInstance메소드를 사용하여 인스턴스를 받는다. <BR />
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
	 * PatternMap 인스턴스를 얻는다. 싱글턴 클래스이기 때문에 유일한 인스턴스가 반환된다.
	 * @return PatternMap의 singleton instance
	 */
	public static PatternMap getInstance() {
		return onlyOneHouse;
	}
	
	/**
	 * 패턴을 추가한다.
	 * @param freshPattern 추가할 패턴
	 */
	public void addPattern(Pattern freshPattern) {
		getPatternMap(freshPattern.getSoundType()).put(freshPattern.getKey(), freshPattern);
	}
	
	/**
	 * 3개의 hashmap중 원하는 자료구조를 리턴한다.
	 * @param soundType 전달받기 원하는 문자타입 (초,중,종성)
	 * @return 해당되는 hashMap
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
	 * 패턴을 검색한다. 중성의 경우 findVowel 메소드 사용을 권장한다.
	 * @param wantedPattern 검색할 패턴
	 * @param findType 검색할 패턴의 초,중,종성 구분
	 * @return
	 */
	public Pattern findPattern(Pattern wantedPattern, int findType) {
		Pattern result;
		
		result = getPatternMap(findType).get(wantedPattern.getKey());
		return (result == null) ? getPatternMap(findType).get(wantedPattern.getLines() + "D") : result;
	}
	
	/**
	 * 모음을 찾는다. findPattern 메소드와 다른 점은, 모음의 위치관계를 분석하여 정보로 활용하기 때문에 인식률이 높다.
	 * @param wantedPattern 검색할 패턴
	 * @param vowelPosition 모음의 위치 (PatternAnalyzer.compareSide 메소드를 사용하면 편하다.)
	 * @return
	 */
	
	public Pattern findVowel(Pattern wantedPattern, String vowelPosition) {
		Pattern result;
		
		result = vowelPatterns.get(wantedPattern.getLines() + vowelPosition);
		return result;
	}
	
	/**
	 * 현재 패턴집합을 저장한다. data 디렉토리의 pattern.an 파일에 저장된다.
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
	 * 패턴정보를 파일로 부터 불러와서 초기화 한다. 이 부분의 warning은 필연적으로 발생한다.
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