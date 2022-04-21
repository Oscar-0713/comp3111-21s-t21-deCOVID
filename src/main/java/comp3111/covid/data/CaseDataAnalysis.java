package comp3111.covid.data;

import java.util.ArrayList;
import java.util.Date;

import comp3111.covid.GUI.GUISelectHandler;
import comp3111.covid.Utilities.CountryCode;
/**
 * This class is for A1/2, which can handle total case scanerio
 * @author Oscar Tse
 *
 */
public class CaseDataAnalysis {
	private ArrayList<CaseObject> result = new ArrayList<CaseObject>();
	private GUISelectHandler handler;
	private String dataset;
	/**
	 * Constructor
	 * @param dataset
	 * @param handler
	 */
	public CaseDataAnalysis(String dataset, GUISelectHandler handler) {
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
					CaseObject object = data.getCaseObject(code);
					result.add(object);
				}
			}
			
		}
		
	}
	/**
	 * Return the result of the requested countries and date
	 * @return
	 */
	public ArrayList<CaseObject> getResult() {
		return result;
	}
	/**
	 * Get the current using dataset
	 * @return the dataset using
	 */
	public String getDataSet() {
		return dataset;
	}
	
}
