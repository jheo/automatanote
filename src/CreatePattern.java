/**
 * 紫穿舛税吉 鳶渡聖 PatternMap拭 蓄亜廃陥.
 * @author Heo June
 *
 */

public class CreatePattern {
	public CreatePattern(PatternMap pHouse) {

		pHouse.addPattern(new Pattern("012", 'ぁ', 0, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("013", 'ぁ', 0, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("012012", 'あ', 1, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("012013", 'あ', 1, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("013013", 'あ', 1, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("013012", 'あ', 1, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("021", 'い', 2, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("041", 'い', 2, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01021", 'ぇ', 3, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0102101021", 'え', 4, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01201021", 'ぉ', 5, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0201201", 'け', 6, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("02020101", 'げ', 7, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("02010201", 'げ', 7, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0202010102020101", 'こ', 8, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0201020102010201", 'こ', 8, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0304", 'さ', 9, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("03040304", 'ざ', 10, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("05", 'し', 11, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("010304", 'じ', 12, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01304",'じ', 12, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0130401304", 'す', 13, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0103040304", 'す', 13, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01010304", 'ず', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("03010304", 'ず', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("04010304", 'ず', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0101304", 'ず', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0401304", 'ず', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0301304", 'ず', 14, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01201", 'せ', 15, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01301", 'せ', 15, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("0102101", 'ぜ', 16, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("01020201", 'そ', 17, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("010105", 'ぞ', 18, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("030105", 'ぞ', 18, Pattern.DONTCARE, Pattern.CONSO));
		pHouse.addPattern(new Pattern("040105", 'ぞ', 18, Pattern.DONTCARE, Pattern.CONSO));
		
		pHouse.addPattern(new Pattern("0201", 'た', 0, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("020101", 'ち', 2, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0102", 'っ', 4, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("010102", 'づ', 6, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0201", 'で', 8, Pattern.WIDTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("020201", 'に', 12, Pattern.WIDTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0102", 'ぬ', 13, Pattern.WIDTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("010202", 'ば', 17, Pattern.WIDTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("01", 'ぱ', 18, Pattern.WIDTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("02", 'び', 20, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("020102", 'だ',1, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("02010102", 'ぢ', 3, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("010202", 'つ', 5, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("01010202", 'て', 7, Pattern.LENGTHWISE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("02010201", 'と', 9, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0201020102", 'ど', 10, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("020102", 'な', 11, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("01020102", 'ね', 14, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0102010202", 'の', 15, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("010202", 'は', 16, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("01030102", 'ね', 14, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0103010202", 'の', 15, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("010302", 'は', 16, Pattern.SQUARE, Pattern.VOWEL));
		pHouse.addPattern(new Pattern("0102", 'ひ', 19, Pattern.SQUARE, Pattern.VOWEL));
		
		pHouse.addPattern(new Pattern("012", 'ぁ', 1, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("013", 'ぁ', 1, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("012012", 'あ', 2, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0120304", 'ぃ', 3, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("021", 'い', 4, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("021010304", 'ぅ', 5, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("02101304", 'ぅ', 5, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("021010105", 'う', 6, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("021030105", 'う', 6, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("021040105", 'う', 6, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01021", 'ぇ', 7, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201021", 'ぉ', 8, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201021012", 'お', 9, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("012010210201201", 'か', 10, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0120102102020101", 'が', 11, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0120102102010201", 'が', 11, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("012010210304", 'き', 12, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("012010210102101", 'ぎ', 13, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0120102101020201", 'く', 14, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201021010105", 'ぐ', 15, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201021030105", 'ぐ', 15, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201021040105", 'ぐ', 15, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0201201", 'け', 16, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("02020101", 'げ', 17, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("02010201", 'げ', 17, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("020201010304", 'ご', 18, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("020102010304", 'ご', 18, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0304", 'さ', 19, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("03040304", 'ざ', 20, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("05", 'し', 21, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("010304", 'じ', 22, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01304", 'じ', 22, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0101304", 'ず', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0301304", 'ず', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0401304", 'ず', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01010304", 'ず', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("03010304", 'ず', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("04010304", 'ず', 23, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01201", 'せ', 24, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("0102101", 'ぜ', 25, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("01020201", 'そ', 26, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("010105", 'ぞ', 27, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("030105", 'ぞ', 27, Pattern.DONTCARE, Pattern.FCONSO));
		pHouse.addPattern(new Pattern("040105", 'ぞ', 27, Pattern.DONTCARE, Pattern.FCONSO));
		
		pHouse.savePatterns();
		
	}
}
