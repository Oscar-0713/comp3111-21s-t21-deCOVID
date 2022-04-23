package comp3111.covid;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.GUI.GUISelectTableHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.data.DataCache;
import comp3111.covid.data.VaccineAnalysis;
import comp3111.covid.data.VaccineObject;

public class TaskC1TableTest {
	private ArrayList<String> code;
	private GUISelectTableHandler handler;
	private String date = "4/3/2021";
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
		ArrayList<VaccineObject> expected = new ArrayList<>();
		expected.add(new VaccineObject(CountryCode.HK, 68934, (float) ( 68934f/7496988f *100f)));
		expected.add(new VaccineObject(CountryCode.US, 59858146, (float)(59858146f/331002647f*100f)));
		VaccineAnalysis analysis = new VaccineAnalysis("COVID_Dataset_v1.0.csv", handler);
		ArrayList<VaccineObject> output = new ArrayList<>();
		output = analysis.getResult();
		assertEquals(expected.get(0).getFullyvaccinated(), output.get(0).getFullyvaccinated());
		assertEquals(expected.get(1).getFullyvaccinated(), output.get(1).getFullyvaccinated());
		
		assertEquals(Float.parseFloat(expected.get(0).getPercentagevaccinated()), Float.parseFloat(output.get(0).getPercentagevaccinated()), 0.01);
		assertEquals(Float.parseFloat(expected.get(1).getPercentagevaccinated()), Float.parseFloat(output.get(1).getPercentagevaccinated()), 0.01);
		
	}

}
