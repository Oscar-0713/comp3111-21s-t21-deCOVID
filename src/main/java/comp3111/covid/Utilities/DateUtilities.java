package comp3111.covid.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtilities {
	public static DateFormat getDateFormatMMDDYYYY() {
		return new SimpleDateFormat("MM/dd/yyyy");
	}
	
	public static DateFormat getDateFormatDDMMYYYY() {
		return new SimpleDateFormat("dd/MM/yyyy");
	}
}
