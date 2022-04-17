package comp3111.covid.GUI;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.Utilities.DateUtilities;

/**
 * This class is for use to pack required information from GUI to other classes to retrieve data.
 * 
 * @author Oscar Tse
 *
 */
public class GUISelectTableHandler extends GUISelectHandler {
	private Date date;
	public GUISelectTableHandler(ArrayList<String> selectedCountryName, String selectedDate) {
		for (String country: selectedCountryName) {
			CountryCode code = CountryCode.getByName(country);
			if (code != null) {
				selectedCountry.add(code);
			}
			try {
				date = DateUtilities.getDateFormat().parse(selectedDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//System.out.print(selectedCountry.isEmpty());

;
		
	}
	/**
	 * Return the selected date from the user
	 * return <p>A map contains only ONE key "select" </p>
	 */
	@Override
	public HashMap<String, Date> getSelectedDate() {
		HashMap<String, Date> map = new HashMap<>();
		map.put("select", date);
		return map;
	}

}
