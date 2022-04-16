package comp3111.covid.GUI;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.Utilities.DateUtilities;

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
	@Override
	public HashMap<String, Date> getSelectedDate() {
		HashMap<String, Date> map = new HashMap<>();
		map.put("select", date);
		return map;
	}

}
