// Problem

// In some English words, there is a letter that appears more than once. Search through a sample of text to find the 
// word with a letter that is repeated more times than any other letter is repeated in any other word. When there is a 
// tie between two words, choose the word that appeared first in the sample.  The text sample will contain only alphabetic
// characters (“a” through “z” and “A” through “Z”), whitespace, and punctuation marks. The words will be separated by 
// whitespace. A letter is considered to be the same letter regardless of whether it appears in uppercase or lowercase. 
// Any punctuation marks should be ignored—so, in particular, contractions, possessives, and hyphenated words count as a
// single word.

// Each sample is stored in a text file: Write a function that accepts a file path as its argument, and returns the 
// chosen word as its output.

// Example:

// Input: “O Romeo, Romeo, wherefore art thou Romeo?”
// Output: “wherefore”

// Explanation: The letter “e” is repeated three times in the word “wherefore” and this is more than any other letter is
// repeated in any other word!

// Example:

// Input: “Some people feel the rain, while others just get wet.”
// Output: “people”

// Explanation: Both “people” and “feel” have a letter that is repeated twice within the word. 
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;
import java.util.regex.Pattern;

public class Parser {

	private static final String LETTER_REGEX = "[a-z]";
	private static final Pattern LETTER_PATTERN = Pattern.compile(LETTER_REGEX);
	private static final Predicate<String> LETTER_PREDICATE = LETTER_PATTERN.asPredicate();


	/**
	 * Reads input from the file located at the given inputFileName and returns the word with the highest count of a 
	 * repeating letter.
	 */
	public String findWordWithMaxRepeatingLetter(String inputFileName) {
		// track word with max repeating letter
		final MaxRepeat maxRepeat = new MaxRepeat();

		// process the input file via a stream of lines 
		try (Stream<String> lineStream = Files.lines(Paths.get(inputFileName))) {
			// split on whitespace
			lineStream.forEach( line -> {
				List<String> words = Stream.of(line)
										.map(w -> w.split("[\\s]+")).flatMap(Arrays::stream)
										.collect(Collectors.toList());

				// track word and max repeating letter count
				words.stream().forEach( word -> {
					int count = maxRepeatingLetter(word);
					if (count > maxRepeat.getCount()) {
						maxRepeat.set( trim(word), count );
					} 
				});
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return maxRepeat.getWord();
	}

	// edge case: last word in a sentence: drop the trailing punctuation mark
	private String trim(String word) {
		return word.matches(".*[.?!]") ? word.substring(0, word.length()-1) : word;
	}

	/**
	 * Returns the number of occurrences of the most repeating letter in the given word.
	 */
	public int maxRepeatingLetter(String word) {
		// track letter count per letter in the word
		final Map<String,Integer> letterCounts = new HashMap<String,Integer>();
		
		// increment letter occurrence count in the word as each letter is encontered
		word.toLowerCase().chars()
			.mapToObj( i -> String.valueOf( (char)i ))
			.filter(LETTER_PREDICATE)
			.forEach( letter -> {
				int count = letterCounts.containsKey(letter) ? letterCounts.get(letter) + 1 : 1;
				letterCounts.put(letter, count);
			}
		);
		
		// return the max letter count
		int maxCount = letterCounts.values().stream().max(Integer::compare).orElse(0);
		return maxCount;
	}


	private static class MaxRepeat {
		private String word = "";
		private int count = 0;

		public void set(String word, int count) {
			this.word = word;
			this.count = count;
		}

		public String getWord() { return word; }
		public int getCount() { return count; }
	}
	
}