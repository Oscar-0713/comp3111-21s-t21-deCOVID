package comp3111.covid.data;

import java.util.ArrayList;
import java.util.Date;

import comp3111.covid.GUI.GUISelectHandler;
import comp3111.covid.Utilities.CountryCode;
/**
 * Analysis the vaccination and its rate
 * @author Oscar Tse
 *
 */
public class VaccineAnalysis {
	private ArrayList<VaccineObject> result = new ArrayList<VaccineObject>();
	private GUISelectHandler handler;
	private String dataset;
	/**
	 * Constructor
	 * @param dataset
	 * @param handler
	 */
	public VaccineAnalysis(String dataset, GUISelectHandler handler) {
		this.handler = handler;
		this.dataset = dataset;
		handlingDataWithHashMap();
	}
	
	private void handlingDataWithHashMap() {
		for (CountryCode code : handler.getSelectedCountryList()) {
			if (handler.getSelectedDate().get("select") != null) {
				Date selectedDate = handler.getSelectedDate().get("select");
				DataCache.getCache();
				DayDataObject data = DataCache.getCache().getData(dataset, code, selectedDate);
				if (data != null) {
					VaccineObject object = data.getVaccineObject(code);
					result.add(object);
				}
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
	
	/**
	 * Get the current dataset
	 * @return
	 */
	public String getDataSet() {
		return dataset;
	}
}
