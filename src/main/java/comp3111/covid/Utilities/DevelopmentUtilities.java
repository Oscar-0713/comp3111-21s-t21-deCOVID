package comp3111.covid.Utilities;

import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import comp3111.covid.DataAnalysis;

/**
 * All methods in this class is for development purpose.
 * NO method within this class should be used in the deployment process.
 * This class should be removed after finishing development.
 * @author Oscar Tse
 *
 */
@Deprecated
public class DevelopmentUtilities {
	/**
	 * Print the missing Country Code in Console.
	 * @param dataset dataset for checking
	 */
	public static void findMissingCountryCode(String dataset) {
		ArrayList<String> result = new ArrayList<>();
		CSVParser file =  DataAnalysis.getFileParser(dataset);
		for (CSVRecord record : file) {
			if (!record.get("iso_code").equals("")) {
				if (CountryCode.getByCode(record.get("iso_code")) == null) {
					if (!result.contains(record.get("iso_code"))) {
						result.add(record.get("iso_code"));
					}
				}
			}
		}
		System.out.print(result);
	}
}
