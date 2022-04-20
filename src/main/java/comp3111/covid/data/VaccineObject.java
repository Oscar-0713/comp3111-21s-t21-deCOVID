package comp3111.covid.data;

import comp3111.covid.Utilities.CountryCode;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VaccineObject {
	private CountryCode country;
	private long fullyVaccinated;
	private float percentageVaccinated;
	
	private StringProperty countryOutput;
	private StringProperty fullyVaccinatedOutput;
	private StringProperty percentageVaccinatedOutput;
	
	
	
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
	public StringProperty countryProperty() {
		return this.countryOutput;
	}
	
	public StringProperty fullyVaccinatedProperty() {
		return fullyVaccinatedOutput;
	}
	
	public StringProperty percentageVaccinatedProperty() {
		return percentageVaccinatedOutput;
	}
	
	public String getCountry() {
		return countryOutput.get();
	}
	
	public String getFullyvaccinated() {
		return fullyVaccinatedOutput.get();
	}
	
	public String getPercentagevaccinated() {
		return percentageVaccinatedOutput.get();
	}
}
