package comp3111.covid.data;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import org.apache.commons.csv.CSVRecord;

import comp3111.covid.DataAnalysis;
import comp3111.covid.GUI.GUISelectHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.Utilities.DateUtilities;
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
	 * This method is the old method to handling data with a for-loop.
	 * A new HashMap Method has been implemented.
	 * Use that method instead
	 */
	@Deprecated
	private void handlingData() {
		for (CountryCode code : handler.getSelectedCountryList()) {
			String codeName = code.getAlpha3();
			for (CSVRecord record : DataAnalysis.getFileParser(dataset)) {
				try {
//					System.out.print(handler.getSelectedDate().get("select").toString());
//					System.out.print(DateUtilities.getDateFormat().parse(record.get("date")).toString());
					if (record.get("iso_code").equalsIgnoreCase(codeName) && 
							handler.getSelectedDate().get("select").compareTo(DateUtilities.getDateFormatMMDDYYYY().parse(record.get("date"))) == 0) {
							String totalDeath = record.get("total_deaths");
							String deathPerMillion = record.get("total_deaths_per_million");
							int intTotalDeath = 0;
							float longDeathPerMillion = 0;
							if (!totalDeath.equals("")) {
								intTotalDeath = Integer.parseInt(totalDeath);
							}
							if (!deathPerMillion.equals("")) {
								longDeathPerMillion = Float.parseFloat(deathPerMillion);
							}
							
							DeathObject object = new DeathObject(code, intTotalDeath, longDeathPerMillion);
							result.add(object);
							break;
					}
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
			}
		}
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
			} else {
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
			}
			
			
		}
	}
	
	/**
	 * Get result from the request date and countries
	 * @return results
	 */
	public ArrayList<DeathObject> getResult() {
		return result;
	}
	
	public String getDataSet() {
		return dataset;
	}
	
	
}
