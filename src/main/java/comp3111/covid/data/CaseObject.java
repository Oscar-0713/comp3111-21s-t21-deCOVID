package comp3111.covid.data;

import comp3111.covid.Utilities.CountryCode;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * An object which handles table output
 * @author Oscar Tse
 *
 */
public class CaseObject {
	private CountryCode country;
	private long totalCase;
	private float totalDeathPerMillion;
	
	private StringProperty countryOutput;
	private StringProperty totalCaseOutput;
	private StringProperty casePerMillsionOutput;
	
	
	/**
	 * Constructor
	 * @param country
	 * @param totalCase
	 * @param casePerMillion
	 */
	public CaseObject(CountryCode country, long totalCase, float casePerMillion) {
		this.country = country;
		this.totalCase = totalCase;
		this.totalDeathPerMillion = casePerMillion;
		constructString();
	}
	
	private void constructString() {
		//Construct String Property
		this.countryOutput = new SimpleStringProperty(this, "country");
		this.countryOutput.setValue(country.getName());
		
		this.totalCaseOutput = new SimpleStringProperty(this, "case");
		this.totalCaseOutput.setValue(Long.toString(totalCase));
		
		this.casePerMillsionOutput = new SimpleStringProperty(this, "casepermillion");
		this.casePerMillsionOutput.setValue(Float.toString(totalDeathPerMillion));
	}
	
	//String property getter
	
	/**
	 * Get country String Property
	 * @return
	 */
	public StringProperty countryProperty() {
		return this.countryOutput;
	}
	
	/**
	 * Get Total Case String Property
	 * @return Corresponding String Property
	 */
	public StringProperty totalDeathProperty() {
		return totalCaseOutput;
	}
	
	/**
	 * Get Case / M String Property
	 * @return Corresponding String Property
	 */
	public StringProperty casePerMillsionOutput() {
		return casePerMillsionOutput;
	}
	
	/**
	 * Get the current object Country
	 * @return country
	 */
	public String getCountry() {
		return countryOutput.get();
	}
	
	/**
	 * Get the total cases
	 * @return total cases
	 */
	public String getCase() {
		return totalCaseOutput.get();
	}
	
	/**
	 * Get Cases / M
	 * @return Cases / M
	 */
	public String getCasepermillion() {
		return casePerMillsionOutput.get();
	}
}
