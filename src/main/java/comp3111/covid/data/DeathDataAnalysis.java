package comp3111.covid.data;

import java.text.ParseException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

import comp3111.covid.DataAnalysis;
import comp3111.covid.GUI.GUISelectTableHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.Utilities.DateUtilities;

public class DeathDataAnalysis {
	private ArrayList<DeathObject> result = new ArrayList<DeathObject>();
	private GUISelectTableHandler handler;
	private String dataset;
	
	public DeathDataAnalysis(String dataset, GUISelectTableHandler handler) {
		this.handler = handler;
		this.dataset = dataset;
		handlingData();
	}
	
	private void handlingData() {
		for (CountryCode code : handler.getSelectedCountryList()) {
			String codeName = code.getAlpha3();
			for (CSVRecord record : DataAnalysis.getFileParser(dataset)) {
				try {
//					System.out.print(handler.getSelectedDate().get("select").toString());
//					System.out.print(DateUtilities.getDateFormat().parse(record.get("date")).toString());
					if (record.get("iso_code").equalsIgnoreCase(codeName) && 
							handler.getSelectedDate().get("select").compareTo(DateUtilities.getDateFormat().parse(record.get("date"))) == 0) {
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
	
	public ArrayList<DeathObject> getResult() {
		return result;
	}
}
