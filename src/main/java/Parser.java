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
import java.util.stream.*;

public class Parser {

	private static final boolean LOG = true;
	private static final String LETTER_REGEX = "[a-z]";
	
	private static class Result {
		private String word = "";
		private int maxRepeat = 0;

		public void set(String word, int maxRepeat) {
			this.word = word;
			this.maxRepeat = maxRepeat;
		}

		public String getWord() { return word; }
		public int getMaxRepeat() { return maxRepeat; }
	}

	/**
	 * Reads input from the file located at the given inputFileName and returns the word with the highest count of a 
	 * repeating letter.
	 */
	public String findWordWithMaxRepeatingLetter(String inputFileName) {
		// track word with max repeating letter
		final Result result = new Result();

		// process the input file via a stream of lines 
		try (Stream<String> lineStream = Files.lines(Paths.get(inputFileName))) {
			// split on whitespace
			lineStream.forEach( line -> {
				List<String> words = Stream.of(line)
						            .map(w -> w.split("\\s+")).flatMap(Arrays::stream)
						            .collect(Collectors.toList());

				// track word and max repeating letter count
				words.stream().forEach( word -> {					
					int count = maxRepeatingLetter(word);
					if (count > result.getMaxRepeat()) {
						result.set(word, count);
					} 
				});
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result.getWord();
	}



	/**
	 * Returns the number of occurrences of the most repeating letter in the given word.
	 */
	public int maxRepeatingLetter(String word) {
		// track letter count per letter in the word
		final Map<Character,Integer> letterCounts = new HashMap<Character,Integer>();

		// increment letter occurrence count in the word as each letter is encontered
		Stream<Character> letterStream = word.toLowerCase().chars().mapToObj( i -> (char)i );
		
		letterStream.filter( c -> c.toString().matches(LETTER_REGEX) )
			.forEach( letter -> {
				int count = letterCounts.containsKey(letter) ? letterCounts.get(letter) + 1 : 1;
				letterCounts.put(letter, count);
		});
		
		// return the max letter count, 0 if no actual letters in the word
		int maxRepeat = letterCounts.values().stream().max(Integer::compare).orElse(0);
		return maxRepeat;
	}

	private static void log(String msg) {
		if (LOG) System.out.println(msg);		
	}

}