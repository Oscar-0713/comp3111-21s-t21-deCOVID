package comp3111.covid;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.GUI.GUIShowHandler;
import comp3111.covid.Utilities.CountryCode;

public class TestGUIShowHandler {
	private GUIShowHandler testClass;
	private Date startDate;
	private Date endDate;
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	
	@Before
	public void setUp() throws Exception {
		testClass = new GUIShowHandler("COVID_Dataset_v1.0.csv");
		String startDateString = "01/01/2018";
		String endDateString = "01/01/2047";
		startDate = dateFormat.parse(startDateString);
		endDate = dateFormat.parse(endDateString);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCountry() {
		assertTrue(testClass.getAvailableCountry().contains(CountryCode.getByCode("CPV")));
		assertTrue(testClass.getAvailableCountry().contains(CountryCode.getByCode("CHN")));
		assertTrue(testClass.getAvailableCountry().contains(CountryCode.getByCode("HKG")));
	}
	
	@Test
	public void testDate() {
		assertTrue(testClass.getStartDate().compareTo(startDate) > 0);
		assertTrue(testClass.getEndDate().compareTo(endDate) < 0);
	}
	


}
