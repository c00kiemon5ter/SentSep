package utils;

import java.text.BreakIterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class TokenizationUtils {

	public final static char DEFAULT_MARK = '^';

	public static List<Integer> findBoundaries(String text, TokenType type) {
		return findBoundaries(text, type, Locale.getDefault());
	}

	public static List<Integer> findBoundaries(String text, TokenType type, Locale locale) {
		List<Integer> boundaries = new LinkedList<Integer>();
		BreakIterator iter = TokenizerFactory.createIterator(type, locale);

		iter.setText(text);
		for (int pos = iter.first(); pos != BreakIterator.DONE; pos = iter.next()) {
			boundaries.add(pos);
		}

		return boundaries;
	}

	public static String markBoundaries(String text, TokenType type) {
		return markBoundaries(text, type, Locale.getDefault(), DEFAULT_MARK);
	}

	public static String markBoundaries(String text, TokenType type, Locale locale) {
		return markBoundaries(text, type, locale, DEFAULT_MARK);
	}

	public static String markBoundaries(String text, TokenType type, char mark) {
		return markBoundaries(text, type, Locale.getDefault(), mark);
	}

	public static String markBoundaries(String text, TokenType type, Locale locale, char mark) {
		StringBuilder marks = new StringBuilder();
		BreakIterator iter = TokenizerFactory.createIterator(type, locale);

		marks.setLength(text.length() + 1);
		for (int pos = 0; pos < marks.length(); pos++) {
			marks.setCharAt(pos, ' ');
		}

		iter.setText(text);
		for (int pos = iter.first(); pos != BreakIterator.DONE; pos = iter.next()) {
			marks.setCharAt(pos, mark);
		}

		return marks.toString();
	}

	public static List<String> extractTokens(String text, TokenType type) {
		return extractTokens(text, type, Locale.getDefault());
	}

	public static List<String> extractTokens(String text, TokenType type, Locale locale) {
		List<String> tokens = new LinkedList<String>();
		BreakIterator iter = TokenizerFactory.createIterator(type, locale);

		iter.setText(text);
		for (int start = iter.first(), end = iter.next();
		     end != BreakIterator.DONE;
		     start = end, end = iter.next()) {
			tokens.add(text.substring(start, end));
		}

		return tokens;
	}
}
