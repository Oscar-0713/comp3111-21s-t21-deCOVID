package comp3111.covid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.GUI.GUISelectTableHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.data.DeathDataAnalysis;
import comp3111.covid.data.DeathObject;

public class TaskB1TableTest {
	private ArrayList<String> code;
	private GUISelectTableHandler handler;
	private String date = "12/23/2020";
	@Before
	public void setUp() throws Exception {
		code = new ArrayList<String>();
		code.add(CountryCode.HK.getName());
		code.add(CountryCode.US.getName());
		handler = new GUISelectTableHandler(code, date);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDataAnalysis() {
		ArrayList<DeathObject> expected = new ArrayList<DeathObject>();
		DeathObject a = new DeathObject(CountryCode.HK, 133 , (float) 17.74);
		DeathObject b = new DeathObject(CountryCode.US, 331641, (float) 1001.929);
		expected.add(a);
		expected.add(b);
		
		DeathDataAnalysis analyse = new DeathDataAnalysis("COVID_Dataset_v1.0.csv", handler);
		assertEquals(expected.get(0).getCountry(),analyse.getResult().get(0).getCountry());
		assertEquals(expected.get(1).getCountry(),analyse.getResult().get(1).getCountry());
		assertEquals(expected.get(0).getDeath(), analyse.getResult().get(0).getDeath());
		assertEquals(expected.get(1).getDeath(), analyse.getResult().get(1).getDeath());
		assertEquals(expected.get(0).getDeathpermillion(), analyse.getResult().get(0).getDeathpermillion());
		assertEquals(expected.get(1).getDeathpermillion(), analyse.getResult().get(1).getDeathpermillion());
		
	}
}
