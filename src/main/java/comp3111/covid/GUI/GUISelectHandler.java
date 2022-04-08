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
	protected ArrayList<CountryCode> selectedCountry = new ArrayList<>();
	
	public ArrayList<CountryCode> getSelectedCountryList() {
		return selectedCountry;
	}
	
	public int getSelectedCountryNum() {
		return selectedCountry.size();
	}
	
	public abstract HashMap<String, Date> getSelectedDate();
}
