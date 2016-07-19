import java.io.Serializable;
/**
 *	문자 패턴 정보를 저장하고 있는 클래스.	
 *
 *	@author Heo June
 */

public class Pattern implements Serializable {
	private static final long serialVersionUID = 20030302L;
	
	public static final String SERPERATOR = "0";
	public static final String HORIZONTAL = "1";
	public static final String VERTICAL = "2";
	public static final String RLDIAG = "3";
	public static final String LRDIAG = "4";
	public static final String CIRCLE = "5";
	
	public static final String DONTCARE = "D";
	public static final String WIDTHWISE = "W";
	public static final String LENGTHWISE = "L";
	public static final String SQUARE = "S";
	
	public static final int CONSO = 1;
	public static final int VOWEL = 2;
	public static final int FCONSO = 3;
	
	private char charcode;
	private int unicode;
	private String lines;
	private int lineCount;
	private String charShape;
	private int soundType;
	

	/**
	 * 패턴 정보를 전달받아서 하나의 문자 패턴을 생성한다. <BR />
	 * lines 스트링은 가로,세로,양쪽 대각선 및 원형 패턴의 집합이다. <BR />
	 * <UL>
	 * <LI />0 : 구분문자. 모든 패턴은 0으로 시작하며 획 간의 구분도 0으로 한다.<BR />
	 * <LI />1 : 가로획 <BR />
	 * <LI />2 : 세로획 <BR />
	 * <LI />3 : 우상 - 좌하 대각선 <BR />
	 * <LI />4 : 좌상 - 우하 대각선 <BR />
	 * <LI />5 : 원 <BR />
	 * </UL>
	 * @param lines 패턴 정보를 저장하고 있는 스트링
	 */
	
	public Pattern(String lines) {
		this.charShape = Pattern.DONTCARE;
		this.lines = lines;
		for (int i = 0; i<lines.length(); i++) 
			if (lines.charAt(i) == '0')
				lineCount++;	
	}
	
	/**
	 * 패턴 정보와 패턴이 나타내려는 결과값을 전달받아서 문자 패턴을 생성한다.
	 * @param lines 패턴 정보를 저장하고 있는 스트링
	 * @param result 패턴이 나타내는 문자
	 */
	
	public Pattern(String lines, int unicode) {
		this(lines);
		this.unicode = unicode;
	}
	
	/**
	 * 패턴의 획과 모양을 기준으로 한 패턴을 생성한다.
	 * @param lines 패턴 정보를 저장하고 있는 스트링
	 * @param charShape 패턴의 모양
	 */
	public Pattern(String lines, String charShape) {
		this(lines);
		this.charShape = charShape;
	}
	
	/**
	 * 패턴의 획, 유니코드, 모양을 기준으로 패턴을 생성한다.
	 * 
	 * @param lines 패턴의 획 스트링
	 * @param unicode 유니코드
	 * @param charShape 패턴의 모양
	 */
	public Pattern(String lines, int unicode, String charShape) {
		this(lines, unicode);
		this.charShape = charShape;
	}
	
	/**
	 * 패턴의 획, 유니코드, 모양, 초중종성 구분을 기준으로 패턴을 생성한다.
	 * @param lines 패턴의 획 스트링
	 * @param unicode 유니코드
	 * @param charShape 패턴의 모양
	 * @param soundType 패턴의 초,중,종성 구분
	 */
	public Pattern(String lines, int unicode, String charShape, int soundType) {
		this(lines, unicode, charShape);
		this.soundType = soundType;
	}
	/**
	 * 패턴의 획, 유니코드, 모양, 초중종성 구분, 결과값을 기준으로 패턴을 생성한다.
	 * @param lines 패턴의 획 스트링
	 * @param unicode 유니코드
	 * @param charShape 패턴의 모양
	 * @param soundType 패턴의 초,중,종성 구분
	 * @param charcode 패턴이 나타내고 있는 글자
	 */
	public Pattern(String lines, char charcode, int unicode, String charShape, int soundType) {
		this(lines, unicode, charShape, soundType);
		this.charcode = charcode;
	}
	
	/**
	 * Stroke 인스턴스를 전달받아 패턴을 생성한다.
	 * @param s 생성할 stroke의 인스턴스
	 */
	
	public Pattern(Stroke s) {
		this(s.getLine());
		this.charShape = s.getShape();
	}
			
	public String getLines(){
		return lines;
	}
	
	public int getSoundType() {
		return soundType;
	}
	
	public int getUnicode() {
		return unicode;
	}
	
	public char getCharcode() {
		return charcode;
	}
	
	public String getShape() {
		return charShape;
	}
	
	public String getKey() {
		return lines + charShape;
	}
	
	// 이하 사용하지 않는 method
	
	public void setLineCount(int lineCount){
		this.lineCount = lineCount;
	}
	
	public int getLineCount() {
		return lineCount;
	}
	
	public void setResult(char result) {
		this.unicode = result;		
	}
	
	public void setLines(String lines) {
		this.lines = lines;
	}	
		
	public boolean equals(Pattern anotherPattern) {
		if ((anotherPattern.getShape() == Pattern.DONTCARE || this.charShape == Pattern.DONTCARE )
				|| (anotherPattern.getLines() == this.lines && anotherPattern.getShape() == this.charShape))
			return true;
		else return false;
	}
	
	public String toString() {
		String patternString = new String();
		String lineDirection = new String();
		String charSize = new String();
		
		switch(charShape.charAt(0)) {
		case 'D' : charSize = "Don't Care Size"; break;
		case 'W' : charSize = "Widthwise Size"; break;
		case 'L' : charSize = "Lengthwise Size"; break;
		case 'S' : charSize = "Regular Square"; break;
		default: charSize = "Size Error";
		}
		
		patternString = "Line Count : " + lineCount + "\n";
		
		for (int i=0;i<lines.length();i++) {
			switch (lines.charAt(i)) {
			case '1' : lineDirection += "─"; break;
			case '2' : lineDirection += "│ "; break;
			case '3' : lineDirection += "／ "; break;
			case '4' : lineDirection += "＼ "; break;
			case '5' : lineDirection += "○"; break;
			default : lineDirection += "\n\t";
			}
		}
		
		patternString += "Line Shape : " + lineDirection +" \n";
		patternString += "Character Size : " + charSize + " \n";
		patternString += "Unicode : "+unicode+" / Character : "+charcode+" \n";
		patternString += "Sound Type : "+ (soundType == VOWEL ? "Vowel" : (soundType == CONSO ? "Consonant" : "Final Consonant"));
		return patternString;
	}
	
}
