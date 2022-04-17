package comp3111.covid.data;

import comp3111.covid.Utilities.CountryCode;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CaseObject {
	private CountryCode country;
	private long totalCase;
	private float totalDeathPerMillion;
	
	private StringProperty countryOutput;
	private StringProperty totalCaseOutput;
	private StringProperty casePerMillsionOutput;
	
	
	
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
	public StringProperty countryProperty() {
		return this.countryOutput;
	}
	
	public StringProperty totalDeathProperty() {
		return totalCaseOutput;
	}
	
	public StringProperty deathPerMillionProperty() {
		return casePerMillsionOutput;
	}
	
	public String getCountry() {
		return countryOutput.get();
	}
	
	public String getCase() {
		return totalCaseOutput.get();
	}
	
	public String getCasepermillion() {
		return casePerMillsionOutput.get();
	}
}
