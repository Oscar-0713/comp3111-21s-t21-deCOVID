package comp3111.covid;

import static org.junit.Assert.*;

import comp3111.covid.data.DataForecast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DataForecastTest {
	private static ArrayList<Float> inputs;
	private static HashMap<Character, List<Double>> results;
	
	
	@Before
	public void setUp() throws Exception {
		inputs = new ArrayList<Float>(Arrays.asList(0.0f, 0.0f, 2.0f, 3.0f, 5.0f, 8.0f, 9.0f, 8.0f));
		results = DataForecast.predictValues(5, inputs, 2, 1, 2);
	}

	@Test
	public void testP() {
		assertNotEquals(results.get('P'), null);
		assertFalse(results.get('P').isEmpty());
	}
	
	@Test
	public void testU() {
		assertNotEquals(results.get('U'), null);
		assertFalse(results.get('U').isEmpty());
	}
	
	@Test
	public void testL() {
		assertNotEquals(results.get('L'), null);
		assertFalse(results.get('L').isEmpty());
	}

}
