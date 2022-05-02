package comp3111.covid.GUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import comp3111.covid.Utilities.CountryCode;

/**
 * <p>This is the base class of handling user input. </p>
 * 
 * <p>Do NOT construct this class for use. Instead, use inherited class to get proper attitude. </p>
 * @author Oscar Tse
 *
 */
abstract public class GUISelectHandler {
	/** Store selected country */
	protected ArrayList<CountryCode> selectedCountry = new ArrayList<>();
	
	/**
	 * Get the country list which user is selected
	 * @return selected countryList from user
	 */
	public ArrayList<CountryCode> getSelectedCountryList() {
		return selectedCountry;
	}
	/**
	 * Return the number of available country
	 * @return available country
	 */
	public int getSelectedCountryNum() {
		return selectedCountry.size();
	}
	/**
	 * Get the hashMap of the date of user input
	 * @return HashMap with key dependent on type
	 */
	public abstract HashMap<String, Date> getSelectedDate();
}
