import org.junit.Test;
import static org.junit.Assert.*;


public class ParserTest {

	private static final String BANANA_INPUT_FILE = "/Users/scott/dev/projects/datto/input/banana.txt";
	private static final String PEOPLE_INPUT_FILE = "/Users/scott/dev/projects/datto/input/people.txt";
	private static final String WHEREFORE_INPUT_FILE = "/Users/scott/dev/projects/datto/input/wherefore.txt";

	@Test public void tesMaxRepeatingLetter() {
		Parser parser = new Parser();
		assertEquals( 2, parser.maxRepeatingLetter("hello") );
		assertEquals( 3, parser.maxRepeatingLetter("Everyone") );
	}
	
	@Test public void testFindWordWithMaxRepeatingLetter() {
		Parser parser = new Parser();
		assertEquals( "banana", parser.findWordWithMaxRepeatingLetter(BANANA_INPUT_FILE) );
		assertEquals( "people", parser.findWordWithMaxRepeatingLetter(PEOPLE_INPUT_FILE) );
		assertEquals( "wherefore",  parser.findWordWithMaxRepeatingLetter(WHEREFORE_INPUT_FILE) );
	}
}