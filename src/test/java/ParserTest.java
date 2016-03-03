import org.junit.Test;
import static org.junit.Assert.*;


public class ParserTest {

	// file paths assume that test is executed in the project root folder (..../datto/)
	private static final String PEOPLE_INPUT_FILE    = "./input/people.txt";
	private static final String WHEREFORE_INPUT_FILE = "./input/wherefore.txt";
	private static final String BANANA_INPUT_FILE    = "./input/banana.txt";

	@Test public void tesMaxRepeatingLetter() {
		Parser parser = new Parser();
		assertEquals( 2, parser.maxRepeatingLetter("hello") );
		assertEquals( 3, parser.maxRepeatingLetter("Everyone") );
		assertEquals( 3, parser.maxRepeatingLetter("Everyone????''''") );		
	}
	
	@Test public void testFindWordWithMaxRepeatingLetter() {
		Parser parser = new Parser();
		assertEquals( "people",     parser.findWordWithMaxRepeatingLetter(PEOPLE_INPUT_FILE) );
		assertEquals( "wherefore",  parser.findWordWithMaxRepeatingLetter(WHEREFORE_INPUT_FILE) );
		assertEquals( "Banana-Ape", parser.findWordWithMaxRepeatingLetter(BANANA_INPUT_FILE) );
	}
}