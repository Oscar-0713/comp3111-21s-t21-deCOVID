package comp3111.covid.data;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

import comp3111.covid.DataAnalysis;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.Utilities.DateUtilities;

/**
 * <p>This class is for caching the data when the program starts.
 * This class is NOT intended to be constructed by the user</p>
 * 
 * 
 * 
 * @author Oscar Tse
 *
 */
public class DataCache {
	private static DataCache cache;
	private static HashMap<String, HashMap<CountryCode, HashMap<Date, DayDataObject>>> dataMap = new HashMap<>();
	/**
	 * Get the Cache object to initialize and retrieve data
	 * @return DataCache 
	 */
	public static DataCache getCache() {
		if (cache == null) {
			cache = new DataCache();
		}
		return cache;
	}
	/**
	 * Prevent user construction of this class
	 */
	private DataCache() {
		
	}
	
	/**
	 * Insert CSV data to the hashMap
	 * @param dataset the name of the dataset (with .csv)
	 * @throws ParseException 
	 */
	public void initalizeData(String dataset) throws ParseException {
		HashMap<CountryCode, HashMap<Date, DayDataObject>> tempMap = new HashMap<>();
		for (CSVRecord record : DataAnalysis.getFileParser(dataset)) {
			CountryCode code = CountryCode.getByCode(record.get("iso_code"));
			if (!tempMap.containsKey(code)) {
				HashMap<Date,DayDataObject> map = new HashMap<>();
				tempMap.put(code, map);
			}
			
			HashMap<Date, DayDataObject> map = tempMap.get(code);
			Date date = DateUtilities.getDateFormat().parse(record.get("date"));
			
			String newCase = record.get("new_cases_smoothed");
			String newDeath = record.get("new_deaths_smoothed");
			String fullyVaccinated = record.get("people_fully_vaccinated");
			String population = record.get("population");
			String totalCase = record.get("total_cases");
			String totalDeath = record.get("total_deaths");
			
			
			long longNewCase = 0;
			long longNewDeath = 0;
			long longFullyVaccinated = 0;
			long longPopulation = 1;
			long longTotalCase = 0;
			long longTotalDeath = 0;
			boolean isMissing = true;
			if (!newCase.equals("") && !newDeath.equals("") && !fullyVaccinated.equals("") &&
					!population.equals("") && !totalCase.equals("") && !totalDeath.equals("")) {
				isMissing = false;
			}
				
			if (!newCase.equals("")) {
				longNewCase = (long) Double.parseDouble(newCase);
			}
			
			if (!newDeath.equals("")) {
				longNewDeath = (long) Double.parseDouble(newDeath);
			}
			
			if (!fullyVaccinated.equals("")) {
				longFullyVaccinated = (long) Double.parseDouble(fullyVaccinated);
			}
			
			if (!population.equals("")) {
				longPopulation = (long) Double.parseDouble(population);
			}
			
			if (!totalCase.equals("")) {
				longTotalCase = (long) Double.parseDouble(totalCase);
			}
			
			if (!totalDeath.equals("")) {
				longTotalDeath = (long) Double.parseDouble(totalDeath);
			}
			
			DayDataObject object = new DayDataObject(longNewCase, longNewDeath, longFullyVaccinated, longPopulation, longTotalCase, longTotalDeath, isMissing);
			map.put(date, object);
		}
		dataMap.put(dataset, tempMap);
	}
	
	/**
	 * Retrieve the data in the dataCache
	 * @param dataset
	 * @param code
	 * @param date
	 * @return DayDataObject if the object is found, otherwise, return null
	 */
	public DayDataObject getData(String dataset, CountryCode code, Date date) {
		if (dataMap.containsKey(dataset)) {
			HashMap<?,?> DataMap = dataMap.get(dataset);
			if (DataMap.containsKey(code)) {
				HashMap<?,?> CountryMap = (HashMap<?, ?>) DataMap.get(code);
				if (CountryMap.containsKey(date)) {
					return (DayDataObject) CountryMap.get(date);
				}
			}
		}
		return null;
	}
}
