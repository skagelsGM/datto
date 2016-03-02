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
	
	public static void main(String[] args) {
		System.out.println("Hello Datto!");
	}


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
	 * Reads input from the file located at the given inputFilepath and returns the word with the highest count of a 
	 * repeating letter.
	 */
	public String findWordWithMaxRepeatingLetter(String inputFileName) {
		// track word with max repeating letter
		final Result result = new Result();


		// process the input file via a stream of lines 
		try (Stream<String> lineStream = Files.lines(Paths.get(inputFileName))) {
			// split on whitespace
			lineStream.forEach( line -> {
				List<String> words = Stream
		            .of(line)
		            .map(w -> w.split("\\s+")).flatMap(Arrays::stream)
		            .collect(Collectors.toList());					
				words.stream().forEach( word-> {
					// - track word and max repeating letter count
					int count = maxRepeatingLetter(w);
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
	 * Returns the count of the most repeating letter in the given word
	 */
	public int maxRepeatingLetter(String word) {
		// track count of repeating letters in word and return the max
		final Map<Character,Integer> letterCounts = new HashMap<Character,Integer>();

		Stream<Character> letterStream = word.toLowerCase().chars().mapToObj( i -> (char)i );
		letterStream.forEach( letter -> {
			int count = letterCounts.containsKey(letter) ? letterCounts.get(letter) + 1 : 1;
			letterCounts.put(letter, count);
		});
		
		letterCounts.keySet().stream().forEach( k -> log(k + " count: " + letterCounts.get(k)));

		int maxRepeat = letterCounts.values().stream().max(Integer::compare).get();
		return maxRepeat;
	}

	private static void log(String msg) {
		System.out.println(msg);
	}
}