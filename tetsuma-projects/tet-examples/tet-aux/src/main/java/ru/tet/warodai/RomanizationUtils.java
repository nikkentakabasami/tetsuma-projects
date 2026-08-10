package ru.tet.warodai;

import static ru.tet.warodai.KatakanaHiraganaUtils.*;

import java.util.Arrays;
/**
 * Осуществляет романизацию текста, написанного хираганой/катаканой (по системе Hepburn-а)
 * https://en.wikipedia.org/wiki/Romanization_of_Japanese
 * 
 * 
 * 
 * 
 * @author tetsuma
 *
 */
public class RomanizationUtils {



	
	public static boolean isHiraganaChar(char c) {
		return (c>=HIRAGANA_ZERO_CHAR && c<(HIRAGANA_ZERO_CHAR+CHAR_TABLE_SIZE));
	}
	public static boolean isKatakanaChar(char c) {
		return (c>=KATAKANA_ZERO_CHAR && c<(KATAKANA_ZERO_CHAR+CHAR_TABLE_SIZE));
	}
	
	
	
	public static String romanizeHiraganaChar(char c) {
		if (c<=HIRAGANA_ZERO_CHAR || c>(HIRAGANA_ZERO_CHAR+CHAR_TABLE_SIZE-ODD_CHAR_COUNT_AT_TABLE_END)) {
			return "";
		}
		return kanaSoundTable[(c-HIRAGANA_ZERO_CHAR)];
	}
	public static String romanizeKatakanaChar(char c) {
		if (c<=KATAKANA_ZERO_CHAR || c>(KATAKANA_ZERO_CHAR+CHAR_TABLE_SIZE-ODD_CHAR_COUNT_AT_TABLE_END)) {
			return "";
		}
		return kanaSoundTable[(c-KATAKANA_ZERO_CHAR)];
	}

	public static String romanizeKanaChar(char c) {
		if (c<=HIRAGANA_ZERO_CHAR || c>(KATAKANA_ZERO_CHAR+CHAR_TABLE_SIZE-ODD_CHAR_COUNT_AT_TABLE_END)) {
			return "";
		}
		
		if (c>=KATAKANA_ZERO_CHAR) {
			return kanaSoundTable[(c-KATAKANA_ZERO_CHAR)];
		} else {
			return kanaSoundTable[(c-HIRAGANA_ZERO_CHAR)];
		}
		
	}
	
	
	

	
	public static String romanizeKanaSyllable(String s, int from) {
		
		String syllable = s.substring(from,from+2);
		char fc = syllable.charAt(0);
		char sc = syllable.charAt(1);
		
		//сокуон
		if (fc=='っ' || fc=='ッ') {
			String r = romanizeKanaChar(sc);
			if (r.length()==0) {
				return null;
			}
			return r.charAt(0)+r;
			
		//удлинение гласной (используется в катакане)
		} else if (sc=='ー') {
			String r = romanizeKanaChar(fc);
//			return r+r.charAt(r.length()-1);
			return r+":";
		}
		
		
		
		//TODO  есть большие и маленькие гласные буквы!!!
		if (sc=='ゃ' || sc=='ゅ' || sc=='ょ') {
			
		}
		
		int ind;
		if (fc<KATAKANA_ZERO_CHAR) {
			ind = Arrays.binarySearch(hiraganaSyllablesTable, syllable);
		} else {
			ind = Arrays.binarySearch(hiraganaSyllablesTable, syllable);
		}
		if (ind<0) {
			return null;
		}
		return romajiSyllablesTable[ind];
	}	
	
	
	
	

	public static String romanizeKanaText(String s) {
		
		if (s.length()==0) {
			return s;
		}
		if (s.length()==1) {
			return ""+romanizeKanaChar(s.charAt(0)) ;
		}
		
		StringBuilder result = new StringBuilder();
		int i=0;
		do {

			char currChar = s.charAt(i);
			
			if (currChar==',' || currChar=='･') {
				result.append(currChar);
				i++;
				continue;
			}
			
			String romajiSyllable = null;
			if ((i+1)<s.length()) {
				romajiSyllable = romanizeKanaSyllable(s,i);
			}
			
			if (romajiSyllable!=null) {
				result.append(romajiSyllable);
				i+=2;
			} else {
				result.append(romanizeKanaChar(currChar));
				i++;
			}
			
		} while (i<s.length());
		
		return result.toString();
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public static String romanizeHiraganaSyllable(String s, int from) {
		
		String syllable = s.substring(from,from+2);
		char fc = syllable.charAt(0);
		char sc = syllable.charAt(1);
		
		//сокуон
		if (fc=='っ') {
			String r = romanizeHiraganaChar(sc);
			return r.charAt(0)+r;
			
		//удлинение гласной (используется в катакане)
		} else if (sc=='ー') {
			String r = romanizeHiraganaChar(sc);
			return r+r.charAt(r.length()-1);
		
			
			
		}
		
		
		
		//TODO  есть большие и маленькие гласные буквы!!!
		if (sc=='ゃ' || sc=='ゅ' || sc=='ょ') {
			
		}

		
		int ind = Arrays.binarySearch(hiraganaSyllablesTable, syllable);
		if (ind<0) {
			return null;
		}
		return romajiSyllablesTable[ind];
	}
	

	
	
	public static String romanizeHiraganaText(String s) {
		
		if (s.length()==0) {
			return s;
		}
		if (s.length()==1) {
			return ""+romanizeHiraganaChar(s.charAt(0)) ;
		}
		
		StringBuilder result = new StringBuilder();
		int i=0;
		do {

			String romajiSyllable = null;
			if ((i+1)<s.length()) {
				romajiSyllable = romanizeHiraganaSyllable(s,i);
			}
			
			if (romajiSyllable!=null) {
				result.append(romajiSyllable);
				i+=2;
			} else {
				result.append(romanizeHiraganaChar(s.charAt(i)));
				i++;
			}
			
			
			
		} while (i<s.length());
		
		return result.toString();
		
	}
	*/
	
	
	public static void main(String[] args) {

		/*
		Arrays.sort(katakanaSyllablesTable);
		for (int i = 0; i < katakanaSyllablesTable.length; i++) {
			System.out.println("\""+katakanaSyllablesTable[i]+"\",");
			
		}
		
 */
		System.out.println(romanizeKanaText("じゅう"));
		
		/*
		System.out.println(romanizeKanaText("けさたくさんコーヒをのみました"));
		System.out.println(romanizeKanaText("かえっていいって"));
		System.out.println(romanizeKanaText("しょくじをたべた"));
		System.out.println(romanizeKanaText("びょいんににゅういんしました"));
		System.out.println(romanizeKanaText("よんじゅう"));
		*/
		
		
		
		/*
		for (int i = HIRAGANA_ZERO_CHAR; i < (HIRAGANA_ZERO_CHAR+CHAR_TABLE_SIZE); i++) {
			char hiraganaChar = (char)(i);
			System.out.println(hiraganaChar+" - "+romanizeHiraganaChar(hiraganaChar));
		}
		*/
		
	}
	
	
	
	
	
	
}
