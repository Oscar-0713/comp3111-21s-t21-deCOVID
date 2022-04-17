package comp3111.covid.data;

import java.util.ArrayList;
import java.util.Date;

import comp3111.covid.GUI.GUISelectTableHandler;
import comp3111.covid.Utilities.CountryCode;

public class VaccineAnalysis {
	private ArrayList<VaccineObject> result = new ArrayList<VaccineObject>();
	private GUISelectTableHandler handler;
	private String dataset;
	
	public VaccineAnalysis(String dataset, GUISelectTableHandler handler) {
		this.handler = handler;
		this.dataset = dataset;
		handlingDataWithHashMap();
	}
	
	private void handlingDataWithHashMap() {
		for (CountryCode code : handler.getSelectedCountryList()) {
			Date selectedDate = handler.getSelectedDate().get("select");
			DataCache.getCache();
			DayDataObject data = DataCache.getCache().getData(dataset, code, selectedDate);
			
			if (data != null) {
				VaccineObject object = data.getVaccineObject(code);
				result.add(object);
			}
		}
	}
	/**
	 * Get the result from given date and countries
	 * @return the result 
	 */
	public ArrayList<VaccineObject> getResult() {
		return result;
	}
}
