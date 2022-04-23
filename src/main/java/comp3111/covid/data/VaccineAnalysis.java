package comp3111.covid.data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import comp3111.covid.GUI.GUISelectHandler;
import comp3111.covid.Utilities.CountryCode;

public class VaccineAnalysis {
	private ArrayList<VaccineObject> result = new ArrayList<VaccineObject>();
	private GUISelectHandler handler;
	private String dataset;
	
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
			
			else {
				// replicated in the controller part as easier to handle the chart (at least for me XD)
				Date selectedDate1 = handler.getSelectedDate().get("selectStart");
				Date selectedDate2 = handler.getSelectedDate().get("selectEnd");
				ZoneId defaultZoneId = ZoneId.systemDefault();
				LocalDate startDate = selectedDate1.toInstant().atZone(defaultZoneId).toLocalDate();
				LocalDate endDate = selectedDate2.toInstant().atZone(defaultZoneId).toLocalDate();
				DataCache.getCache();
				for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
					Date dateDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
					DayDataObject data = DataCache.getCache().getData(dataset, code, dateDate);
					if (data != null) {
						VaccineObject object = data.getVaccineObject(code);
						result.add(object);
					}
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
	
	public String getDataSet() {
		return dataset;
	}
}
