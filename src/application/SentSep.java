package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import utils.TokenType;
import utils.TokenizationUtils;

/**
 *
 * @author c00kiemon5ter
 */
public class SentSep {

	private TokenType type;
	private Locale locale;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SentSep ss = new SentSep(TokenType.SENTENCE, new Locale("el", "GR"));
		if (args.length != 2) {
			ss.usage();
		}
		List<File> trainlist = ss.listFiles(args[0]);
		List<File> testlist = ss.listFiles(args[1]);
		List<String> traindocs = ss.readFiles(trainlist);
		List<String> testdocs = ss.readFiles(testlist);
		ss.train(traindocs);
		ss.evaluate(testdocs);
		ss.testUtils();
	}

	public SentSep(TokenType type, Locale locale) {
		this.type = type;
		this.locale = locale;
	}

	private void usage() {
		System.err.printf("usage: java -jar %s "
				  + "training_collection_dir "
				  + "testing_collection_dir\n",
				  SentSep.class.getSimpleName());
		System.exit(1);
	}

	private List<File> listFiles(String dirname) {
		return Arrays.asList(new File(dirname).listFiles());
	}

	private List<String> readFiles(List<File> trainlist) throws FileNotFoundException, IOException {
		List<String> documents = new ArrayList<String>(trainlist.size());
		Scanner scanner;
		for (File file : trainlist) {
			scanner = new Scanner(file, "ISO-8859-7");
			StringBuilder document = new StringBuilder();
			while (scanner.hasNextLine()) {
				document.append(scanner.nextLine());
				document.append(System.getProperty("line.separator"));
			}
			documents.add(document.toString().trim());
			scanner.close();
		}
		return documents;
	}

	private void train(List<String> documents) {
	}

	private void evaluate(List<String> testdocs) {
	}

	private void testUtils() {
		String en = "The name org.gnome.SessionManager was not provided "
			    + "by any government. What about the U.K. or the U.S. "
			    + "government? She stopped. She said, \"Hello there.\" "
			    + "and then went on. It's what Mr. Brown would do. "
			    + "Also that's her ip: 192.168.0.1. It's a deal. "
			    + "It's their decision, said the U.S. What can I say?";
		String gr = "Τελειώνει η \"προστασία\" των μισθώσεων κατοικιών\n"
			    + "ΛΗΓΕΙ σήμερα και τυπικά το προστατευτικό καθεστώς των μισθώσεων κατοικιών, "
			    + "το οποίο ουσιαστικά έχει λήξει από τον περασμένο Ιούλιο, οπότε τα ενοίκια "
			    + "κατοικιών είχαν μπει στην τροχιά της πλήρους απελευθέρωσης. "
			    + "Συγκεκριμένα από σήμερα 1 Ιουλίου, θα αρχίσουν να γίνονται εκτελεστές οι "
			    + "εξωστικές αγωγές που είχαν αποφασισθεί δικαστικώς για μισθώσεις που "
			    + "συμπλήρωσαν 4ετία και για τις οποίες, από πλευράς ιδιοκτήτη, δεν εκδηλώθηκε "
			    + "πρόθεση ανανέωσης του συμβολαίου. "
			    + "Το προστατευτικό καθεστώς των μισθώσεων αυτών έχει λήξει από τον Ιούλιο του "
			    + "1996. Σύμφωνα όμως με τον νόμο, οι ιδιοκτήτες δεν μπορούσαν να καταθέσουν "
			    + "αγωγή έξωσης πριν από την 1η Ιανουαρίου 1997, ενώ η εφαρμογή της δικαστικής "
			    + "απόφασης προβλέποταν να αρχίσει ανά εξάμηνο μετά από την έκδοση της απόφασης, "
			    + "δηλαδή από 1 Ιουλίου 1997. "
			    + "Υπενθυμίζεται, εξάλλου, ότι από τις 31 Αυγούστου 1997 θα λήγει κάθε μίσθωση "
			    + "επαγγελματικής στέγης που συμπλήρωσε ή θα συμπληρώσει 12ετία. Από 1ης "
			    + "Σεπτεμβρίου 1997 οι ιδιοκτήτες μπορούν να ζητήσουν αποχώρηση των ενοικιαστών, "
			    + "υπό την προϋπόθεση ότι οι τελευταίοι βρίσκονται στο ακίνητο επί 12 χρόνια και "
			    + "αφού αποζημιωθούν με 12 μισθώματα. "
			    + "Η ενεργοποίηση, από σήμερα των εξωστικών αγωγών για μισθώσεις κατοικιών, "
			    + "δημιουργεί -όπως είναι επόμενο- τριβές ανάμεσα σε μισθωτές και εκμισθωτές, "
			    + "πιστεύεται όμως ότι δεν πρόκειται να προκαλέσει ιδιαίτερα προβλήματα στο χώρο "
			    + "της ενοικιαζόμενης κατοικίας.";
		String text = en;

		List<Integer> bounds = TokenizationUtils.findBoundaries(text, type, locale);
		System.out.println(bounds.toString());
		System.out.println("---------------------");

		String marked = TokenizationUtils.markBoundaries(text, type, locale);
		System.out.printf("%s\n%s\n", text, marked);
		System.out.println("---------------------");

		List<String> tokens = TokenizationUtils.extractTokens(text, type, locale);
		for (String token : tokens) {
			System.out.println(token);
		}
		System.out.println("---------------------");
	}
}
