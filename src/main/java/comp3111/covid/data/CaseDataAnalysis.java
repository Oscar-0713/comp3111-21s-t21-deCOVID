package comp3111.covid.data;

import java.util.ArrayList;
import java.util.Date;

import comp3111.covid.GUI.GUISelectTableHandler;
import comp3111.covid.Utilities.CountryCode;

public class CaseDataAnalysis {
	private ArrayList<CaseObject> result = new ArrayList<CaseObject>();
	private GUISelectTableHandler handler;
	private String dataset;
	
	public CaseDataAnalysis(String dataset, GUISelectTableHandler handler) {
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
				CaseObject object = data.getCaseObject(code);
				result.add(object);
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
}