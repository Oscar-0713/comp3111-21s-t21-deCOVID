package comp3111.covid.data;

import comp3111.covid.Utilities.CountryCode;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Death Object for table output
 * @author Oscar Tse
 *
 */
public class DeathObject {
	private CountryCode country;
	private long totalDeath;
	private float totalDeathPerMillion;
	private float newDeaths;
	
	private StringProperty countryOutput;
	private StringProperty totalDeathOutput;
	private StringProperty deathPerMillsionOutput;
	
	
	/**
	 * Constructor (for table)
	 * @param country Country Code of the object
	 * @param totalDeath number of total death
	 * @param deathPerMillion death per million people
	 */
	public DeathObject(CountryCode country, long totalDeath, float deathPerMillion) {
		this.country = country;
		this.totalDeath = totalDeath;
		this.totalDeathPerMillion = deathPerMillion;
		constructString();
	}
	/**
	 * Constructor
	 * @param country Country code of the object
	 * @param totalDeath total death on that day
	 * @param deathPerMillion death per million people
	 * @param newDeaths new deaths om that day
	 */
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
	
	/**
	 * Get the string property for country
	 * @return country string property
	 */
	public StringProperty countryProperty() {
		return this.countryOutput;
	}
	
	/**
	 * Get the total death case string property
	 * @return death case string property
	 */
	public StringProperty totalDeathProperty() {
		return totalDeathOutput;
	}
	
	/**
	 * Get the death case / M string property
	 * @return death case / M string property
	 */
	public StringProperty deathPerMillionProperty() {
		return deathPerMillsionOutput;
	}
	
	/**
	 * Get the object current country name
	 * @return country name
	 */
	public String getCountry() {
		return countryOutput.get();
	}
	
	/**
	 * Get the death string
	 * @return death string
	 */
	public String getDeath() {
		return totalDeathOutput.get();
	}
	
	/**
	 * Get the death / M string
	 * @return death / M string
	 */
	public String getDeathpermillion() {
		return deathPerMillsionOutput.get();
	}
	/**
	 * Get new deaths of that day
	 * @return new deaths
	 */
	public float getNewDeaths() {
		return newDeaths;
	}
}
