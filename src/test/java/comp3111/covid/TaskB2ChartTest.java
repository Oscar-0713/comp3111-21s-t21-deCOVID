package comp3111.covid;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.GUI.GUISelectChartHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.data.DataCache;
import comp3111.covid.data.DeathDataAnalysis;
import comp3111.covid.data.DeathObject;

public class TaskB2ChartTest {
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
		ArrayList<DeathObject> expected = new ArrayList<DeathObject>();
		DeathObject a = new DeathObject(CountryCode.HK, 133 , (float) 17.740458);
		DeathObject a1 = new DeathObject(CountryCode.HK, 135 , (float) 18.007233);
		DeathObject b = new DeathObject(CountryCode.US, 331641, (float) 1001.9285);
		DeathObject b1 = new DeathObject(CountryCode.US, 334540, (float) 1010.68677);
		expected.add(a);
		expected.add(a1);
		expected.add(b);
		expected.add(b1);
		
		DeathDataAnalysis analyse = new DeathDataAnalysis("COVID_Dataset_v1.0.csv", handler);
		assertEquals(expected.get(0).getCountry(),analyse.getResult().get(0).getCountry());
		assertEquals(expected.get(1).getCountry(),analyse.getResult().get(1).getCountry());
		assertEquals(expected.get(2).getCountry(),analyse.getResult().get(2).getCountry());
		assertEquals(expected.get(3).getCountry(),analyse.getResult().get(3).getCountry());
		assertEquals(expected.get(0).getDeathpermillion(), analyse.getResult().get(0).getDeathpermillion());
		assertEquals(expected.get(1).getDeathpermillion(), analyse.getResult().get(1).getDeathpermillion());
		assertEquals(expected.get(2).getDeathpermillion(), analyse.getResult().get(2).getDeathpermillion());
		assertEquals(expected.get(3).getDeathpermillion(), analyse.getResult().get(3).getDeathpermillion());
		
	}
}
