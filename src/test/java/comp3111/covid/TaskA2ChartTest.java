package comp3111.covid;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.GUI.GUISelectChartHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.data.DataCache;
import comp3111.covid.data.CaseDataAnalysis;
import comp3111.covid.data.CaseObject;

public class TaskA2ChartTest {
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
		ArrayList<CaseObject> expected = new ArrayList<CaseObject>();
		CaseObject a = new CaseObject(CountryCode.HK, 8353, (float) 1114.1808);
		CaseObject a1 = new CaseObject(CountryCode.HK, 8424, (float) 1123.6512);
		CaseObject b = new CaseObject(CountryCode.US, 18582087, (float) 56138.79);
		CaseObject b1 = new CaseObject(CountryCode.US, 18776242, (float) 56725.35);
		expected.add(a);
		expected.add(a1);
		expected.add(b);
		expected.add(b1);
		
		CaseDataAnalysis analyse = new CaseDataAnalysis("COVID_Dataset_v1.0.csv", handler);
		assertEquals(expected.get(0).getCountry(),analyse.getResult().get(0).getCountry());
		assertEquals(expected.get(1).getCountry(),analyse.getResult().get(1).getCountry());
		assertEquals(expected.get(2).getCountry(),analyse.getResult().get(2).getCountry());
		assertEquals(expected.get(3).getCountry(),analyse.getResult().get(3).getCountry());
		assertEquals(expected.get(0).getCasepermillion(), analyse.getResult().get(0).getCasepermillion());
		assertEquals(expected.get(1).getCasepermillion(), analyse.getResult().get(1).getCasepermillion());
		assertEquals(expected.get(2).getCasepermillion(), analyse.getResult().get(2).getCasepermillion());
		assertEquals(expected.get(3).getCasepermillion(), analyse.getResult().get(3).getCasepermillion());
	
	}
	
}
