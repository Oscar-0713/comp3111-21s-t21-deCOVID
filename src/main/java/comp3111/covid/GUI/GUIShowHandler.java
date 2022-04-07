package comp3111.covid.GUI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

import comp3111.covid.DataAnalysis;
import comp3111.covid.Utilities.CountryCode;


/*
 * 
 * This class is for dynamic showing of GUI based on the available country and the valid date range
 * @author Oscar Tse
 * @param String: dataset
 * @version 1.0
 * @throw ParseException when dateFormat in the CSV is invalid
 */
public class GUIShowHandler {
	private ArrayList<CountryCode> availableCountry = new ArrayList<CountryCode>();
	private HashMap<String, Date> dateRange = new HashMap<String, Date>();
	
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public GUIShowHandler(String dataset) throws ParseException {
		for (CSVRecord record : DataAnalysis.getFileParser(dataset)) {
			if (!availableCountry.contains(CountryCode.getByCode(record.get("iso_code"))) && record.get("iso_code") != null) {
				availableCountry.add(CountryCode.getByCode(record.get("iso_code")));
			}
			String dateString = record.get("date");
			Date date = dateFormat.parse(dateString);
			
			// Run only once
			if (dateRange.isEmpty()) {
				dateRange.put("start", date);
				dateRange.put("end", date);
			}
			
			if (date.compareTo(dateRange.get("start")) < 0) {
				dateRange.replace("start", date);
			}
			
			if (date.compareTo(dateRange.get("end")) > 0) {
				dateRange.replace("end", date);
			}

		}
		
	}
	
	
	/*
	 * Return the available country from given CSV
	 * 
	 * @return ArrayList<CountryCode>
	 * @see CountryCode
	 */
	public ArrayList<CountryCode> getAvailableCountry() {
		return availableCountry;
	}
	
	/*
	 * Return the start Date of the CSN
	 * 
	 * @return Date
	 */
	public Date getStartDate() {
		return dateRange.get("start");
	}
	
	
	/*
	 * Return the end date of the whole CSV
	 * 
	 * @return Date
	 */
	public Date getEndDate() {
		return dateRange.get("end");
	}
}
