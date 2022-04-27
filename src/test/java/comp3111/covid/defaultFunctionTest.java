package comp3111.covid;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class defaultFunctionTest {
	private static final String DATASET = "COVID_Dataset_v1.0.csv";
	private static final String ISOCODE = "HKG";
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConfirmedCase() {
		DataAnalysis.getConfirmedCases(DATASET, ISOCODE);
	}
	
	@Test
	public void testDeathCase() {
		DataAnalysis.getConfirmedDeaths(DATASET, ISOCODE);
	}
	
	@Test
	public void testVaccine() {
		DataAnalysis.getRateOfVaccination(DATASET, ISOCODE);
	}

}
