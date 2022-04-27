package comp3111.covid.data;

import java.time.LocalDate;
import java.time.ZoneId;
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
	private boolean isMissing = false;
	
	/**
	 * Constructor for class CaseDataAnalysis
	 * @param dataset the data set from which the data is retrieved
	 * @param handler the handler used to handle inputs
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
					if (data.isMissing()) {
						isMissing = true;
					}
				}
			}
			//moved to Controller
			 
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
						CaseObject object = data.getCaseObject(code);
						result.add(object);
					}
				}
			}
			
		}
		
	}
	/**
	 * Return the result of the requested countries and date
	 * @return the result of analysis
	 */
	public ArrayList<CaseObject> getResult() {
		return result;
	}
	
	/**
	 * Get the name of the data set
	 * @return the name

	 */
	public String getDataSet() {
		return dataset;
	}
	
	/**
	 * Return whether any data is missing
	 * @return true if missing, false otherwise
	 */
	public boolean getIsMissing() {
		return isMissing;
	}
}
