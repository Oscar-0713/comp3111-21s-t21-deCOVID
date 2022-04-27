package comp3111.covid.Utilities;

/**
 * This is the enum class of the error label of input
 * @author Oscar
 *
 */
public enum ErrorLabel {
	/** User does not provide a date */
	ERROR_MISSING_DATE,
	
	/** User provides invalid date */
	ERROR_INVALID_DATE,
	
	/** User does not select any country */
	ERROR_NO_COUNTRY,
	
	/** User selects more than one country (For forecast) */
	ERROR_TOO_MANY_COUNTRY,
	
	/** User inputs invalid date range (For chart tasks) */
	ERROR_INVALID_RANGE
}
