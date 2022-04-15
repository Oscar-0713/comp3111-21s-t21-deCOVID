package comp3111.covid.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtilities {
	public static DateFormat getDateFormat() {
		return new SimpleDateFormat("MM/dd/yyyy");
	}
}
