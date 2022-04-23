package comp3111.covid.data;

import comp3111.covid.Utilities.CountryCode;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DeathObject {
	private CountryCode country;
	private long totalDeath;
	private float totalDeathPerMillion;
	private float newDeaths;
	
	private StringProperty countryOutput;
	private StringProperty totalDeathOutput;
	private StringProperty deathPerMillsionOutput;
	
	
	
	public DeathObject(CountryCode country, long totalDeath, float deathPerMillion) {
		this.country = country;
		this.totalDeath = totalDeath;
		this.totalDeathPerMillion = deathPerMillion;
		constructString();
	}
	
	public DeathObject(CountryCode country, long totalDeath, float deathPerMillion, float newDeaths) {
		this.country = country;
		this.totalDeath = totalDeath;
		this.totalDeathPerMillion = deathPerMillion;
		constructString();
		
		this.newDeaths = newDeaths;
	}
	
	private void constructString() {
		//Construct String Property
		this.countryOutput = new SimpleStringProperty(this, "country");
		this.countryOutput.setValue(country.getName());
		
		this.totalDeathOutput = new SimpleStringProperty(this, "death");
		this.totalDeathOutput.setValue(Long.toString(totalDeath));
		
		this.deathPerMillsionOutput = new SimpleStringProperty(this, "deathpermillion");
		this.deathPerMillsionOutput.setValue(Float.toString(totalDeathPerMillion));
	}
	
	//String property getter
	public StringProperty countryProperty() {
		return this.countryOutput;
	}
	
	public StringProperty totalDeathProperty() {
		return totalDeathOutput;
	}
	
	public StringProperty deathPerMillionProperty() {
		return deathPerMillsionOutput;
	}
	
	public String getCountry() {
		return countryOutput.get();
	}
	
	public String getDeath() {
		return totalDeathOutput.get();
	}
	
	public String getDeathpermillion() {
		return deathPerMillsionOutput.get();
	}
	
	public float getNewDeaths() {
		return newDeaths;
	}
}
