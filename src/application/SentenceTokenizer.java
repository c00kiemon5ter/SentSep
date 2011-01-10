package application;

import java.util.List;
import java.util.Locale;
import utils.TokenType;
import utils.TokenizationUtils;

/**
 * TokenizationUtils Wrapper for sentence boundary detection
 */
public class SentenceTokenizer {

	public static List<Integer> findSentenceBoundaries(String text) {
		return findSentenceBoundaries(text, Locale.getDefault());
	}

	public static List<Integer> findSentenceBoundaries(String text, Locale locale) {
		return TokenizationUtils.findBoundaries(text, TokenType.SENTENCE, locale);
	}

	public static String markSentenceBoundaries(String text) {
		return markSentenceBoundaries(text, Locale.getDefault(), TokenizationUtils.MARK);
	}

	public static String markSentenceBoundaries(String text, Locale locale) {
		return markSentenceBoundaries(text, locale, TokenizationUtils.MARK);
	}

	public static String markSentenceBoundaries(String text, char mark) {
		return markSentenceBoundaries(text, Locale.getDefault(), mark);
	}

	public static String markSentenceBoundaries(String text, Locale locale, char mark) {
		return TokenizationUtils.markBoundaries(text, TokenType.SENTENCE, locale, mark);
	}

	public static List<String> extractSentences(String text) {
		return extractSentences(text, Locale.getDefault());
	}

	public static List<String> extractSentences(String text, Locale locale) {
		return TokenizationUtils.extractTokens(text, TokenType.SENTENCE, locale);
	}
}
