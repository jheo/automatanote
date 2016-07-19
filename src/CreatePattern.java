/**
 * �������ǵ� ������ PatternMap�� �߰��Ѵ�.
 * @author Heo June
 *
 */

public class CreatePattern {
	public CreatePattern(PatternMap pHouse) {

		pHouse.addPattern(new Pattern("012", '��', 0, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("013", '��', 0, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("012012", '��', 1, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("012013", '��', 1, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("013013", '��', 1, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("013012", '��', 1, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("021", '��', 2, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("041", '��', 2, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01021", '��', 3, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0102101021", '��', 4, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01201021", '��', 5, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0201201", '��', 6, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("02020101", '��', 7, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("02010201", '��', 7, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0202010102020101", '��', 8, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0201020102010201", '��', 8, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0304", '��', 9, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("03040304", '��', 10, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("05", '��', 11, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("010304", '��', 12, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01304",'��', 12, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0130401304", '��', 13, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0103040304", '��', 13, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01010304", '��', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("03010304", '��', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("04010304", '��', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0101304", '��', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0401304", '��', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0301304", '��', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01201", '��', 15, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01301", '��', 15, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0102101", '��', 16, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01020201", '��', 17, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("010105", '��', 18, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("030105", '��', 18, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("040105", '��', 18, Pattern.DONTCARE, Pattern.CONSO));
		
		pHouse.addPattern(new Pattern("0201", '��', 0, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("020101", '��', 2, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0102", '��', 4, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("010102", '��', 6, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0201", '��', 8, Pattern.WIDTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("020201", '��', 12, Pattern.WIDTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0102", '��', 13, Pattern.WIDTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("010202", '��', 17, Pattern.WIDTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("01", '��', 18, Pattern.WIDTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("02", '��', 20, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("020102", '��',1, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("02010102", '��', 3, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("010202", '��', 5, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("01010202", '��', 7, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("02010201", '��', 9, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0201020102", '��', 10, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("020102", '��', 11, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("01020102", '��', 14, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0102010202", '��', 15, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("010202", '��', 16, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("01030102", '��', 14, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0103010202", '��', 15, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("010302", '��', 16, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0102", '��', 19, Pattern.SQUARE, Pattern.VOWEL));
		
		pHouse.addPattern(new Pattern("012", '��', 1, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("013", '��', 1, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("012012", '��', 2, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0120304", '��', 3, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("021", '��', 4, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("021010304", '��', 5, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("02101304", '��', 5, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("021010105", '��', 6, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("021030105", '��', 6, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("021040105", '��', 6, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01021", '��', 7, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201021", '��', 8, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201021012", '��', 9, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("012010210201201", '��', 10, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0120102102020101", '��', 11, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0120102102010201", '��', 11, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("012010210304", '��', 12, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("012010210102101", '��', 13, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0120102101020201", '��', 14, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201021010105", '��', 15, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201021030105", '��', 15, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201021040105", '��', 15, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0201201", '��', 16, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("02020101", '��', 17, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("02010201", '��', 17, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("020201010304", '��', 18, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("020102010304", '��', 18, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0304", '��', 19, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("03040304", '��', 20, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("05", '��', 21, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("010304", '��', 22, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01304", '��', 22, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0101304", '��', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0301304", '��', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0401304", '��', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01010304", '��', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("03010304", '��', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("04010304", '��', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201", '��', 24, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0102101", '��', 25, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01020201", '��', 26, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("010105", '��', 27, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("030105", '��', 27, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("040105", '��', 27, Pattern.DONTCARE, Pattern.FCONSO));
		
		pHouse.savePatterns();
		
	}
}
