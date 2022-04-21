package comp3111.covid.data;

import java.util.ArrayList;
import java.util.Date;

import comp3111.covid.GUI.GUISelectHandler;
import comp3111.covid.Utilities.CountryCode;

/**
 * This class is for retrieving the death data for task B1.
 * 
 * This class interact with other main classes, but will NOT do any modify behavior.
 * @author Oscar Tse
 *
 */
public class DeathDataAnalysis {
	private ArrayList<DeathObject> result = new ArrayList<DeathObject>();
	private GUISelectHandler handler;
	private String dataset;
	
	public DeathDataAnalysis(String dataset, GUISelectHandler handler) {
		this.handler = handler;
		this.dataset = dataset;
		handlingDataWithHashMap();
	}

	/**
	 * Retrieve necessary data
	 */
	private void handlingDataWithHashMap() {
		for (CountryCode code : handler.getSelectedCountryList()) {
			if (handler.getSelectedDate().get("select") != null) {
				Date selectedDate = handler.getSelectedDate().get("select");
				DataCache.getCache();
				DayDataObject data = DataCache.getCache().getData(dataset, code, selectedDate);
				if (data != null) {
					DeathObject object = data.getDeathObject(code);
					result.add(object);
				}
			} 
			//moved to Controller
			/* 
			else {
				// replicated in the controller part as easier to handle the chart (at least for me XD)
				Date selectedDate1 = handler.getSelectedDate().get("selectStart");
				Date selectedDate2 = handler.getSelectedDate().get("selectEnd");
				ZoneId defaultZoneId = ZoneId.systemDefault();
				LocalDate startDate = selectedDate1.toInstant().atZone(defaultZoneId).toLocalDate();
				LocalDate endDate = selectedDate2.toInstant().atZone(defaultZoneId).toLocalDate();
				for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
					DataCache.getCache();
					Date dateDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
					DayDataObject data = DataCache.getCache().getData(dataset, code, dateDate);
					if (data != null) {
						DeathObject object = data.getDeathObject(code);
						result.add(object);
					}
				}
				*/
			
			
			
		}
	}
	
	/**
	 * Get result from the request date and countries
	 * @return results
	 */
	public ArrayList<DeathObject> getResult() {
		return result;
	}
	
	/**
	 * Get the current dataset
	 * @return dataset
	 */
	public String getDataSet() {
		return dataset;
	}
	
	
}
