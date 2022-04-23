package comp3111.covid.GUI;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.Utilities.DateUtilities;

/**
 * 
 * This class is for packing required information from GUI to other classes to retrieve data for generating chart.
 *
 */

public class GUISelectChartHandler extends GUISelectHandler {
	private Date startDate;
	private Date endDate;
	
	/**
	 * Constructor for class GUISelectChartHandler
	 * @param selectedCountryName a list of string containing the selected countries
	 * @param selectedStartDate the selected starting date of interest
	 * @param selectedEndDate the selected ending date of interest
	 */
	public GUISelectChartHandler(ArrayList<String> selectedCountryName, String selectedStartDate, String selectedEndDate) {
		for (String country: selectedCountryName) {
			CountryCode code = CountryCode.getByName(country);
			if (code != null) {
				selectedCountry.add(code);
			}
			try {
				startDate = DateUtilities.getDateFormat().parse(selectedStartDate);
				endDate = DateUtilities.getDateFormat().parse(selectedEndDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Return the selected starting and ending dates from the user
	 * return <p>A map contains only TWO keys, "selectStart" and "selectEnd" </p>
	 */
	@Override
	public HashMap<String, Date> getSelectedDate() {
		HashMap<String, Date> map = new HashMap<>();
		map.put("selectStart", startDate);
		map.put("selectEnd", endDate);
		return map;
	}
}
