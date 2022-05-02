package comp3111.covid.data;

import comp3111.covid.Utilities.CountryCode;
/**
 * This is a data object class, inherited with the dataCache.
 * This object is designed to return ALL required object to show data.
 * 
 * @author oscar
 *
 */
public class DayDataObject {
	private long newCase;
	private long newDeath; 
	private long fullyVanccinated;
	private long population;
	private long totalCase;
	private long totalDeath;
	private boolean isMissing = true;
	private static double ONE_MILLION = 1000000;
	/**
	 * This the original constructor, default as true in "isMissing"
	 * @param newCase number of new case
	 * @param newDeath number of new Death
	 * @param fullyVanccinated number of new vaccinated
	 * @param population number of population
	 * @param totalCase number of total case
	 * @param totalDeath number of total case
	 */
	public DayDataObject(long newCase, long newDeath, long fullyVanccinated, long population, long totalCase, long totalDeath) {
		this.newCase = newCase;
		this.newDeath = newDeath;
		this.fullyVanccinated = fullyVanccinated;
		this.population = population;
		this.totalCase = totalCase;
		this.totalDeath = totalDeath;
	}
	/**
	 * New constructor to make record of data missing
	 * @param newCase number of new case
	 * @param newDeath number of new Death
	 * @param fullyVanccinated number of new vaccinated
	 * @param population number of population
	 * @param totalCase number of total case
	 * @param totalDeath number of total case
	 * @param isMissing whether the data is missing
	 */
	public DayDataObject(long newCase, long newDeath, long fullyVanccinated, long population, long totalCase, long totalDeath, boolean isMissing) {
		this.newCase = newCase;
		this.newDeath = newDeath;
		this.fullyVanccinated = fullyVanccinated;
		this.population = population;
		this.totalCase = totalCase;
		this.totalDeath = totalDeath;
		this.isMissing = isMissing;
	}
	
	
	/**
	 * compatibility interface to get DeathObject
	 * @param code Country Code to construct the object
	 * @return DeathObject
	 */
	public DeathObject getDeathObject(CountryCode code) {
		return new DeathObject(code, totalDeath, (float) ((float)(totalDeath) / ((float) (population/ONE_MILLION))));
	}
	
	/**
	 * compatibility interface to get DeathObject, specialized for new death data
	 * @param code Country Code to construct the object
	 * @return DeathObject
	 */
	public DeathObject getNewDeathObject(CountryCode code) {
		return new DeathObject(code, totalDeath, (float) ((float)(totalDeath) / ((float) (population/ONE_MILLION))), (float) newDeath);
	}
	
	/**
	 * compatibility interface to get CaseObject
	 * @param code Country Code to construct the object
	 * @return The targeted CaseObject for display
	 */
	public CaseObject getCaseObject(CountryCode code) {
		return new CaseObject(code, totalCase, (float) ((float)(totalCase) / ((float) (population/ONE_MILLION))));
	}
	
	/**
	 * compatibility interface to get CaseObject, specialized for new case data
	 * @param code Country Code to construct the object
	 * @return The targeted CaseObject for display
	 */
	public CaseObject getNewCaseObject(CountryCode code) {
		return new CaseObject(code, totalCase, (float) ((float)(totalCase) / ((float) (population/ONE_MILLION))), (float) newCase);
	}
	
	/**
	 * compatibility interface to get VaccineObject
	 * @param code Country Code to construct the object
	 * @return The targeted VaccineObject for display
	 */
	public VaccineObject getVaccineObject(CountryCode code) {
		return new VaccineObject(code, fullyVanccinated, (float) ((float)(fullyVanccinated) / ((float) (population)) *100));
	}
	
	/**
	 * Return whether the data is missing from the data set
	 * @return whether the data is missing
	 */
	public boolean isMissing() {
		return isMissing;
	}
}
