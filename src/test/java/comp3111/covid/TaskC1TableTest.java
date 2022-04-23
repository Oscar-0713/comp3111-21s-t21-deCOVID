package comp3111.covid;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.GUI.GUISelectTableHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.data.VaccineAnalysis;
import comp3111.covid.data.VaccineObject;
import comp3111.covid.data.DataCache;
import comp3111.covid.data.VaccineAnalysis;
import comp3111.covid.data.VaccineObject;

public class TaskC1TableTest {
	private ArrayList<String> code;
	private GUISelectTableHandler handler;
	private String date = "12/23/2020";
	@Before
	public void setUp() throws Exception {
		code = new ArrayList<String>();
		code.add(CountryCode.HK.getName());
		code.add(CountryCode.US.getName());
		handler = new GUISelectTableHandler(code, date);
		DataCache.getCache().initalizeData("COVID_Dataset_v1.0.csv");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testDataAnalysis() {
		ArrayList<VaccineObject> expected = new ArrayList<VaccineObject>();
		VaccineObject a = new VaccineObject(CountryCode.HK, 0, (float) 0);
		VaccineObject b = new VaccineObject(CountryCode.US, 0, (float) 0);
		expected.add(a);
		expected.add(b);
		
		VaccineAnalysis analyse = new VaccineAnalysis("COVID_Dataset_v1.0.csv", handler);
		assertEquals(expected.get(0).getCountry(),analyse.getResult().get(0).getCountry());
		assertEquals(expected.get(1).getCountry(),analyse.getResult().get(1).getCountry());
		assertEquals(expected.get(0).getFullyvaccinated(), analyse.getResult().get(0).getFullyvaccinated());
		assertEquals(expected.get(1).getFullyvaccinated(), analyse.getResult().get(1).getFullyvaccinated());
		
	}
	
}
