package comp3111.covid.data;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.csv.CSVRecord;

import comp3111.covid.DataAnalysis;
import comp3111.covid.GUI.GUISelectTableHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.Utilities.DateUtilities;
/**
 * This class is for retriving the death data for task B1.
 * 
 * This class interact with other main classes, but will NOT do any modify behavior.
 * @author Oscar Tse
 *
 */
public class DeathDataAnalysis {
	private ArrayList<DeathObject> result = new ArrayList<DeathObject>();
	private GUISelectTableHandler handler;
	private String dataset;
	
	public DeathDataAnalysis(String dataset, GUISelectTableHandler handler) {
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
			Date selectedDate = handler.getSelectedDate().get("select");
			DataCache.getCache();
			DayDataObject data = DataCache.getCache().getData(dataset, code, selectedDate);
			if (data != null) {
				DeathObject object = data.getDeathObject(code);
				result.add(object);
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
}
