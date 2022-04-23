package comp3111.covid;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.GUI.GUISelectChartHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.data.DataCache;
import comp3111.covid.data.VaccineAnalysis;
import comp3111.covid.data.VaccineObject;

public class TaskC2ChartTest {
	private ArrayList<String> code;
	private GUISelectChartHandler handler;
	private String startDate = "12/23/2020";
	private String endDate = "12/24/2020";
	@Before
	public void setUp() throws Exception {
		code = new ArrayList<String>();
		code.add(CountryCode.HK.getName());
		code.add(CountryCode.US.getName());
		handler = new GUISelectChartHandler(code, startDate, endDate);
		DataCache.getCache().initalizeData("COVID_Dataset_v1.0.csv");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testDataAnalysis() {
		ArrayList<VaccineObject> expected = new ArrayList<VaccineObject>();
		VaccineObject a = new VaccineObject(CountryCode.HK, 0 , (float) 0.0);
		VaccineObject a1 = new VaccineObject(CountryCode.HK, 0 , (float) 0.0);
		VaccineObject b = new VaccineObject(CountryCode.US, 0, (float) 0.0);
		VaccineObject b1 = new VaccineObject(CountryCode.US, 0, (float) 0.0);
		expected.add(a);
		expected.add(a1);
		expected.add(b);
		expected.add(b1);
		
		VaccineAnalysis analyse = new VaccineAnalysis("COVID_Dataset_v1.0.csv", handler);
		assertEquals(expected.get(0).getCountry(),analyse.getResult().get(0).getCountry());
		assertEquals(expected.get(1).getCountry(),analyse.getResult().get(1).getCountry());
		assertEquals(expected.get(2).getCountry(),analyse.getResult().get(2).getCountry());
		assertEquals(expected.get(3).getCountry(),analyse.getResult().get(3).getCountry());
		assertEquals(expected.get(0).getPercentagevaccinated(), analyse.getResult().get(0).getPercentagevaccinated());
		assertEquals(expected.get(1).getPercentagevaccinated(), analyse.getResult().get(1).getPercentagevaccinated());
		assertEquals(expected.get(2).getPercentagevaccinated(), analyse.getResult().get(2).getPercentagevaccinated());
		assertEquals(expected.get(3).getPercentagevaccinated(), analyse.getResult().get(3).getPercentagevaccinated());
		
	}
}
