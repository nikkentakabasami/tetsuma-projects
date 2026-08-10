package ru.tet.warodai;

/**
 * Преобразует произношения между катакананой, хираганой и каной
 * 
 * @author tetsuma
 *
 */
public class KatakanaHiraganaUtils {

	public static char KATAKANA_ZERO_CHAR = '\u30A0';
	public static char HIRAGANA_ZERO_CHAR = '\u3040';
	public static int CHAR_TABLE_SIZE = 96;

	public static int ODD_CHAR_COUNT_AT_TABLE_END = 9;
	

	public static String[] kanaSoundTable = new String[] {
			"", //゠ - 
			"a", //ァ - ぁ
			"a", //ア - あ
			"i", //ィ - ぃ
			"i", //イ - い
			"u", //ゥ - ぅ
			"u", //ウ - う
			"e", //ェ - ぇ
			"e", //エ - え
			"o", //ォ - ぉ
			"o", //オ - お
			"ka", //カ - か
			"ga", //ガ - が
			"ki", //キ - き
			"gi", //ギ - ぎ
			"ku", //ク - く
			"gu", //グ - ぐ
			"ke", //ケ - け
			"ge", //ゲ - げ
			"ko", //コ - こ
			"go", //ゴ - ご
			"sa", //サ - さ
			"za", //ザ - ざ
			"shi", //シ - し
			"ji", //ジ - じ
			"su", //ス - す
			"zu", //ズ - ず
			"se", //セ - せ
			"ze", //ゼ - ぜ
			"so", //ソ - そ
			"zo", //ゾ - ぞ
			"ta", //タ - た
			"da", //ダ - だ
			"chi", //チ - ち
			"ji", //ヂ - ぢ
			"", //ッ - っ
			"tsu", //ツ - つ
			"zu", //ヅ - づ
			"te", //テ - て
			"de", //デ - で
			"to", //ト - と
			"do", //ド - ど
			"na", //ナ - な
			"ni", //ニ - に
			"nu", //ヌ - ぬ
			"ne", //ネ - ね
			"no", //ノ - の
			"ha", //ハ - は
			"ba", //バ - ば
			"pa", //パ - ぱ
			"hi", //ヒ - ひ
			"bi", //ビ - び
			"pi", //ピ - ぴ
			"fu", //フ - ふ
			"bu", //ブ - ぶ
			"pu", //プ - ぷ
			"he", //ヘ - へ
			"be", //ベ - べ
			"pe", //ペ - ぺ
			"ho", //ホ - ほ
			"bo", //ボ - ぼ
			"po", //ポ - ぽ
			"ma", //マ - ま
			"mi", //ミ - み
			"mu", //ム - む
			"me", //メ - め
			"mo", //モ - も
			"ya", //ャ - ゃ
			"ya", //ヤ - や
			"yu", //ュ - ゅ
			"yu", //ユ - ゆ
			"yo", //ョ - ょ
			"yo", //ヨ - よ
			"ra", //ラ - ら
			"ri", //リ - り
			"ru", //ル - る
			"re", //レ - れ
			"ro", //ロ - ろ
			"wa", //ヮ - ゎ
			"wa", //ワ - わ
			"wi", //ヰ - ゐ
			"we", //ヱ - ゑ
			"o ", //ヲ - を
			"n", //ン - ん
			"vu", //ヴ - ゔ
			"ka", //ヵ - ゕ
			"ke", //ヶ - ゖ
			"", //ヷ - 
			"", //ヸ - 
			"", //ヹ - ゙
			"", //ヺ - ゚
			"", //・ - ゛
			"", //ー - ゜
			"", //ヽ - ゝ
			"", //ヾ - ゞ
			"" //ヿ - ゟ	 * 
	};	
	
