import org.junit.Test;
import static org.junit.Assert.*;


public class ParserTest {

    // file paths assume that test is executed in the project root folder (..../datto/)
    private static final String PEOPLE_INPUT_FILE    = "./input/people.txt";
    private static final String WHEREFORE_INPUT_FILE = "./input/wherefore.txt";
    private static final String BANANA_INPUT_FILE    = "./input/banana.txt";
    private static final String CARROT_INPUT_FILE    = "./input/carrot.txt";

    @Test public void tesMaxRepeatingLetter() {
        Parser parser = new Parser();
        assertEquals( 2, parser.maxRepeatingLetter("hello"));
        assertEquals( 3, parser.maxRepeatingLetter("Everyone"));
        assertEquals( 3, parser.maxRepeatingLetter("Every????one"));
    }

    @Test public void testFindWordWithMaxRepeatingLetter() {
        Parser parser = new Parser();

        // assert the simple case
        assertEquals( "wherefore",  parser.findWordWithMaxRepeatingLetter(WHEREFORE_INPUT_FILE));

        // assert that in the case of a tie the first word appearing in the file wins
        assertEquals( "people",     parser.findWordWithMaxRepeatingLetter(PEOPLE_INPUT_FILE));

        // assert that hyphenated word is recognized as a single word
        assertEquals( "Banana-Ape", parser.findWordWithMaxRepeatingLetter(BANANA_INPUT_FILE));

        // assert that punctuation at end of sentence is not counted as part of the word, return "carrot", not "carrot."
        assertEquals( "carrot",     parser.findWordWithMaxRepeatingLetter(CARROT_INPUT_FILE));
    }
}