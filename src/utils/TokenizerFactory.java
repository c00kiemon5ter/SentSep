package utils;

import java.text.BreakIterator;
import java.util.Locale;

public class TokenizerFactory {

	public static BreakIterator createIterator(TokenType type) {
		return createIterator(type, Locale.getDefault());
	}

	public static BreakIterator createIterator(TokenType type, Locale locale) throws NullPointerException {
		switch (type) {
			case CHARACTER:
				return BreakIterator.getCharacterInstance(locale);
			case WORD:
				return BreakIterator.getWordInstance(locale);
			case LINE:
				return BreakIterator.getLineInstance(locale);
			case SENTENCE:
				return BreakIterator.getSentenceInstance(locale);
			default:
				throw new NullPointerException("unknown token type selector.");
		}
	}
}