	public static String[] romajiSyllablesTable = new String[] {
			
			"kya",
			"kyu",
			"kyo",
			"gya",
			"gyu",
			"gyo",
			"sha",
			"shu",
			"sho",
			"ja",
			"ju",
			"jo",
			"cha",
			"chu",
			"cho",
			"nya",
			"nyu",
			"nyo",
			"nya",
			"nyu",
			"nyo",
			"hya",
			"hyu",
			"hyo",
			"bya",
			"byu",
			"byo",
			"pya",
			"pyu",
			"pyo",
			"mya",
			"myu",
			"myo",
			"rya",
			"ryu",
			"ryo"			
	};

	public static String[] katakanaSyllablesTable = new String[] {
			"キャ",
			"キュ",
			"キョ",
			"ギャ",
			"ギュ",
			"ギョ",
			"シャ",
			"シュ",
			"ショ",
			"ジャ",
			"ジュ",
			"ジョ",
			"チャ",
			"チュ",
			"チョ",
			"ヂャ",
			"ヂュ",
			"ヂョ",
			"ニャ",
			"ニュ",
			"ニョ",
			"ヒャ",
			"ヒュ",
			"ヒョ",
			"ビャ",
			"ビュ",
			"ビョ",
			"ピャ",
			"ピュ",
			"ピョ",
			"ミャ",
			"ミュ",
			"ミョ",
			"リャ",
			"リュ",
			"リョ"			
	};
	
	
	public static String[] hiraganaSyllablesTable = new String[] {
			"きゃ",
			"きゅ",
			"きょ",
			"ぎゃ",
			"ぎゅ",
			"ぎょ",
			"しゃ",
			"しゅ",
			"しょ",
			"じゃ",
			"じゅ",
			"じょ",
			"ちゃ",
			"ちゅ",
			"ちょ",
			"ぢゃ",
			"ぢゅ",
			"ぢょ",
			"にゃ",
			"にゅ",
			"にょ",
			"ひゃ",
			"ひゅ",
			"ひょ",
			"びゃ",
			"びゅ",
			"びょ",
			"ぴゃ",
			"ぴゅ",
			"ぴょ",
			"みゃ",
			"みゅ",
			"みょ",
			"りゃ",
			"りゅ",
			"りょ"			
	};
	
	
	/**
	 * Конвертит текст, написанный катаканой в хирагану
	 * 
	 * @param s
	 * @return
	 */
	public static String convertKatakanaWordToHiragana(String s) {
		StringBuilder result = new StringBuilder(s.length());
		for (int i = 0; i < s.length(); i++) {
			result.append(convertKatakanaCharToHiragana(s.charAt(i)));
		}
		return result.toString();
	}

	/**
	 * Конвертит символ катаканы в символ хираганы (только те варианты, для которых есть соответствия)
	 * 
	 * @param c
	 * @return
	 */
	public static char convertKatakanaCharToHiragana(char c) {
		if (c<=KATAKANA_ZERO_CHAR || c>(KATAKANA_ZERO_CHAR+CHAR_TABLE_SIZE-ODD_CHAR_COUNT_AT_TABLE_END)) {
			return c;
		}
		char hiraganaChar = (char)(c-CHAR_TABLE_SIZE);
		
		return hiraganaChar;
	}
	
	
	
	
	public static void main(String[] args) {
		
		/*
		char c = '\u30A0';
		System.out.println(c);
		System.out.println((int)c);
		System.out.println(Integer.toHexString(c));
		*/
		
		for (int i = KATAKANA_ZERO_CHAR; i < (KATAKANA_ZERO_CHAR+CHAR_TABLE_SIZE); i++) {
			char katakanaChar = (char)i;
			char hiraganaChar = convertKatakanaCharToHiragana(katakanaChar);
			System.out.println(katakanaChar+" - "+hiraganaChar);
		}

		System.out.println(convertKatakanaWordToHiragana("ワタクシ"));
		System.out.println(convertKatakanaWordToHiragana("パース"));
		
		
		
	}	
}
