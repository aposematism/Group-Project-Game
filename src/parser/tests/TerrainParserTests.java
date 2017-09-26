package parser.tests;
import org.junit.Test;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.IOException;
import parser.TerrainParser;



/** 
 * This class will handle all white-box tests for the Terrain Parser and ensure it functions as far as I can tell.
 * @author - Jordan
 * */
public class TerrainParserTests {
	
	File testTerrain = new File("res/test_terrain_file.txt");
	/** 
	 * Testing the first method for initializing properly
	 * */
	@Test
	public void init_scanner_load() throws IOException{
		TerrainParser tp = new TerrainParser();
		try{
			tp.init_scanner(testTerrain);
		}
		catch(IOException e){
			fail("IOException during the Terrain Parser Initialization");
		}			
	}
}
