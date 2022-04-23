package comp3111.covid;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.GUI.GUISelectTableHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.data.DataCache;
import comp3111.covid.data.CaseDataAnalysis;
import comp3111.covid.data.CaseObject;

public class TaskA1TableTest {
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
		ArrayList<CaseObject> expected = new ArrayList<CaseObject>();
		CaseObject a = new CaseObject(CountryCode.HK, 8353, (float) 1114.181);
		CaseObject b = new CaseObject(CountryCode.US, 18582087, (float) 56138.787);
		expected.add(a);
		expected.add(b);
		
		CaseDataAnalysis analyse = new CaseDataAnalysis("COVID_Dataset_v1.0.csv", handler);
		assertEquals(expected.get(0).getCountry(),analyse.getResult().get(0).getCountry());
		assertEquals(expected.get(1).getCountry(),analyse.getResult().get(1).getCountry());
		assertEquals(expected.get(0).getCase(), analyse.getResult().get(0).getCase());
		assertEquals(expected.get(1).getCase(), analyse.getResult().get(1).getCase());
		
	}
}
