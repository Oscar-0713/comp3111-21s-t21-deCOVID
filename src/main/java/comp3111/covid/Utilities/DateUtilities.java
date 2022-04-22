package comp3111.covid.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * Utilities about Date and corresponding DateFormat
 * @author Oscar Tse
 *
 */
public class DateUtilities {
	/**
	 * Get the DateFormat for CSV
	 * @return DateFormat MM/dd/yyyy
	 */
	public static DateFormat getDateFormat() {
		return new SimpleDateFormat("MM/dd/yyyy");
	}
}
