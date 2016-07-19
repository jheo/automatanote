import java.io.Serializable;
/**
 *	���� ���� ������ �����ϰ� �ִ� Ŭ����.	
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
	 * ���� ������ ���޹޾Ƽ� �ϳ��� ���� ������ �����Ѵ�. <BR />
	 * lines ��Ʈ���� ����,����,���� �밢�� �� ���� ������ �����̴�. <BR />
	 * <UL>
	 * <LI />0 : ���й���. ��� ������ 0���� �����ϸ� ȹ ���� ���е� 0���� �Ѵ�.<BR />
	 * <LI />1 : ����ȹ <BR />
	 * <LI />2 : ����ȹ <BR />
	 * <LI />3 : ��� - ���� �밢�� <BR />
	 * <LI />4 : �»� - ���� �밢�� <BR />
	 * <LI />5 : �� <BR />
	 * </UL>
	 * @param lines ���� ������ �����ϰ� �ִ� ��Ʈ��
	 */
	
	public Pattern(String lines) {
		this.charShape = Pattern.DONTCARE;
		this.lines = lines;
		for (int i = 0; i<lines.length(); i++) 
			if (lines.charAt(i) == '0')
				lineCount++;	
	}
	
	/**
	 * ���� ������ ������ ��Ÿ������ ������� ���޹޾Ƽ� ���� ������ �����Ѵ�.
	 * @param lines ���� ������ �����ϰ� �ִ� ��Ʈ��
	 * @param result ������ ��Ÿ���� ����
	 */
	
	public Pattern(String lines, int unicode) {
		this(lines);
		this.unicode = unicode;
	}
	
	/**
	 * ������ ȹ�� ����� �������� �� ������ �����Ѵ�.
	 * @param lines ���� ������ �����ϰ� �ִ� ��Ʈ��
	 * @param charShape ������ ���
	 */
	public Pattern(String lines, String charShape) {
		this(lines);
		this.charShape = charShape;
	}
	
	/**
	 * ������ ȹ, �����ڵ�, ����� �������� ������ �����Ѵ�.
	 * 
	 * @param lines ������ ȹ ��Ʈ��
	 * @param unicode �����ڵ�
	 * @param charShape ������ ���
	 */
	public Pattern(String lines, int unicode, String charShape) {
		this(lines, unicode);
		this.charShape = charShape;
	}
	
	/**
	 * ������ ȹ, �����ڵ�, ���, �������� ������ �������� ������ �����Ѵ�.
	 * @param lines ������ ȹ ��Ʈ��
	 * @param unicode �����ڵ�
	 * @param charShape ������ ���
	 * @param soundType ������ ��,��,���� ����
	 */
	public Pattern(String lines, int unicode, String charShape, int soundType) {
		this(lines, unicode, charShape);
		this.soundType = soundType;
	}
	/**
	 * ������ ȹ, �����ڵ�, ���, �������� ����, ������� �������� ������ �����Ѵ�.
	 * @param lines ������ ȹ ��Ʈ��
	 * @param unicode �����ڵ�
	 * @param charShape ������ ���
	 * @param soundType ������ ��,��,���� ����
	 * @param charcode ������ ��Ÿ���� �ִ� ����
	 */
	public Pattern(String lines, char charcode, int unicode, String charShape, int soundType) {
		this(lines, unicode, charShape, soundType);
		this.charcode = charcode;
	}
	
	/**
	 * Stroke �ν��Ͻ��� ���޹޾� ������ �����Ѵ�.
	 * @param s ������ stroke�� �ν��Ͻ�
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
	
	// ���� ������� �ʴ� method
	
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
			case '1' : lineDirection += "��"; break;
			case '2' : lineDirection += "�� "; break;
			case '3' : lineDirection += "�� "; break;
			case '4' : lineDirection += "�� "; break;
			case '5' : lineDirection += "��"; break;
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
