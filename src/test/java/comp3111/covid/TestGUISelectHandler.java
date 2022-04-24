package comp3111.covid;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.GUI.GUISelectChartHandler;
import comp3111.covid.GUI.GUISelectTableHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.data.DataCache;


public class TestGUISelectHandler {
	private ArrayList<String> selectedCountries;
	private GUISelectTableHandler tableHandler;
	private GUISelectChartHandler chartHandler;
	private Date startDate;
	private Date endDate;
	private HashMap<String, Date> selectedDate;
	private HashMap<String, Date> selectedDates;
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	@Before
	public void setUp() throws Exception {
		selectedCountries = new ArrayList<String>();
		selectedCountries.add(CountryCode.HK.getName());
		selectedCountries.add(CountryCode.US.getName());
		String startDateString = "12/23/2021";
		String endDateString = "12/28/2020";
		startDate = dateFormat.parse(startDateString);
		endDate = dateFormat.parse(endDateString);
		selectedDate.put("select", startDate);
		selectedDates.put("selectStart", startDate);
		selectedDates.put("selectEnd", endDate);
		tableHandler = new GUISelectTableHandler(selectedCountries, startDateString);
		chartHandler = new GUISelectChartHandler(selectedCountries, startDateString, endDateString);
		//DataCache.getCache().initalizeData("COVID_Dataset_v1.0.csv");
	}

	@After
	public void tearDown() throws Exception {
	}
	/*
	@Test
	public void testSelectedCountryNumber() {
		//assertEquals(tableHandler.getSelectedCountryNum(), selectedCountries.size());
		//assertEquals(chartHandler.getSelectedCountryNum(), selectedCountry.size());
		//assertEquals(2, 2);
	}
	
	
	@Test
	public void testSelectedCountryList() {
		//assertEquals(tableHandler.getSelectedCountryList(), selectedCountry);
		//assertEquals(chartHandler.getSelectedCountryList(), selectedCountry);
		assertTrue(chartHandler.getSelectedCountryList().contains(CountryCode.getByName("HKG")));
	}
	
	@Test
	public void testSelectedDate() {
		assertEquals(tableHandler.getSelectedDate(), selectedDate);
		assertEquals(chartHandler.getSelectedDate(), selectedDates);
	}
	*/
}
