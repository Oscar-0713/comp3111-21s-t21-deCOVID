package comp3111.covid.data;

import comp3111.covid.Utilities.CountryCode;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Vaccination object for table output
 * @author Oscar Tse
 *
 */
public class VaccineObject {
	private CountryCode country;
	private long fullyVaccinated;
	private float percentageVaccinated;
	
	private StringProperty countryOutput;
	private StringProperty fullyVaccinatedOutput;
	private StringProperty percentageVaccinatedOutput;
	
	
	/**
	 * Constructor
	 * @param country
	 * @param fullyVaccinated
	 * @param percentageVaccinated
	 */
	public VaccineObject(CountryCode country, long fullyVaccinated, float percentageVaccinated) {
		this.country = country;
		this.fullyVaccinated = fullyVaccinated;
		this.percentageVaccinated = percentageVaccinated;
		constructString();
	}
	
	private void constructString() {
		//Construct String Property
		this.countryOutput = new SimpleStringProperty(this, "country");
		this.countryOutput.setValue(country.getName());
		
		this.fullyVaccinatedOutput = new SimpleStringProperty(this, "fullyvaccinated");
		this.fullyVaccinatedOutput.setValue(Long.toString(fullyVaccinated));
		
		this.percentageVaccinatedOutput = new SimpleStringProperty(this, "percentagevaccinated");
		//this.percentageVaccinatedOutput.setValue(Float.toString(percentageVaccinated) + "%");
		this.percentageVaccinatedOutput.setValue(Float.toString(percentageVaccinated));
	}
	
	//String property getter
	
	/**
	 * Get the country string property
	 * @return country string property
	 */
	public StringProperty countryProperty() {
		return this.countryOutput;
	}
	
	/**
	 * Get the fully vaccinated people string property
	 * @return fully vaccinated people string property
	 */
	public StringProperty fullyVaccinatedProperty() {
		return fullyVaccinatedOutput;
	}
	
	/**
	 * Get the percentage vaccination string property
	 * @return percentage vaccination string property
	 */
	public StringProperty percentageVaccinatedProperty() {
		return percentageVaccinatedOutput;
	}
	
	/**
	 * Get the country string
	 * @return country string
	 */
	public String getCountry() {
		return countryOutput.get();
	}
	
	
	/**
	 * Get number of people who fully vaccinated
	 * @return get number of people who fully vaccinated
	 */
	public String getFullyvaccinated() {
		return fullyVaccinatedOutput.get();
	}
	
	/**
	 * Return the percentage vaccinated 
	 * @return percentage vaccinated
	 */
	public String getPercentagevaccinated() {
		return percentageVaccinatedOutput.get();
	}
}
